package com.sharpjvm.memory.model.heap;

/**
 * ���ڴ�������
 * �ܶ��߼��ڴ���ɣ������������Ƿ�ý����������
 * 
 * User: zhuguoyin
 * Date: 13-3-8
 * To change this template use File | Settings | File Templates.
 */
public interface HeapOrganizer {

	/**
	 * �����ڴ�
	 * 
	 * @param heap
	 */
    void organizeHeap(Heap heap);
}
