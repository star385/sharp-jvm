package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * lstore_{i}指令执行器，将栈顶long类型存入第{i}个本地变量
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午9:09
 * To change this template use File | Settings | File Templates.
 */
public class LStoreExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte [] longVar = getOperandStack().pop8Bytes();
        byte commandType = command.getType();
        switch (commandType) {
            case Constants.L_STORE:
                byte[] parameter = command.getParameter();
                int index = ByteUtil.byteArray2Int(parameter);
                getStackFrame().setLocalVariable(index, longVar);
                break;
            case Constants.L_STORE_0:
                getStackFrame().setLocalVariable(0, longVar);
                break;
            case Constants.L_STORE_1:
                getStackFrame().setLocalVariable(1, longVar);
                break;
            case Constants.L_STORE_2:
                getStackFrame().setLocalVariable(2, longVar);
                break;
            case Constants.L_STORE_3:
                getStackFrame().setLocalVariable(3, longVar);
                break;
        }
    }
}
