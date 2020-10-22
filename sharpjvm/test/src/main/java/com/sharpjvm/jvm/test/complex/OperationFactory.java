package com.sharpjvm.jvm.test.complex;

/**
 * ���㹤��
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public class OperationFactory {

	/**
	 * ������������ȡ��������
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
