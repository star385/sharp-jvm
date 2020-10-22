package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * iload��ص�ָ��ִ��������ָ��int���ͱ���������ջ����
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: ����9:34
 * To change this template use File | Settings | File Templates.
 */
public class ILoadExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte commandType = command.getType();
        switch (commandType) {
            case Constants.I_LOAD:
                byte[] parameter = command.getParameter();
//                System.out.println("iloadָ���������Ϊ��" + parameter.length);
                int index = ByteUtil.byteArray2Int(parameter);
                byte[] intVar = getStackFrame().getLocalVariableTableAt(index);
                getOperandStack().push(intVar);
                break;
            case Constants.I_LOAD_0:
                byte[] intVarAtIndex0 = getStackFrame().getLocalVariableTableAt(0);
                getOperandStack().push(intVarAtIndex0);
                break;
            case Constants.I_LOAD_1:
                byte[] intVarAtIndex1 = getStackFrame().getLocalVariableTableAt(1);
                getOperandStack().push(intVarAtIndex1);
                break;
            case Constants.I_LOAD_2:
                byte[] intVarAtIndex2 = getStackFrame().getLocalVariableTableAt(2);
                getOperandStack().push(intVarAtIndex2);
                break;
            case Constants.I_LOAD_3:
                byte[] intVarAtIndex3 = getStackFrame().getLocalVariableTableAt(3);
                getOperandStack().push(intVarAtIndex3);
                break;
        }
    }
}
