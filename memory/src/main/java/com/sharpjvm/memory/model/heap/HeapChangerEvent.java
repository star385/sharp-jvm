package com.sharpjvm.memory.model.heap;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import com.sharpjvm.bytecode.bean.ClassInfo;

/**
 * 内存变化事件。
 * 
 * User: zhuguoyin
 * Date: 13-3-8
 * Time: 10:10
 * To change this template use File | Settings | File Templates.
 */
public class HeapChangerEvent extends EventObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 放入事件类型
	public static final int TYPE_PUT_IN = 1;

	// 移除事件类型
    public static final int TYPE_REMOVE = 2;
    
    public static final String KEY_TYPE = "type";
    
    public static final String KEY_REFERENCE = "reference";
    
    public static final String KEY_HEAP_OBJECT_CONTAINER = "heapObjectContainer";
    
    public static final String KEY_CLASS_INFO = "classInfo";
    
    public static final String KEY_CLASS_LOADER_CLASS_NAME = "classLoaderClassName";

    private Map<String, Object> context;

    /**
     * 构造方法
     *
     * @param source 其实就是com.sharpjvm.memory.model.heap.Heap对象.
     * @throws IllegalArgumentException
     *          if source is null.
     */
    public HeapChangerEvent(Object source) {
        super(source);
    }

    public int getType() {
        return (Integer) getContextInner().get(KEY_TYPE);
    }

    public void setType(int type) {
    	getContextInner().put(KEY_TYPE, type);
    }

    public HeapObjectContainer getHeapObjectContainer() {
        return (HeapObjectContainer) getContextInner().get(KEY_HEAP_OBJECT_CONTAINER);
    }

    public void setHeapObjectContainer(HeapObjectContainer heapObjectContainer) {
        getContextInner().put(KEY_HEAP_OBJECT_CONTAINER, heapObjectContainer);
    }

    public byte[] getReference() {
        return (byte[]) getContextInner().get(KEY_REFERENCE);
    }

    public void setReference(byte[] key) {
        getContextInner().put(KEY_REFERENCE, key);
    }
    
    public ClassInfo getClassInfo() {
        return (ClassInfo) getContextInner().get(KEY_CLASS_INFO);
    }

    public void setClassInfo(ClassInfo classInfo) {
        getContextInner().put(KEY_REFERENCE, classInfo);
    }
    
    public String getClassLoaderClassName() {
        return (String) getContextInner().get(KEY_CLASS_LOADER_CLASS_NAME);
    }

    public void setClassLoaderClassName(String classLoaderClassName) {
        getContextInner().put(KEY_CLASS_LOADER_CLASS_NAME, classLoaderClassName);
    }
    
    private Map<String, Object> getContextInner() {
    	if (context == null) {
    		context = new HashMap<String, Object>();
    	}
    	return context;
    }

	public Map<String, Object> getContext() {
		return context;
	}

}
