package com.sharpjvm.memory.model.heap;

/**
 * 堆内存对象容器
 * 
 * User: zhuguoyin
 * Date: 13-3-8
 * To change this template use File | Settings | File Templates.
 */
public interface HeapObjectContainer {

	/**
	 * 获取真正放置的对象
	 * 
	 * @return
	 */
    Object getValue();
    
    /**
     * 放置对象
     * 
     * @param o
     */
    void putValue(Object o);
}
