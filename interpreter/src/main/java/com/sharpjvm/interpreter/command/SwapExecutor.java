package com.sharpjvm.interpreter.command;

import com.sharpjvm.interpreter.ExecuteException;

/**
 * swap command, exchange the 2 top of stack
 *
 * User: zhuguoyin
 * Date: 13-3-16
 * Time: ÏÂÎç5:02
 * To change this template use File | Settings | File Templates.
 */
public class SwapExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        byte[] topBytes = getOperandStack().pop();
        if (topBytes == null || topBytes.length != 4) {
            throw new ExecuteException("swap can only swap 4 bytes");
        }
        byte[] nextTopBytes = getOperandStack().pop();
        if (nextTopBytes == null || nextTopBytes.length != 4) {
            throw new ExecuteException("swap can only swap 4 bytes");
        }
        getOperandStack().push(topBytes);
        getOperandStack().push(nextTopBytes);
    }
}
