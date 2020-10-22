package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * sipushָ��ִ��������һ��short������ջ����
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: ����9:29
 * To change this template use File | Settings | File Templates.
 */
public class SIPushExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte [] shortVar = command.getParameter();
        if (shortVar == null || shortVar.length != 2) {
            throw new ExecuteException("�ֽ�������bipushָ��Ĳ���Ӧ����һ�����ֽ�����");
        }
        getOperandStack().push(shortVar);
    }
}
