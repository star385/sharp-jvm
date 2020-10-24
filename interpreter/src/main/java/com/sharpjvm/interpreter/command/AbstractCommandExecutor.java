package com.sharpjvm.interpreter.command;

import com.sharpjvm.memory.model.stack.ByteStack;
import com.sharpjvm.memory.model.stack.StackFrame;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * �����ָ��ִ����
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: ����11:43
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractCommandExecutor implements CommandExecutor {

    protected ByteStack getOperandStack() {
        long threadId = Thread.currentThread().getId();
        return RuntimeContext.getInstance().getStack().getStackFrame(threadId).getOperandStack();
    }

    protected StackFrame getStackFrame() {
        long threadId = Thread.currentThread().getId();
        return RuntimeContext.getInstance().getStack().getStackFrame(threadId);
    }
}
