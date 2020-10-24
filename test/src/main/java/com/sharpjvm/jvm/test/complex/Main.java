package com.sharpjvm.jvm.test.complex;

/**
 * ≤‚ ‘¿‡»Îø⁄°£
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        Operation addOperation = OperationFactory.getOperation(Constants.ADD_DESCRIPTOR);
        addOperation.addOperator(12);
        addOperation.addOperator(23.1);
        addOperation.addOperator(90);
        Object addResult = addOperation.executeResult();
        if (addResult instanceof ValidateResult) {
            ValidateResult validateResult = (ValidateResult) addResult;
            System.err.println("execute wrong! message:" + validateResult.getMessage());
        } else {
            System.out.println("execute success! the result is:" + addResult);
        }

        Operation subOperation = OperationFactory.getOperation(Constants.SUB_DESCRIPTOR);
        subOperation.addOperator(12);
        subOperation.addOperator(23.1);
        subOperation.addOperator(90);
        Object subResult = subOperation.executeResult();
        if (subResult instanceof ValidateResult) {
            ValidateResult validateResult = (ValidateResult) subResult;
            System.err.println("execute wrong! message:" + validateResult.getMessage());
        } else {
            System.out.println("execute success! the result is:" + subResult);
        }
    }
}
