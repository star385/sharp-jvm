package com.sharpjvm.jvm.test.complex;

/**
 * 测试的运算接口
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public interface Operation {

	/**
	 * 添加参数。
	 * 
	 * @param o
	 */
    void addOperator(Object o);

    /**
     * 执行运算。
     * 
     * @return
     */
    Object executeResult();
}
