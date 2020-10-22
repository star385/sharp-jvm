package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * dload相关的指令执行器，将指定double类型变量推送至栈顶。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午9:34
 * To change this template use File | Settings | File Templates.
 */
public class DLoadExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte commandType = command.getType();
        switch (commandType) {
            case Constants.D_LOAD:
                byte[] parameter = command.getParameter();
                int index = ByteUtil.byteArray2Int(parameter);
                byte[] doubleVar = getStackFrame().getLocalVariableTableAt(index);
                getOperandStack().push(doubleVar);
                break;
            case Constants.D_LOAD_0:
                byte[] doubleVarAtIndex0 = getStackFrame().getLocalVariableTableAt(0);
                getOperandStack().push(doubleVarAtIndex0);
                break;
            case Constants.D_LOAD_1:
                byte[] doubleVarAtIndex1 = getStackFrame().getLocalVariableTableAt(1);
                getOperandStack().push(doubleVarAtIndex1);
                break;
            case Constants.D_LOAD_2:
                byte[] doubleVarAtIndex2 = getStackFrame().getLocalVariableTableAt(2);
                getOperandStack().push(doubleVarAtIndex2);
                break;
            case Constants.D_LOAD_3:
                byte[] doubleVarAtIndex3 = getStackFrame().getLocalVariableTableAt(3);
                getOperandStack().push(doubleVarAtIndex3);
                break;
        }
    }
}
