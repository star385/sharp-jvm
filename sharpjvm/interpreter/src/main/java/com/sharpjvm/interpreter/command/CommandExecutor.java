package com.sharpjvm.interpreter.command;

import com.sharpjvm.interpreter.ExecuteException;

/**
 * ָ��ִ����
 *
 * User: zhuguoyin
 * Date: 13-3-6
 * To change this template use File | Settings | File Templates.
 */
public interface CommandExecutor {

    /**
     * ִ��ָ�����Ϊ��
     *
     * @param commandChain
     */
    void execute(CommandChain commandChain) throws ExecuteException;
}
