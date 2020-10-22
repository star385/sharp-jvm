package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * dup related executor
 *
 * User: zhuguoyin
 * Date: 13-3-16
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
public class DupExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        switch (command.getType()) {
            case Constants.DUP:
                byte[] topBytes = getOperandStack().get();
                if (topBytes == null || topBytes.length != 4) {
                    throw new ExecuteException("dup command, can only dup 4 bytes!");
                }
                // 之前以为需要克隆一下，后来发现不需要，用的系统的数组拷贝的方法拷贝了一份，实质push已经克隆了
                getOperandStack().push(topBytes);
                break;
            case Constants.DUP2:
                byte[] topBytes8Bytes = getOperandStack().get8Bytes();
                if (topBytes8Bytes == null || topBytes8Bytes.length != 8) {
                    throw new ExecuteException("dup2 command, can only dup 8 bytes!");
                }
                // 之前以为需要克隆一下，后来发现不需要，用的系统的数组拷贝的方法拷贝了一份，实质push已经克隆了
                getOperandStack().push(topBytes8Bytes);
                break;
            case Constants.DUP_X1:
//                byte[] dupX1TopBytes = getOperandStack().get();
//                if (dupX1TopBytes == null || dupX1TopBytes.length != 4) {
//                    throw new ExecuteException("dup command, can only dup 4 bytes!");
//                }
//                // 之前以为需要克隆一下，后来发现不需要，用的系统的数组拷贝的方法拷贝了一份，实质push已经克隆了
//                getOperandStack().push(dupX1TopBytes);
                byte[] dupX1TopBytes = getOperandStack().pop();
                if (dupX1TopBytes == null || dupX1TopBytes.length != 4) {
                    throw new ExecuteException("dup command, can only dup 4 bytes!");
                }
                byte[] dupX1NextTopBytes = getOperandStack().pop();
                if (dupX1NextTopBytes == null || dupX1NextTopBytes.length != 4) {
                    throw new ExecuteException("dup command, can only dup 4 bytes!");
                }
                // 之前以为需要克隆一下，后来发现不需要，用的系统的数组拷贝的方法拷贝了一份，实质push已经克隆了
                getOperandStack().push(dupX1TopBytes);
//                getOperandStack().push(dupX1TopBytes);
                getOperandStack().push(dupX1NextTopBytes);
                getOperandStack().push(dupX1TopBytes);
                break;
            case Constants.DUP_X2:
                // TODO
                break;
            case Constants.DUP2_X1:
                // TODO
                break;
            case Constants.DUP2_X2:
                // TODO
                break;
        }
    }
}
