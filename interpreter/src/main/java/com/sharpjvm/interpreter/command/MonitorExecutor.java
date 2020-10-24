package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * ͬ��ָ�Ŀǰû��ʵ������
 *
 * User: zhuguoyin
 * Date: 13-3-25
 * Time: ����10:13
 * To change this template use File | Settings | File Templates.
 */
public class MonitorExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        switch (command.getType()) {
            case Constants.MONITOR_ENTER:
            case Constants.MONITOR_EXIT:
            default:
                getOperandStack().pop();
        }
    }
}
