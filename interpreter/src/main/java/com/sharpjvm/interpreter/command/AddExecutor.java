package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * 所有add命令的集合。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
public class AddExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        switch (command.getType()) {
            // 整数加
            case Constants.I_ADD:
                byte[] addOperator1Array = getOperandStack().pop();
                int addOperator1Int = ByteUtil.byteArray2Int(addOperator1Array);
                byte[] addOperator2Array = getOperandStack().pop();
                int addOperator2Int = ByteUtil.byteArray2Int(addOperator2Array);
                int result = addOperator1Int + addOperator2Int;
                byte[] resultBytes = ByteUtil.int2ByteArray(result);
                getOperandStack().push(resultBytes);
                break;
             // 长整型加
            case Constants.L_ADD:
                byte[] longAddOperator1Array = getOperandStack().pop8Bytes();
                long longAddOperator1 = ByteUtil.byteArray2Long(longAddOperator1Array);
                byte[] longAddOperator2Array = getOperandStack().pop8Bytes();
                long longAddOperator2 = ByteUtil.byteArray2Long(longAddOperator2Array);
                long longResult = longAddOperator1 + longAddOperator2;
                byte[] longResultBytes = ByteUtil.long2ByteArray(longResult);
                getOperandStack().push(longResultBytes);
                break;
            // 浮点数加
            case Constants.F_ADD:
                byte[] floatAddOperator1Array = getOperandStack().pop();
                float floatAddOperator1 = ByteUtil.byteArray2Float(floatAddOperator1Array);
                byte[] floatAddOperator2Array = getOperandStack().pop();
                float floatAddOperator2 = ByteUtil.byteArray2Float(floatAddOperator2Array);
                float floatResult = floatAddOperator1 + floatAddOperator2;
                byte[] floatResultBytes = ByteUtil.float2ByteArray(floatResult);
                getOperandStack().push(floatResultBytes);
                break;
            // 双精度浮点数加
            case Constants.D_ADD:
                byte[] doubleAddOperator1Array = getOperandStack().pop8Bytes();
                double doubleAddOperator1 = ByteUtil.byteArray2Double(doubleAddOperator1Array);
                byte[] doubleAddOperator2Array = getOperandStack().pop8Bytes();
                double doubleAddOperator2 = ByteUtil.byteArray2Double(doubleAddOperator2Array);
                double doubleResult = doubleAddOperator1 + doubleAddOperator2;
                byte[] doubleResultBytes = ByteUtil.double2ByteArray(doubleResult);
                getOperandStack().push(doubleResultBytes);
                break;
        }

    }
}
