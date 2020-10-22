package com.sharpjvm.jvm.test.complex;

/**
 * 加法运算器。
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public class AddOperation extends AbstractOperation {

	/**
	 * 运算，将所有参数转换为double，然后加起来得到加法结果。
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
     * 参数校验，校验逻辑是参数不能少于2个，每个参数必须都是数字。
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
