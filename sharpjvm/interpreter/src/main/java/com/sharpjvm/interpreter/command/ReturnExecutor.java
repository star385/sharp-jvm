package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * 返回指令集合。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午11:00
 * To change this template use File | Settings | File Templates.
 */
public class ReturnExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte commandType = command.getType();
        switch (commandType) {
            case Constants.RETURN:
                getStackFrame().setReturnAddress(null);
                break;
            case Constants.I_RETURN:
                getStackFrame().setReturnAddress(getOperandStack().pop());
                break;
            case Constants.L_RETURN:
                getStackFrame().setReturnAddress(getOperandStack().pop8Bytes());
                break;
            case Constants.F_RETURN:
                getStackFrame().setReturnAddress(getOperandStack().pop());
                break;
            case Constants.D_RETURN:
                getStackFrame().setReturnAddress(getOperandStack().pop8Bytes());
                break;
            case Constants.A_RETURN:
                getStackFrame().setReturnAddress(getOperandStack().pop());
        }
        commandChain.setEnd(true);
    }
}
