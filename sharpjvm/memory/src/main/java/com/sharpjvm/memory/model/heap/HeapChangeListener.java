package com.sharpjvm.memory.model.heap;

/**
 * ���ڴ�仯�¼���������
 * 
 * User: zhuguoyin
 * Date: 13-3-8
 * To change this template use File | Settings | File Templates.
 */
public interface HeapChangeListener {

	/**
	 * �ڴ�仯ǰ�¼���
	 * 
	 * @param e
	 */
    void beforeChange(HeapChangerEvent e);

    /**
     * �ڴ�仯���¼���
     * 
     * @param e
     */
    void afterChanged(HeapChangerEvent e);
}
