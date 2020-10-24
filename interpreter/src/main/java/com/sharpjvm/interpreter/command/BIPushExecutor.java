package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * BiPushָ��ִ�����������ֽڵĳ���ֵ������ջ����
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: ����8:28
 * To change this template use File | Settings | File Templates.
 */
public class BIPushExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte[] parameter = command.getParameter();
        if (parameter == null || parameter.length != 1) {
            throw new ExecuteException("�ֽ�������bipushָ��Ĳ���Ӧ����һ�����ֽ�����");
        }
        getOperandStack().push(parameter);
    }
}
