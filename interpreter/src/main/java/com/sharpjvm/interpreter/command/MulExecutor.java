package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * 整数的乘操作。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
public class MulExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        switch (command.getType()) {
            case Constants.I_MUL:
                byte[] addOperator1Array = getOperandStack().pop();
                int addOperator1Int = ByteUtil.byteArray2Int(addOperator1Array);
                byte[] addOperator2Array = getOperandStack().pop();
                int addOperator2Int = ByteUtil.byteArray2Int(addOperator2Array);
                int result = addOperator1Int * addOperator2Int;
                byte[] resultBytes = ByteUtil.int2ByteArray(result);
                getOperandStack().push(resultBytes);
                break;
            case Constants.L_MUL:
                byte[] longAddOperator1Array = getOperandStack().pop8Bytes();
                long longAddOperator1 = ByteUtil.byteArray2Long(longAddOperator1Array);
                byte[] longAddOperator2Array = getOperandStack().pop8Bytes();
                long longAddOperator2 = ByteUtil.byteArray2Long(longAddOperator2Array);
                long longResult = longAddOperator1 * longAddOperator2;
                byte[] longResultBytes = ByteUtil.long2ByteArray(longResult);
                getOperandStack().push(longResultBytes);
                break;
            case Constants.F_MUL:
                byte[] floatAddOperator1Array = getOperandStack().pop();
                float floatAddOperator1 = ByteUtil.byteArray2Float(floatAddOperator1Array);
                byte[] floatAddOperator2Array = getOperandStack().pop();
                float floatAddOperator2 = ByteUtil.byteArray2Long(floatAddOperator2Array);
                float floatResult = floatAddOperator1 * floatAddOperator2;
                byte[] floatResultBytes = ByteUtil.float2ByteArray(floatResult);
                getOperandStack().push(floatResultBytes);
                break;
            case Constants.D_MUL:
                byte[] doubleAddOperator1Array = getOperandStack().pop8Bytes();
                double doubleAddOperator1 = ByteUtil.byteArray2Float(doubleAddOperator1Array);
                byte[] doubleAddOperator2Array = getOperandStack().pop8Bytes();
                double doubleAddOperator2 = ByteUtil.byteArray2Long(doubleAddOperator2Array);
                double doubleResult = doubleAddOperator1 * doubleAddOperator2;
                byte[] doubleResultBytes = ByteUtil.double2ByteArray(doubleResult);
                getOperandStack().push(doubleResultBytes);
                break;
        }

    }
}
