package com.sharpjvm.interpreter.common;

import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.memory.model.stack.StackFrame;

/**
 * ִ��ĳ��������
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: ����2:10
 * To change this template use File | Settings | File Templates.
 */
public interface MethodExecutor {

    /**
     * ִ��һ��������
     *
     */
    public StackFrame execute(MethodExecutorParameter parameter) throws ExecuteException;
}
