package com.sharpjvm.jvm.test.complex;

/**
 * 运算工厂
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public class OperationFactory {

	/**
	 * 根据描述符获取运算器。
	 * 
	 * @param descriptor
	 * @return
	 */
    public static Operation getOperation(String descriptor) {
        if (Constants.ADD_DESCRIPTOR.equals(descriptor)) {
            return new AddOperation();
        }
        if (Constants.SUB_DESCRIPTOR.equals(descriptor)) {
            return new SubOperation();
        }
        return null;
    }
}
