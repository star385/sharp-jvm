package com.sharpjvm.interpreter.command;

import java.lang.reflect.Field;

import com.sharpjvm.bytecode.bean.constant.FieldRefConstant;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.util.InterpreterUtil;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * getfield÷∏¡Ó
 * 
 * User: zhuguoyin
 * Date: 13-3-19
 * Time: œ¬ŒÁ9:18
 * To change this template use File | Settings | File Templates.
 */
public class GetFieldExecutor extends FieldOptExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        FieldRefConstant fieldRefConstant = getFieldRefConstant(commandChain);
        try {
            Field field = getField(fieldRefConstant);
            field.setAccessible(true);
            Object topObject = getTopObject();
//            System.out.println(topObject);
//            System.out.println(field);
            Object o = field.get(topObject);
            byte[] reference = InterpreterUtil.getBytesByType(o, field.getType());
            getOperandStack().push(reference);
        } catch (Exception e) {
            throw new ExecuteException("error occurred when execute command getfield", e);
        }
    }

    private Object getTopObject() {
        byte [] reference = getOperandStack().pop();
        return RuntimeContext.getInstance().getHeap().get(reference);
    }
}
