package com.sharpjvm.jvm.test.complex;

/**
 * ���Ե�����ӿ�
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public interface Operation {

	/**
	 * ��Ӳ�����
	 * 
	 * @param o
	 */
    void addOperator(Object o);

    /**
     * ִ�����㡣
     * 
     * @return
     */
    Object executeResult();
}
