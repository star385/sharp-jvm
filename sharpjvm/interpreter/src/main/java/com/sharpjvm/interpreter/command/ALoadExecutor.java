package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * aload��ص�ָ��ִ��������ָ�����ñ���������ջ����
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: ����9:34
 * To change this template use File | Settings | File Templates.
 */
public class ALoadExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte commandType = command.getType();
        switch (commandType) {
        	// indexΪ�����ľֲ��������͵�ջ��
            case Constants.A_LOAD:
                byte[] parameter = command.getParameter();
                int index = ByteUtil.byteArray2Int(parameter);
                byte[] intVar = getStackFrame().getLocalVariableTableAt(index);
                getOperandStack().push(intVar);
                break;
             // indexΪ0�ľֲ��������͵�ջ��
            case Constants.A_LOAD_0:
                byte[] intVarAtIndex0 = getStackFrame().getLocalVariableTableAt(0);
                getOperandStack().push(intVarAtIndex0);
                break;
             // indexΪ1�ľֲ��������͵�ջ��
            case Constants.A_LOAD_1:
                byte[] intVarAtIndex1 = getStackFrame().getLocalVariableTableAt(1);
                getOperandStack().push(intVarAtIndex1);
                break;
             // indexΪ2�ľֲ��������͵�ջ��
            case Constants.A_LOAD_2:
                byte[] intVarAtIndex2 = getStackFrame().getLocalVariableTableAt(2);
                getOperandStack().push(intVarAtIndex2);
                break;
             // indexΪ3�ľֲ��������͵�ջ��
            case Constants.A_LOAD_3:
                byte[] intVarAtIndex3 = getStackFrame().getLocalVariableTableAt(3);
                getOperandStack().push(intVarAtIndex3);
                break;
        }
    }
}
