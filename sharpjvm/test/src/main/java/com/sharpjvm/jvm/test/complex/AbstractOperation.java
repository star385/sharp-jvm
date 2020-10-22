package com.sharpjvm.jvm.test.complex;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的运算器。
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractOperation implements Operation {

    protected List<Object> operatorList;

    /**
     * 添加参数。
     */
    public void addOperator(Object o) {
        if (operatorList == null) {
            operatorList = new ArrayList<Object>();
        }
        operatorList.add(o);
    }

    public Object executeResult() {
    	// 首先校验参数，校验逻辑由子类去实现
        ValidateResult validateResult = validateOperators();
        // 没有校验通过，就不执行运算操作了
        if (!validateResult.isSuccess()) {
            return validateResult;
        }
        // 真正地执行运算
        return executeReality();
    }

    /**
     * 确保参数是合法的以后，执行运算。
     * 
     * @return
     */
    protected abstract Object executeReality();

    /**
     * 校验参数是否合法
     * 
     * @return
     */
    protected abstract ValidateResult validateOperators();
}
