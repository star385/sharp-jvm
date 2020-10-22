package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * 无条件跳转指令。
 * 
 * User: zhuguoyin
 * Date: 13-3-17
 * Time: 下午7:47
 * To change this template use File | Settings | File Templates.
 */
public class GotoExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte[] ifEqGotoLineBytes = command.getParameter();
        if (ifEqGotoLineBytes == null || ifEqGotoLineBytes.length != Constants.SHORT_BYTE_COUNT) {
            throw new ExecuteException("command:ifeq, goto line must be " + Constants.SHORT_BYTE_COUNT + " bytes");
        }
        short ifEqGotoLine = ByteUtil.byteArray2Short(ifEqGotoLineBytes);
        commandChain.gotoCommand(ifEqGotoLine + command.getLineNumber());// ???
    }
}
