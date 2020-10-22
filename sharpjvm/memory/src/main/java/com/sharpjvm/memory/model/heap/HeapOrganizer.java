package com.sharpjvm.memory.model.heap;

/**
 * 堆内存整理器
 * 很多逻辑在此完成，比如新生代是否该进入老年代等
 * 
 * User: zhuguoyin
 * Date: 13-3-8
 * To change this template use File | Settings | File Templates.
 */
public interface HeapOrganizer {

	/**
	 * 整理内存
	 * 
	 * @param heap
	 */
    void organizeHeap(Heap heap);
}
