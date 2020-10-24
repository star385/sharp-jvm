package com.sharpjvm.interpreter.command;

import java.lang.reflect.Field;

import com.sharpjvm.bytecode.bean.constant.FieldRefConstant;
import com.sharpjvm.bytecode.bean.constant.NameAndTypeConstant;
import com.sharpjvm.bytecode.bean.constant.Utf8Constant;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.util.InterpreterUtil;

/**
 * …Ë÷√æ≤Ã¨ Ù–‘÷µ
 * 
 * @author zhuguoyin
 *
 */
public class PutStaticExecutor extends FieldOptExecutor {

	public void execute(CommandChain commandChain) throws ExecuteException {
        FieldRefConstant fieldRefConstant = getFieldRefConstant(commandChain);
        try {
            Field field = getField(fieldRefConstant);
            NameAndTypeConstant fieldNameAndTypeConstant = fieldRefConstant.getFieldConstant();
            Utf8Constant fieldDescriptionConstant = fieldNameAndTypeConstant.getDescriptionConstant();
            byte[] bytes = getOperandStack().pop();
            Object value = InterpreterUtil.getObjectByDescription(bytes, fieldDescriptionConstant.getValue());
            field.set(null, value);
        } catch (Exception e) {
            throw new ExecuteException("error occurred when execute getCommandExecutor static", e);
        }
	}


}
