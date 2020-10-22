package com.sharpjvm.interpreter.command;

import com.sharpjvm.interpreter.ExecuteException;

/**
 * 不做任何事，不知道设计这个指令做啥，难道就是为了等待吗？
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 下午11:30
 * To change this template use File | Settings | File Templates.
 */
public class NopExecutor implements CommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {

    }
}
