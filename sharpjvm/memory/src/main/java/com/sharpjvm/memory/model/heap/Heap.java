package com.sharpjvm.memory.model.heap;

import java.util.Collection;

/**
 * 堆内存模型
 * 
 * User: zhuguoyin
 * Date: 13-3-8
 * To change this template use File | Settings | File Templates.
 */
public interface Heap {

	/**
	 * 根据引用获取真正的对象
	 * 
	 * @param reference
	 * @return
	 */
    Object get(byte[] reference);

    /**
     * 根据对象获取引用，如果该对象尚未创建引用，则创建一个索引。
     * 
     * @param value
     * @return
     */
    byte[] getReference(Object value);

    /**
     * 根据引用获取对象的容器
     * 
     * @param reference
     * @return
     */
    HeapObjectContainer getContainer(byte[] reference);

    /**
     * 添加堆内存变化的监听器
     * 
     * @param heapChangeListener
     */
    void addHeapChangeListener(HeapChangeListener heapChangeListener);
    
    /**
     * 移除堆内存变化监听器
     * 
     * @param heapChangeListener
     */
    void removeHeapChangeListener(HeapChangeListener heapChangeListener);

    /**
     * 是否包含某个引用
     * 
     * @param key
     * @return
     */
    boolean containsReference(byte[] key);

    /**
     * 是否包含某个对象
     * 
     * @param value
     * @return
     */
    boolean containsValue(Object value);

    /**
     * 对象引用计数器加1
     * 
     * @param reference
     */
    void incReferenceCount(byte[] reference);

    /**
     * 对象引用计数器减1
     * 
     * @param reference
     */
    void decReferenceCount(byte[] reference);

    /**
     * 将对象移除出堆
     * 
     * @param o
     */
    void remove(Object o);

    /**
     * 根据引用把堆内存对象移除掉
     * 
     * @param key
     */
    void removeByReference(byte[] key);

    /**
     * 设置堆内存的整理器，比如某些对象是不是该从新生代进入老年代了。
     * 
     * @param heapOrganizer
     */
    void setHeapOrganizer(HeapOrganizer heapOrganizer);

    /**
     * 获取所有堆里的对象
     * 
     * @return
     */
    Collection<HeapObjectContainer> getAllHeapObject();
}
