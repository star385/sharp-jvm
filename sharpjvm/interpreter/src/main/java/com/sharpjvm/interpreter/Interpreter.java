package com.sharpjvm.interpreter;

import com.sharpjvm.bytecode.bean.ClassInfo;

/**
 * �������ӿ�
 *
 * User: zhuguoyin
 * Date: 13-2-8
 * Time: ����4:32
 * To change this template use File | Settings | File Templates.
 */
public interface Interpreter {

    /**
     * �����ֽ���
     *
     * @param classInfo
     */
    void interpret(ClassInfo classInfo) throws ExecuteException;
}
