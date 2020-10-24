package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * 自增指令。
 * 
 * User: zhuguoyin
 * Date: 13-3-17
 * Time: 上午10:01
 * To change this template use File | Settings | File Templates.
 */
public class IIncExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte [] parameter = command.getParameter();
        byte value = parameter[1];

        byte localVariableIndex = parameter[0];
        byte[] localVariableBytes = getStackFrame().getLocalVariableTableAt(localVariableIndex);
        if (localVariableBytes.length == 2) {
            short localVariable = ByteUtil.byteArray2Short(localVariableBytes);
            localVariable += value;
            byte[] resultBytes = ByteUtil.short2ByteArray(localVariable);
            getStackFrame().setLocalVariable(localVariableIndex, resultBytes);
        }
        if (localVariableBytes.length == 4) {
            int localVariable = ByteUtil.byteArray2Int(localVariableBytes);
            localVariable += value;
            byte[] resultBytes = ByteUtil.int2ByteArray(localVariable);
            getStackFrame().setLocalVariable(localVariableIndex, resultBytes);
        }
        if (localVariableBytes.length == 8) {
            long localVariable = ByteUtil.byteArray2Long(localVariableBytes);
            localVariable += value;
            byte[] resultBytes = ByteUtil.long2ByteArray(localVariable);
            getStackFrame().setLocalVariable(localVariableIndex, resultBytes);
        }
    }
}
