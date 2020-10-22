package com.sharpjvm.jvm.test.complex;

import java.util.ArrayList;
import java.util.List;

/**
 * �������������
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractOperation implements Operation {

    protected List<Object> operatorList;

    /**
     * ��Ӳ�����
     */
    public void addOperator(Object o) {
        if (operatorList == null) {
            operatorList = new ArrayList<Object>();
        }
        operatorList.add(o);
    }

    public Object executeResult() {
    	// ����У�������У���߼�������ȥʵ��
        ValidateResult validateResult = validateOperators();
        // û��У��ͨ�����Ͳ�ִ�����������
        if (!validateResult.isSuccess()) {
            return validateResult;
        }
        // ������ִ������
        return executeReality();
    }

    /**
     * ȷ�������ǺϷ����Ժ�ִ�����㡣
     * 
     * @return
     */
    protected abstract Object executeReality();

    /**
     * У������Ƿ�Ϸ�
     * 
     * @return
     */
    protected abstract ValidateResult validateOperators();
}
