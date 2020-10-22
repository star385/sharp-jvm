package com.sharpjvm.interpreter.command;

import java.lang.reflect.Array;

import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * ���鳤��ָ��
 * 
 * User: zhuguoyin
 * Date: 13-3-20
 * Time: ����10:59
 * To change this template use File | Settings | File Templates.
 */
public class ArrayLengthExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        byte[] reference = getOperandStack().pop();
        Object instance = RuntimeContext.getInstance().getHeap().get(reference);
        // �÷����ȡ���鳤��
        int length = Array.getLength(instance);
        byte[] lengthBytes = ByteUtil.int2ByteArray(length);
        getOperandStack().push(lengthBytes);
    }
}
