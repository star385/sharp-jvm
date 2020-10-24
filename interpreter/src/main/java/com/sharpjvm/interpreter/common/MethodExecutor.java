package com.sharpjvm.interpreter.common;

import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.memory.model.stack.StackFrame;

/**
 * 执行某个方法。
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */
public interface MethodExecutor {

    /**
     * 执行一个方法。
     *
     */
    public StackFrame execute(MethodExecutorParameter parameter) throws ExecuteException;
}
