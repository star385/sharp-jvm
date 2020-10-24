package com.sharpjvm.jvm.test.complex;

/**
 * �ӷ���������
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public class AddOperation extends AbstractOperation {

	/**
	 * ���㣬�����в���ת��Ϊdouble��Ȼ��������õ��ӷ������
	 */
    @Override
    protected Object executeReality() {
        double result = 0.0;
        for (Object obj : operatorList) {
            Number number = (Number) obj;
            result += number.doubleValue();
        }
        return result;
    }

    /**
     * ����У�飬У���߼��ǲ�����������2����ÿ���������붼�����֡�
     */
    @Override
    protected ValidateResult validateOperators() {
        ValidateResult validateResult = new ValidateResult();
        if (operatorList == null || operatorList.size() <= 2) {
            validateResult.setSuccess(false);
            validateResult.setMessage("operator count wrong!");
            return validateResult;
        }
        for (Object obj : operatorList) {
            if (!(obj instanceof Number)) {
                validateResult.setSuccess(false);
                validateResult.setMessage("operator's type is " + obj.getClass().getName()
                        + "not a number!");
                return validateResult;
            }
        }
        validateResult.setSuccess(true);
        return validateResult;
    }
}
