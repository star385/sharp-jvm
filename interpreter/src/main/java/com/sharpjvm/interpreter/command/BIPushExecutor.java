package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * BiPush指令执行器。将单字节的常量值推送至栈顶。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午8:28
 * To change this template use File | Settings | File Templates.
 */
public class BIPushExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte[] parameter = command.getParameter();
        if (parameter == null || parameter.length != 1) {
            throw new ExecuteException("字节码有误！bipush指令的参数应该是一个单字节数！");
        }
        getOperandStack().push(parameter);
    }
}
