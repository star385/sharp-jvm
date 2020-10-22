package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * istore_{i}指令执行器，将栈顶int类型存入第{i}个本地变量
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午9:09
 * To change this template use File | Settings | File Templates.
 */
public class IStoreExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte [] intVar = getOperandStack().pop();
        byte commandType = command.getType();
        switch (commandType) {
            case Constants.ISTORE:
                byte[] parameter = command.getParameter();
                int index = ByteUtil.byteArray2Int(parameter);
                getStackFrame().setLocalVariable(index, intVar);
                break;
            case Constants.ISTORE_0:
                getStackFrame().setLocalVariable(0, intVar);
                break;
            case Constants.ISTORE_1:
                getStackFrame().setLocalVariable(1, intVar);
                break;
            case Constants.ISTORE_2:
                getStackFrame().setLocalVariable(2, intVar);
                break;
            case Constants.ISTORE_3:
                getStackFrame().setLocalVariable(3, intVar);
                break;
        }
    }
}
