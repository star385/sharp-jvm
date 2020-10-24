package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * astore_{i}指令执行器，将栈顶对象类型存入index为{i}的局部变量
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午9:09
 * To change this template use File | Settings | File Templates.
 */
public class AStoreExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte [] floatVar = getOperandStack().pop();
        byte commandType = command.getType();
        switch (commandType) {
            case Constants.A_STORE:
                byte[] parameter = command.getParameter();
                int index = ByteUtil.byteArray2Int(parameter);
                getStackFrame().setLocalVariable(index, floatVar);
                break;
            case Constants.A_STORE_0:
                getStackFrame().setLocalVariable(0, floatVar);
                break;
            case Constants.A_STORE_1:
                getStackFrame().setLocalVariable(1, floatVar);
                break;
            case Constants.A_STORE_2:
                getStackFrame().setLocalVariable(2, floatVar);
                break;
            case Constants.A_STORE_3:
                getStackFrame().setLocalVariable(3, floatVar);
                break;
        }
    }
}
