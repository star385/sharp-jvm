package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * 减法指令集合。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
public class SubExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        switch (command.getType()) {
            case Constants.I_SUB:
                byte[] addOperator1Array = getOperandStack().pop();
                int addOperator1Int = ByteUtil.byteArray2Int(addOperator1Array);
                byte[] addOperator2Array = getOperandStack().pop();
                int addOperator2Int = ByteUtil.byteArray2Int(addOperator2Array);
                int result = addOperator2Int - addOperator1Int;
                byte[] resultBytes = ByteUtil.int2ByteArray(result);
                getOperandStack().push(resultBytes);
                break;
            case Constants.L_SUB:
                byte[] longAddOperator1Array = getOperandStack().pop8Bytes();
                long longAddOperator1 = ByteUtil.byteArray2Long(longAddOperator1Array);
                byte[] longAddOperator2Array = getOperandStack().pop8Bytes();
                long longAddOperator2 = ByteUtil.byteArray2Long(longAddOperator2Array);
                long longResult = longAddOperator2 - longAddOperator1;
                byte[] longResultBytes = ByteUtil.long2ByteArray(longResult);
                getOperandStack().push(longResultBytes);
                break;
            case Constants.F_SUB:
                byte[] floatAddOperator1Array = getOperandStack().pop();
                float floatAddOperator1 = ByteUtil.byteArray2Float(floatAddOperator1Array);
                byte[] floatAddOperator2Array = getOperandStack().pop();
                float floatAddOperator2 = ByteUtil.byteArray2Long(floatAddOperator2Array);
                float floatResult = floatAddOperator2 - floatAddOperator1;
                byte[] floatResultBytes = ByteUtil.float2ByteArray(floatResult);
                getOperandStack().push(floatResultBytes);
                break;
            case Constants.D_SUB:
                byte[] doubleAddOperator1Array = getOperandStack().pop8Bytes();
                double doubleAddOperator1 = ByteUtil.byteArray2Double(doubleAddOperator1Array);
                byte[] doubleAddOperator2Array = getOperandStack().pop8Bytes();
                double doubleAddOperator2 = ByteUtil.byteArray2Double(doubleAddOperator2Array);
                double doubleResult = doubleAddOperator2 - doubleAddOperator1;
                byte[] doubleResultBytes = ByteUtil.double2ByteArray(doubleResult);
                getOperandStack().push(doubleResultBytes);
                break;
        }

    }
}
