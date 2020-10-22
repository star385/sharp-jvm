package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.bean.constant.Constant;
import com.sharpjvm.bytecode.bean.constant.DoubleConstant;
import com.sharpjvm.bytecode.bean.constant.FloatConstant;
import com.sharpjvm.bytecode.bean.constant.IntegerConstant;
import com.sharpjvm.bytecode.bean.constant.LongConstant;
import com.sharpjvm.bytecode.bean.constant.StringConstant;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * ldc指令执行器。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午8:47
 * To change this template use File | Settings | File Templates.
 */
public class LDCExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        ClassInfo classInfo = commandChain.getExecuteClassInfo();
        byte[] parameter = command.getParameter();
        switch (command.getType()) {
        case Constant.LDC:
            short parameterIndex = ByteUtil.byteArray2Short(parameter);
            Constant ldcConstant = classInfo.getConstantList().getConstant(parameterIndex);
            if (ldcConstant instanceof IntegerConstant) {
            	IntegerConstant integerConstant = (IntegerConstant) ldcConstant;
            	int value = integerConstant.getValue();
            	byte[] valueBytes = ByteUtil.int2ByteArray(value);
            	getOperandStack().push(valueBytes);
            	break;
            } else if (ldcConstant instanceof FloatConstant) {
            	FloatConstant floatConstant = (FloatConstant) ldcConstant;
            	float value = floatConstant.getValue();
            	byte[] valueBytes = ByteUtil.float2ByteArray(value);
            	getOperandStack().push(valueBytes);
            	break;
            } else if (ldcConstant instanceof StringConstant) {
            	StringConstant stringConstant = (StringConstant) ldcConstant;
            	String parameterValue = stringConstant.getNameConstant().getValue();
                byte[] reference = RuntimeContext.getInstance().getHeap().getReference(parameterValue);
                getOperandStack().push(reference);
                break;
            }
            throw new ExecuteException("ldc命令 的参数不正确，请检查字节码!");
        case Constants.LDC_W:
        	short ldcwParameterIndex = ByteUtil.byteArray2Short(parameter);
            Constant ldcwConstant = classInfo.getConstantList().getConstant(ldcwParameterIndex);
            if (ldcwConstant instanceof IntegerConstant) {
            	IntegerConstant integerConstant = (IntegerConstant) ldcwConstant;
            	int value = integerConstant.getValue();
            	byte[] valueBytes = ByteUtil.int2ByteArray(value);
            	byte[] exValueBytes = new byte[8];
            	for (int i = 0; i < 4; i++) {
            		exValueBytes[i] = 0;
            	}
            	System.arraycopy(valueBytes, 0, exValueBytes, 4, 4);
            	getOperandStack().push(exValueBytes);
            	break;
            } else if (ldcwConstant instanceof FloatConstant) {
            	FloatConstant floatConstant = (FloatConstant) ldcwConstant;
            	float value = floatConstant.getValue();
            	byte[] valueBytes = ByteUtil.float2ByteArray(value);
            	byte[] exValueBytes = new byte[8];
            	for (int i = 0; i < 4; i++) {
            		exValueBytes[i] = 0;
            	}
            	System.arraycopy(valueBytes, 0, exValueBytes, 4, 4);
            	getOperandStack().push(exValueBytes);
            	break;
            } else if (ldcwConstant instanceof StringConstant) {
            	StringConstant stringConstant = (StringConstant) ldcwConstant;
            	String parameterValue = stringConstant.getNameConstant().getValue();
                byte[] reference = RuntimeContext.getInstance().getHeap().getReference(parameterValue);
                byte[] exValueBytes = new byte[8];
            	for (int i = 0; i < 4; i++) {
            		exValueBytes[i] = 0;
            	}
            	System.arraycopy(reference, 0, exValueBytes, 4, 4);
                getOperandStack().push(reference);
                break;
            }
            throw new ExecuteException("ldc_w命令 的参数不正确，请检查字节码!");
        case Constants.LDC2_W:
        	short ldc2WParameterIndex = ByteUtil.byteArray2Short(parameter);
            Constant ldc2wConstant = classInfo.getConstantList().getConstant(ldc2WParameterIndex);
            if (ldc2wConstant instanceof LongConstant) {
            	LongConstant longConstant = (LongConstant) ldc2wConstant;
            	long value = longConstant.getValue();
            	byte[] valueBytes = ByteUtil.long2ByteArray(value);
            	getOperandStack().push(valueBytes);
            	break;
            } else if (ldc2wConstant instanceof DoubleConstant) {
            	DoubleConstant doubleConstant = (DoubleConstant) ldc2wConstant;
            	double value = doubleConstant.getValue();
            	byte[] valueBytes = ByteUtil.double2ByteArray(value);
            	getOperandStack().push(valueBytes);
            	break;
            }
            throw new ExecuteException("ldc2_w命令 的参数不正确，请检查字节码!");
        }
        
    }
}
