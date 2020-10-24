package com.sharpjvm.interpreter.command;

import java.lang.reflect.Array;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * 创建基本类型的数组指令
 * 
 * User: zhuguoyin
 * Date: 13-3-20
 * Time: 下午10:27
 * To change this template use File | Settings | File Templates.
 */
public class NewArrayExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte [] parameter = command.getParameter();
        if (parameter == null || parameter.length != 1) {
            throw new ExecuteException("new array parameter must be 1");
        }
        byte typeParameter = parameter[0];
        Class<?> type = getType(typeParameter);
        byte[] lengthBytes = getOperandStack().pop();
        int length = ByteUtil.byteArray2Int(lengthBytes);
        Object instance = Array.newInstance(type, length);
        byte[] reference = RuntimeContext.getInstance().getHeap().getReference(instance);
        getOperandStack().push(reference);
    }

    // just experience, don't know why, who knows?
    private Class<?> getType(byte typeParameter) {
        if (typeParameter == 0xa) {
            return int.class;
        }
        if (typeParameter == 0x5) {
            return char.class;
        }
        if (typeParameter == 0x6) {
            return float.class;
        }
        if (typeParameter == 0xb) {
            return long.class;
        }
        if (typeParameter == 0x9) {
            return short.class;
        }
        if (typeParameter == 0x4) {
            return boolean.class;
        }
        if (typeParameter == 0x8) {
            return byte.class;
        }
        if (typeParameter == 0x7) {
            return double.class;
        }
        return null;
    }
}
