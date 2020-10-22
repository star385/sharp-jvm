package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * 常量相关的指令执行器。这几个都比较简单，揉在一起算了。
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 下午11:52
 * To change this template use File | Settings | File Templates.
 */
public class ConstExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte commandType = command.getType();
        switch (commandType) {
            case Constants.A_CONST_NULL:
            	byte[] bytesOfZero = ByteUtil.int2ByteArray(0);
            	getOperandStack().push(bytesOfZero);
                break;
            case Constants.I_CONST_M1:
                getOperandStack().push(ByteUtil.int2ByteArray(-1));
                break;
            case Constants.I_CONST_0:
                getOperandStack().push(ByteUtil.int2ByteArray(0));
                break;
            case Constants.I_CONST_1:
                getOperandStack().push(ByteUtil.int2ByteArray(1));
                break;
            case Constants.I_CONST_2:
                getOperandStack().push(ByteUtil.int2ByteArray(2));
                break;
            case Constants.I_CONST_3:
                getOperandStack().push(ByteUtil.int2ByteArray(3));
                break;
            case Constants.I_CONST_4:
                getOperandStack().push(ByteUtil.int2ByteArray(4));
                break;
            case Constants.I_CONST_5:
                getOperandStack().push(ByteUtil.int2ByteArray(5));
                break;
            case Constants.L_CONST_0:
                getOperandStack().push(ByteUtil.long2ByteArray(0));
                break;
            case Constants.L_CONST_1:
                getOperandStack().push(ByteUtil.long2ByteArray(1));
                break;
            case Constants.F_CONST_0:
                getOperandStack().push(ByteUtil.float2ByteArray(0f));
                break;
            case Constants.F_CONST_1:
                getOperandStack().push(ByteUtil.float2ByteArray(1f));
                break;
            case Constants.F_CONST_2:
                getOperandStack().push(ByteUtil.float2ByteArray(2f));
                break;
            case Constants.D_CONST_0:
                getOperandStack().push(ByteUtil.double2ByteArray(0));
                break;
            case Constants.D_CONST_1:
                getOperandStack().push(ByteUtil.double2ByteArray(1));
                break;
        }
    }
}
