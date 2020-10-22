package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * sipush指令执行器，将一个short推送至栈顶。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午9:29
 * To change this template use File | Settings | File Templates.
 */
public class SIPushExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte [] shortVar = command.getParameter();
        if (shortVar == null || shortVar.length != 2) {
            throw new ExecuteException("字节码有误！bipush指令的参数应该是一个单字节数！");
        }
        getOperandStack().push(shortVar);
    }
}
