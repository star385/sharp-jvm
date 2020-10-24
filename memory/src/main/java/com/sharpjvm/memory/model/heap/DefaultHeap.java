package com.sharpjvm.memory.model.heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.memory.model.config.SystemConfig;
import com.sharpjvm.memory.model.util.TwoWayHashMap;

/**
 * 堆的默认实现。
 * 此处实现是比较简单的，目前主流的虚拟机堆内存区都是分代的，此处也有分代的概念，分为新生代和老年代。
 * 但是老年代只是一个摆设而已，并没有用它，将来可以考虑实现堆内存区的分代实现。
 *
 * User: zhuguoyin
 * Date: 13-3-8
 * To change this template use File | Settings | File Templates.
 */
public class DefaultHeap implements Heap {

	// 新生代，其实它就是整个堆内存区，因为老年代只是一个花瓶而已。
	// 本来引用的实现是四个字节的字节数组，但是由于内容完全相同的字节数组并不equals，没有办法，只能把字节数组转换为Integer来放。
	// 站在一定的高度，咱们可以认为int就是byte[4]
    private TwoWayHashMap<Integer, HeapObjectContainer> newGeneration;

    @SuppressWarnings("unused")
    private Map<Integer, HeapObjectContainer> oldGeneration;

    private List<HeapChangeListener> listeners = null;

    private static volatile int serialNumber = 1;

    public byte[] getReference(Object value) {
    	// 如果对象为null，则返回0，假定null的引用为0
    	if (value == null) {
            return ByteUtil.int2ByteArray(0);
        }
    	
    	// 已经放置了此对象，直接从内存中获取出来
        if (containsValue(value)) {
            int number = getKeyByValue(value); 
            return ByteUtil.int2ByteArray(number);
        }

		// 为了防止多线程时两个对象的引用完全一样，需要同步一下
		synchronized (DefaultHeap.this) {
			// 实际引用就是把一个Integer的数字转换为字节数组而已，管它呢，反正引用是四个字节
			// java虚拟机规范也没有明确规定引用如何实现，反正不重复，我有自己生成的规则就行。
			byte[] reference = ByteUtil.int2ByteArray(serialNumber);
			// 因为我们不是直接把对象放进堆里，有可能还需要放置别的信息，因此要提取一个容器这个接口
			// 容器的实现在放到配置文件里。如果不配置，就使用默认的。
			HeapObjectContainer heapObjectContainer;
			String heapContainerClassName = SystemConfig.getInstance()
					.getHeapContainerClassName();
			if (heapContainerClassName == null
					|| "".equals(heapContainerClassName)) {
				heapObjectContainer = new DefaultHeapObjectContainer();
			} else {
				try {
					heapObjectContainer = (HeapObjectContainer) Class.forName(
							heapContainerClassName).newInstance();
				} catch (Exception e) {
					heapObjectContainer = new DefaultHeapObjectContainer();
				}
			}
			heapObjectContainer.putValue(value);
			// 发送内存变化前事件
			fireBeforeChangeEvent(reference, heapObjectContainer,
					HeapChangerEvent.TYPE_PUT_IN);
			getNewGenerationInner().put(serialNumber, heapObjectContainer);
			serialNumber++;
			// 发送内存变化后事件
			fireAfterChangedEvent(reference, heapObjectContainer,
					HeapChangerEvent.TYPE_PUT_IN);
			return reference;
		}
        
    }

    // 根据对象获取索引
    private int getKeyByValue(Object value) {
        if (newGeneration == null || newGeneration.isEmpty()) {
            return 0;
        }
        for (Integer key : newGeneration.keySet()) {
            HeapObjectContainer heapObjectContainer = newGeneration.get(key);
            if (heapObjectContainer.getValue() == value) {
                return key;
            }
        }
        return 0;
    }

    private void fireBeforeChangeEvent(byte[] reference, HeapObjectContainer heapObjectContainer, int type) {
        if (listeners == null) {
            return;
        }
        for (HeapChangeListener listener : listeners) {
            HeapChangerEvent event = new HeapChangerEvent(this);
            event.setHeapObjectContainer(heapObjectContainer);
            event.setType(type);
            event.setReference(reference);
            listener.beforeChange(event);
        }
    }

    private void fireAfterChangedEvent(byte[] reference, HeapObjectContainer heapObjectContainer, int type) {
        if (listeners == null) {
            return;
        }
        for (HeapChangeListener listener : listeners) {
            HeapChangerEvent event = new HeapChangerEvent(this);
            event.setHeapObjectContainer(heapObjectContainer);
            event.setType(type);
            event.setReference(reference);
            listener.afterChanged(event);
        }
    }

    private Map<Integer, HeapObjectContainer> getNewGenerationInner() {
        if (newGeneration == null) {
            newGeneration = new TwoWayHashMap<Integer, HeapObjectContainer>();
        }
        return newGeneration;
    }

    public Object get(byte[] reference) {
        int key = ByteUtil.byteArray2Int(reference);
        if (key == 0) {
            return null;
        }
        return getNewGenerationInner().get(key).getValue();
    }

    public HeapObjectContainer getContainer(byte[] reference) {
        int key = ByteUtil.byteArray2Int(reference);
        return getNewGenerationInner().get(key);
    }

    public void addHeapChangeListener(HeapChangeListener heapChangeListener) {
        if (listeners == null) {
            listeners = new ArrayList<HeapChangeListener>();
        }
        listeners.add(heapChangeListener);
    }

    public void removeHeapChangeListener(HeapChangeListener heapChangeListener) {
        if (listeners == null) {
            return;
        }
        listeners.remove(heapChangeListener);
    }

    public boolean containsReference(byte[] reference) {
        int key = ByteUtil.byteArray2Int(reference);
        return getNewGenerationInner().containsKey(key);
    }

    public boolean containsValue(Object value) {
    	Collection<HeapObjectContainer> values = getNewGenerationInner().values();
    	if (values == null || values.isEmpty()) {
    		return false;
    	}
    	for (HeapObjectContainer ele : values) {
    		if (ele.getValue() == value) {
    			return true;
    		}
    	}
        return false;
    }

    public void incReferenceCount(byte[] reference) {

    }

    public void decReferenceCount(byte[] reference) {

    }

    public void remove(Object o) {
        if (newGeneration == null) {
            return;
        }
        for (Integer key : newGeneration.keySet()) {
            HeapObjectContainer heapObjectContainer = newGeneration.get(key);
            if (o.equals(heapObjectContainer.getValue())) {
                byte[] reference = ByteUtil.int2ByteArray(key);
				fireBeforeChangeEvent(reference, heapObjectContainer, HeapChangerEvent.TYPE_REMOVE);
                newGeneration.remove(key);
                fireAfterChangedEvent(reference, heapObjectContainer, HeapChangerEvent.TYPE_REMOVE);
            }
        }
    }

    public void removeByReference(byte[] reference) {
        if (newGeneration == null) {
            return;
        }
        int keyInt = ByteUtil.byteArray2Int(reference);
        if (newGeneration.containsKey(keyInt)) {
            HeapObjectContainer obj = newGeneration.get(reference);
            fireBeforeChangeEvent(reference, obj, HeapChangerEvent.TYPE_REMOVE);
            newGeneration.remove(obj);
            fireAfterChangedEvent(reference, obj, HeapChangerEvent.TYPE_REMOVE);
        }
    }

    public void setHeapOrganizer(HeapOrganizer heapOrganizer) {

    }

    public Collection<HeapObjectContainer> getAllHeapObject() {
        Collection<HeapObjectContainer> heapObjectContainers = getNewGenerationInner().values();
        return heapObjectContainers;
    }
}
