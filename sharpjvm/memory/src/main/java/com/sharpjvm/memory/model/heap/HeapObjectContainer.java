package com.sharpjvm.memory.model.heap;

/**
 * ���ڴ��������
 * 
 * User: zhuguoyin
 * Date: 13-3-8
 * To change this template use File | Settings | File Templates.
 */
public interface HeapObjectContainer {

	/**
	 * ��ȡ�������õĶ���
	 * 
	 * @return
	 */
    Object getValue();
    
    /**
     * ���ö���
     * 
     * @param o
     */
    void putValue(Object o);
}
