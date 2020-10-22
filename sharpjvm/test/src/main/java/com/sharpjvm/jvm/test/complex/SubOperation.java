package com.sharpjvm.jvm.test.complex;

/**
 * 减法运算器。
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public class SubOperation extends AbstractOperation {

	/**
	 * 第一个参数减去第二个参数。
	 */
    @Override
    protected Object executeReality() {
        double operator1 = ((Number)operatorList.get(0)).doubleValue();
        double operator2 = ((Number)operatorList.get(1)).doubleValue();
        return operator1 - operator2;
    }

    /**
     * 校验参数，只允许2个参数。并且参数都必须是数字
     */
    @Override
    protected ValidateResult validateOperators() {
        ValidateResult validateResult = new ValidateResult();
        if (operatorList == null || operatorList.size() != 2) {
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
