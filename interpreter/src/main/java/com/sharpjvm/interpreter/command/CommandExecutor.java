package com.sharpjvm.interpreter.command;

import com.sharpjvm.interpreter.ExecuteException;

/**
 * 指令执行器
 *
 * User: zhuguoyin
 * Date: 13-3-6
 * To change this template use File | Settings | File Templates.
 */
public interface CommandExecutor {

    /**
     * 执行指令的行为。
     *
     * @param commandChain
     */
    void execute(CommandChain commandChain) throws ExecuteException;
}
