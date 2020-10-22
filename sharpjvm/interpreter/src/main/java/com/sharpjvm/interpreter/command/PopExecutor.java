package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * Õ»¶¥pop³öÀ´
 *
 * User: zhuguoyin
 * Date: 13-3-16
 * Time: ÏÂÎç4:29
 * To change this template use File | Settings | File Templates.
 */
public class PopExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        switch (command.getType()) {
            case Constants.POP:
                byte [] stackTopValue = getOperandStack().pop();
                if (stackTopValue == null || stackTopValue.length != 4) {
                    throw new ExecuteException("only can pop 4 bytes");
                }
                break;
            case Constants.POP2:
                byte [] stackTopValue8Bytes = getOperandStack().pop8Bytes();
                if (stackTopValue8Bytes == null || stackTopValue8Bytes.length != 8) {
                    throw new ExecuteException("only can pop 8 bytes");
                }
                break;
        }
    }
}
