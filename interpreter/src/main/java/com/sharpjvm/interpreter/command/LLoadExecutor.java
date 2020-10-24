package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * lload相关的指令执行器，将指定long类型变量推送至栈顶。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午9:34
 * To change this template use File | Settings | File Templates.
 */
public class LLoadExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte commandType = command.getType();
        switch (commandType) {
            case Constants.L_LOAD:
                byte[] parameter = command.getParameter();
                int index = ByteUtil.byteArray2Int(parameter);
                byte[] longVar = getStackFrame().getLocalVariableTableAt(index);
                getOperandStack().push(longVar);
                break;
            case Constants.L_LOAD_0:
                byte[] longVarAtIndex0 = getStackFrame().getLocalVariableTableAt(0);
                getOperandStack().push(longVarAtIndex0);
                break;
            case Constants.L_LOAD_1:
                byte[] longVarAtIndex1 = getStackFrame().getLocalVariableTableAt(1);
                getOperandStack().push(longVarAtIndex1);
                break;
            case Constants.L_LOAD_2:
                byte[] longVarAtIndex2 = getStackFrame().getLocalVariableTableAt(2);
                getOperandStack().push(longVarAtIndex2);
                break;
            case Constants.L_LOAD_3:
                byte[] longVarAtIndex3 = getStackFrame().getLocalVariableTableAt(3);
                getOperandStack().push(longVarAtIndex3);
                break;
        }
    }
}
