package com.sharpjvm.memory.model.methodArea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.memory.classloader.ClassInfoLoader;
import com.sharpjvm.memory.model.heap.HeapChangeListener;
import com.sharpjvm.memory.model.heap.HeapChangerEvent;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * 方法区，保存已加载的类和常量等信息。
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
public class DefaultMethodArea implements MethodArea {

    private Map<String, Map<String, ClassInfo>> classInfoMap = null;
    
    private Map<String, ClassInfoLoader> classInfoLoaderMap = null;

    private List<HeapChangeListener> listeners = null;

    public void putClassInfo(String classLoaderClassName, String className, ClassInfo classInfo) {
    	fireBeforeChangeEvent(classLoaderClassName, classInfo, HeapChangerEvent.TYPE_PUT_IN);
        getClassInfoMap(classLoaderClassName).put(className, classInfo);
        fireAfterChangedEvent(classLoaderClassName, classInfo, HeapChangerEvent.TYPE_PUT_IN);
    }

    public ClassInfo getClassInfo(String classLoaderClassName, String key) throws ClassNotFoundException {
        ClassInfo classInfo = getClassInfoMap(classLoaderClassName).get(key);
        if (classInfo == null) {
        	if (RuntimeContext.getInstance().getBootStrap().getClass().getCanonicalName().equals(classLoaderClassName)) {
        		classInfo = RuntimeContext.getInstance().getBootStrap().loadClassInfo(key);
        	} else {
        		ClassInfoLoader classInfoLoader = getClassInfoLoaderMap().get(classLoaderClassName);
        		if (classInfoLoader == null) {
        			classInfoLoader = newInstance(classLoaderClassName);
        		}
        		classInfo = classInfoLoader.loadClassInfo(key);
        	}
        	putClassInfo(classLoaderClassName, key, classInfo);
        }
        return classInfo;
    }

    private ClassInfoLoader newInstance(String classLoaderClassName) {
    	try {
    		ClassInfoLoader classInfoLoader = (ClassInfoLoader) Class.forName(classLoaderClassName).newInstance();
    		getClassInfoLoaderMap().put(classLoaderClassName, classInfoLoader);
    		return classInfoLoader;
    	} catch (Exception e) {
    		throw new RuntimeException("实例化classInfoLoader：" + classLoaderClassName + " 出错，请检查配置！");
    	}
	}
    
    private void fireBeforeChangeEvent(String classLoaderClassName, ClassInfo classInfo, int type) {
        if (listeners == null) {
            return;
        }
        for (HeapChangeListener listener : listeners) {
            HeapChangerEvent event = new HeapChangerEvent(this);
            event.setClassInfo(classInfo);
            event.setType(type);
            event.setClassLoaderClassName(classLoaderClassName);
            listener.beforeChange(event);
        }
    }

    private void fireAfterChangedEvent(String classLoaderClassName, ClassInfo classInfo, int type) {
        if (listeners == null) {
            return;
        }
        for (HeapChangeListener listener : listeners) {
        	HeapChangerEvent event = new HeapChangerEvent(this);
            event.setClassInfo(classInfo);
            event.setType(type);
            event.setClassLoaderClassName(classLoaderClassName);
            listener.afterChanged(event);
        }
    }

	public ClassInfo getClassInfo(String key) throws ClassNotFoundException {
        return getClassInfo(RuntimeContext.getInstance().getBootStrap().getClass().getName(), key);
    }

    private Map<String, ClassInfo> getClassInfoMap(String classLoaderClassName) {
        if (classInfoMap == null) {
            classInfoMap = new HashMap<String, Map<String, ClassInfo>>();
        }

        if (classInfoMap.get(classLoaderClassName) == null) {
            classInfoMap.put(classLoaderClassName, new HashMap<String, ClassInfo>());
        }
        return classInfoMap.get(classLoaderClassName);
    }

    private Map<String, ClassInfoLoader> getClassInfoLoaderMap() {
    	if (classInfoLoaderMap == null) {
    		classInfoLoaderMap = new HashMap<String, ClassInfoLoader>();
    	}
		return classInfoLoaderMap;
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

	public byte[] getConstantFromPool(String className, String fieldName) {
		return null;
	}

	public void putToConstantPool(String className, String fieldName,
			byte[] value) {
		
	}

	public void removeClassInfo(String classLoaderClassName, ClassInfo classInfo) {
		Map<String, ClassInfo> classInfoMap = getClassInfoMap(classLoaderClassName);
		String key = null;
		for (Map.Entry<String, ClassInfo> entry : classInfoMap.entrySet()) {
			if (entry.getValue() == classInfo) {
				key = entry.getKey();
				break;
			}
		}
		if (key != null) {
			fireBeforeChangeEvent(classLoaderClassName, classInfo, HeapChangerEvent.TYPE_REMOVE);
			classInfoMap.remove(key);
			fireAfterChangedEvent(classLoaderClassName, classInfo, HeapChangerEvent.TYPE_REMOVE);
		}
		
	}
}
