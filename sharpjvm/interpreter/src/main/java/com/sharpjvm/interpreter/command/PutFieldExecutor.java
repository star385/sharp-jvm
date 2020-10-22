package com.sharpjvm.interpreter.command;

import java.lang.reflect.Field;

import com.sharpjvm.bytecode.bean.constant.FieldRefConstant;
import com.sharpjvm.bytecode.bean.constant.NameAndTypeConstant;
import com.sharpjvm.bytecode.bean.constant.Utf8Constant;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.util.InterpreterUtil;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * 设置属性值指令。
 * 
 * User: zhuguoyin
 * Date: 13-3-19
 * Time: 下午9:38
 * To change this template use File | Settings | File Templates.
 */
public class PutFieldExecutor extends FieldOptExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        FieldRefConstant fieldRefConstant = getFieldRefConstant(commandChain);
        try {
            Field field = getField(fieldRefConstant);
            NameAndTypeConstant fieldNameAndTypeConstant = fieldRefConstant.getFieldConstant();
            Utf8Constant fieldDescriptionConstant = fieldNameAndTypeConstant.getDescriptionConstant();
            byte[] bytes = getOperandStack().pop();
            Object value = InterpreterUtil.getObjectByDescription(bytes, fieldDescriptionConstant.getValue());
            field.setAccessible(true);
            field.set(getTopObject(), value);
        } catch (Exception e) {
            throw new ExecuteException("error occurred when execute putfield static", e);
        }
    }

    private Object getTopObject() {
        byte [] reference = getOperandStack().pop();
        return RuntimeContext.getInstance().getHeap().get(reference);
    }
}
