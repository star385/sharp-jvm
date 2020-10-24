package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * fstore_{i}指令执行器，将栈顶float类型存入第{i}个本地变量
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午9:09
 * To change this template use File | Settings | File Templates.
 */
public class FStoreExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte [] floatVar = getOperandStack().pop();
        byte commandType = command.getType();
        switch (commandType) {
            case Constants.F_STORE:
                byte[] parameter = command.getParameter();
                int index = ByteUtil.byteArray2Int(parameter);
                getStackFrame().setLocalVariable(index, floatVar);
            case Constants.F_STORE_0:
                getStackFrame().setLocalVariable(0, floatVar);
                break;
            case Constants.F_STORE_1:
                getStackFrame().setLocalVariable(1, floatVar);
                break;
            case Constants.F_STORE_2:
                getStackFrame().setLocalVariable(2, floatVar);
                break;
            case Constants.F_STORE_3:
                getStackFrame().setLocalVariable(3, floatVar);
                break;
        }
    }
}
