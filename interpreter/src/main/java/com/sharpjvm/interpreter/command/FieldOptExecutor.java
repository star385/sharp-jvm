package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.bean.constant.*;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

import java.lang.reflect.Field;

/**
 * 所有属性操作指令的基类。
 * 
 * @author zhuguoyin
 *
 */
public abstract class FieldOptExecutor extends AbstractCommandExecutor {

	/**
	 * 根据FieldRefConstant获取属性信息
	 * 
	 * @param fieldRefConstant
	 * @return
	 * @throws Exception
	 */
	protected Field getField(FieldRefConstant fieldRefConstant) throws Exception {
		NameAndTypeConstant fieldNameAndTypeConstant = fieldRefConstant
				.getFieldConstant();
		if (fieldNameAndTypeConstant == null) {
			throw new ExecuteException("执行GetStatic时出错，参数常量索引指向了错误的位置");
		}
		Utf8Constant fieldNameConstant = fieldNameAndTypeConstant
				.getNameConstant();

		ClassConstant classConstant = fieldRefConstant.getClassConstant();
		String className = classConstant.getNameConstant().getValue();
		className = className.replace("/", ".");
		Class<?> clazz = Class.forName(className);
		String fieldName = fieldNameConstant.getValue();

        Field field;
        Class<?> loopClass = clazz;
        do {
            try {
                field = loopClass.getDeclaredField(fieldName);
                return field;
            } catch (NoSuchFieldException e) {
                loopClass = loopClass.getSuperclass();
            }
        } while (loopClass != Object.class);
        throw new ExecuteException("didn't find the field which named " + fieldName + " in class " + className);
    }

	/**
	 * 根据上下文信息获取FieldRefConstant
	 * 
	 * @param commandChain
	 * @return
	 * @throws ExecuteException
	 */
	protected FieldRefConstant getFieldRefConstant(CommandChain commandChain)
			throws ExecuteException {
		Command command = commandChain.getCurrentCommand();
		ClassInfo executeClassInfo = commandChain.getExecuteClassInfo();
		byte[] parameter = command.getParameter();
		short staticMethodIndex = ByteUtil.byteArray2Short(parameter);
		Constant constant = executeClassInfo.getConstantList().getConstant(
				staticMethodIndex);
		if (!(constant instanceof FieldRefConstant)) {
			throw new ExecuteException("执行GetStatic时出错，参数常量索引指向了错误的位置");
		}
		FieldRefConstant fieldRefConstant = (FieldRefConstant) constant;
		return fieldRefConstant;
	}
}
