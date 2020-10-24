package com.sharpjvm.memory.model.heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.memory.model.config.SystemConfig;
import com.sharpjvm.memory.model.util.TwoWayHashMap;

/**
 * �ѵ�Ĭ��ʵ�֡�
 * �˴�ʵ���ǱȽϼ򵥵ģ�Ŀǰ��������������ڴ������Ƿִ��ģ��˴�Ҳ�зִ��ĸ����Ϊ���������������
 * ���������ֻ��һ��������ѣ���û���������������Կ���ʵ�ֶ��ڴ����ķִ�ʵ�֡�
 *
 * User: zhuguoyin
 * Date: 13-3-8
 * To change this template use File | Settings | File Templates.
 */
public class DefaultHeap implements Heap {

	// ����������ʵ�������������ڴ�������Ϊ�����ֻ��һ����ƿ���ѡ�
	// �������õ�ʵ�����ĸ��ֽڵ��ֽ����飬��������������ȫ��ͬ���ֽ����鲢��equals��û�а취��ֻ�ܰ��ֽ�����ת��ΪInteger���š�
	// վ��һ���ĸ߶ȣ����ǿ�����Ϊint����byte[4]
    private TwoWayHashMap<Integer, HeapObjectContainer> newGeneration;

    @SuppressWarnings("unused")
    private Map<Integer, HeapObjectContainer> oldGeneration;

    private List<HeapChangeListener> listeners = null;

    private static volatile int serialNumber = 1;

    public byte[] getReference(Object value) {
    	// �������Ϊnull���򷵻�0���ٶ�null������Ϊ0
    	if (value == null) {
            return ByteUtil.int2ByteArray(0);
        }
    	
    	// �Ѿ������˴˶���ֱ�Ӵ��ڴ��л�ȡ����
        if (containsValue(value)) {
            int number = getKeyByValue(value); 
            return ByteUtil.int2ByteArray(number);
        }

		// Ϊ�˷�ֹ���߳�ʱ���������������ȫһ������Ҫͬ��һ��
		synchronized (DefaultHeap.this) {
			// ʵ�����þ��ǰ�һ��Integer������ת��Ϊ�ֽ�������ѣ������أ������������ĸ��ֽ�
			// java������淶Ҳû����ȷ�涨�������ʵ�֣��������ظ��������Լ����ɵĹ�����С�
			byte[] reference = ByteUtil.int2ByteArray(serialNumber);
			// ��Ϊ���ǲ���ֱ�ӰѶ���Ž�����п��ܻ���Ҫ���ñ����Ϣ�����Ҫ��ȡһ����������ӿ�
			// ������ʵ���ڷŵ������ļ����������ã���ʹ��Ĭ�ϵġ�
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
			// �����ڴ�仯ǰ�¼�
			fireBeforeChangeEvent(reference, heapObjectContainer,
					HeapChangerEvent.TYPE_PUT_IN);
			getNewGenerationInner().put(serialNumber, heapObjectContainer);
			serialNumber++;
			// �����ڴ�仯���¼�
			fireAfterChangedEvent(reference, heapObjectContainer,
					HeapChangerEvent.TYPE_PUT_IN);
			return reference;
		}
        
    }

    // ���ݶ����ȡ����
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
