package com.sharpjvm.interpreter.command;

import java.lang.reflect.Field;

import com.sharpjvm.bytecode.bean.constant.FieldRefConstant;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.util.InterpreterUtil;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * getstaticÖ´ÐÐÆ÷
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: ÏÂÎç1:56
 * To change this template use File | Settings | File Templates.
 */
public class GetStaticExecutor extends FieldOptExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        FieldRefConstant fieldRefConstant = getFieldRefConstant(commandChain);
        try {
            Field field = getField(fieldRefConstant);
            field.setAccessible(true);
            Object o = field.get(null);
			if (!field.getType().isPrimitive()) {
				byte[] reference = RuntimeContext.getInstance().getHeap()
						.getReference(o);
				getOperandStack().push(reference);
			} else {
                byte[] bytes = InterpreterUtil.getBytesByType(o, field.getType());
                getOperandStack().push(bytes);
            }
            
        } catch (Exception e) {
            throw new ExecuteException("error occurred when execute command get static", e);
        }
    }
}
