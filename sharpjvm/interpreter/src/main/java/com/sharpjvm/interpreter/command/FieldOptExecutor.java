package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.bean.constant.*;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

import java.lang.reflect.Field;

/**
 * �������Բ���ָ��Ļ��ࡣ
 * 
 * @author zhuguoyin
 *
 */
public abstract class FieldOptExecutor extends AbstractCommandExecutor {

	/**
	 * ����FieldRefConstant��ȡ������Ϣ
	 * 
	 * @param fieldRefConstant
	 * @return
	 * @throws Exception
	 */
	protected Field getField(FieldRefConstant fieldRefConstant) throws Exception {
		NameAndTypeConstant fieldNameAndTypeConstant = fieldRefConstant
				.getFieldConstant();
		if (fieldNameAndTypeConstant == null) {
			throw new ExecuteException("ִ��GetStaticʱ����������������ָ���˴����λ��");
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
	 * ������������Ϣ��ȡFieldRefConstant
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
			throw new ExecuteException("ִ��GetStaticʱ����������������ָ���˴����λ��");
		}
		FieldRefConstant fieldRefConstant = (FieldRefConstant) constant;
		return fieldRefConstant;
	}
}
