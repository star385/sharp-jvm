package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * dstore_{i}指令执行器，将栈顶int类型存入第{i}个本地变量
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午9:09
 * To change this template use File | Settings | File Templates.
 */
public class DStoreExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte [] doubleVar = getOperandStack().pop8Bytes();
        if (doubleVar == null || doubleVar.length != 8) {
            throw new ExecuteException("length of double must be 8!");
        }
        byte commandType = command.getType();
        switch (commandType) {
            case Constants.D_STORE:
                byte[] parameter = command.getParameter();
                int index = ByteUtil.byteArray2Int(parameter);
                getStackFrame().setLocalVariable(index, doubleVar);
            case Constants.D_STORE_0:
                getStackFrame().setLocalVariable(0, doubleVar);
                break;
            case Constants.D_STORE_1:
                getStackFrame().setLocalVariable(1, doubleVar);
                break;
            case Constants.D_STORE_2:
                getStackFrame().setLocalVariable(2, doubleVar);
                break;
            case Constants.D_STORE_3:
                getStackFrame().setLocalVariable(3, doubleVar);
                break;
        }
    }
}
