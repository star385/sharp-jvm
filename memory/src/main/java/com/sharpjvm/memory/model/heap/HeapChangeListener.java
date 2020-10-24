package com.sharpjvm.memory.model.heap;

/**
 * 堆内存变化事件监听器。
 * 
 * User: zhuguoyin
 * Date: 13-3-8
 * To change this template use File | Settings | File Templates.
 */
public interface HeapChangeListener {

	/**
	 * 内存变化前事件。
	 * 
	 * @param e
	 */
    void beforeChange(HeapChangerEvent e);

    /**
     * 内存变化后事件。
     * 
     * @param e
     */
    void afterChanged(HeapChangerEvent e);
}
