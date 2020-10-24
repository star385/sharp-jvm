package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * 取负操作。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
public class NegExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        switch (command.getType()) {
            case Constants.I_NEG:
                byte[] addOperator1Array = getOperandStack().pop();
                int addOperator1Int = ByteUtil.byteArray2Int(addOperator1Array);
                int result = -addOperator1Int;
                byte[] resultBytes = ByteUtil.int2ByteArray(result);
                getOperandStack().push(resultBytes);
                break;
            case Constants.L_NEG:
                byte[] longAddOperator1Array = getOperandStack().pop();
                long longAddOperator1 = ByteUtil.byteArray2Long(longAddOperator1Array);
                long longResult = -longAddOperator1;
                byte[] longResultBytes = ByteUtil.long2ByteArray(longResult);
                getOperandStack().push(longResultBytes);
                break;
            case Constants.F_NEG:
                byte[] floatAddOperator1Array = getOperandStack().pop();
                float floatAddOperator1 = ByteUtil.byteArray2Float(floatAddOperator1Array);
                float floatResult = -floatAddOperator1;
                byte[] floatResultBytes = ByteUtil.float2ByteArray(floatResult);
                getOperandStack().push(floatResultBytes);
                break;
            case Constants.D_NEG:
                byte[] doubleAddOperator1Array = getOperandStack().pop();
                double doubleAddOperator1 = ByteUtil.byteArray2Float(doubleAddOperator1Array);
                double doubleResult = -doubleAddOperator1;
                byte[] doubleResultBytes = ByteUtil.double2ByteArray(doubleResult);
                getOperandStack().push(doubleResultBytes);
                break;
        }

    }
}
