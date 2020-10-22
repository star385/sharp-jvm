package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * 基本类型转换指令集合
 * 
 * User: zhuguoyin
 * Date: 13-3-17
 * Time: 上午9:59
 * To change this template use File | Settings | File Templates.
 */
public class CastExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        switch (command.getType()) {
        	// int转long
            case Constants.I2L:
                byte [] top = getOperandStack().pop();
                if (top == null || top.length != Constants.INT_BYTE_COUNT) {
                    throw new ExecuteException("int must be 4 bytes");
                }
                int topValue = ByteUtil.byteArray2Int(top);
                long topLongValue = topValue;
                byte [] topLongValueBytes = ByteUtil.long2ByteArray(topLongValue);
                getOperandStack().push(topLongValueBytes);
                break;
             // int转float
            case Constants.I2F:
                byte [] i2fTop = getOperandStack().pop();
                if (i2fTop == null || i2fTop.length != Constants.INT_BYTE_COUNT) {
                    throw new ExecuteException("int must be 4 bytes");
                }
                int i2fTopValue = ByteUtil.byteArray2Int(i2fTop);
                float i2fTopCastValue = i2fTopValue;
                byte [] i2fTopCastValueBytes = ByteUtil.float2ByteArray(i2fTopCastValue);
                getOperandStack().push(i2fTopCastValueBytes);
                break;
             // int转double
            case Constants.I2D:
                byte [] i2dTop = getOperandStack().pop();
                if (i2dTop == null || i2dTop.length != Constants.INT_BYTE_COUNT) {
                    throw new ExecuteException("int must be 4 bytes");
                }
                int i2dTopValue = ByteUtil.byteArray2Int(i2dTop);
                double i2dTopCastValue = i2dTopValue;
                byte [] i2dTopCastValueBytes = ByteUtil.double2ByteArray(i2dTopCastValue);
                getOperandStack().push(i2dTopCastValueBytes);
                break;
            // long转int
            case Constants.L2I:
                byte [] l2iTop = getOperandStack().pop8Bytes();
                if (l2iTop == null || l2iTop.length != Constants.LONG_BYTE_COUNT) {
                    throw new ExecuteException("long must be " + Constants.LONG_BYTE_COUNT + " bytes");
                }
                long l2iTopValue = ByteUtil.byteArray2Long(l2iTop);
                int l2iTopCastValue = (int) l2iTopValue;
                byte [] l2iTopCastValueBytes = ByteUtil.int2ByteArray(l2iTopCastValue);
                getOperandStack().push(l2iTopCastValueBytes);
                break;
            // long转float
            case Constants.L2F:
                byte [] l2fTop = getOperandStack().pop8Bytes();
                if (l2fTop == null || l2fTop.length != Constants.LONG_BYTE_COUNT) {
                    throw new ExecuteException("long must be " + Constants.LONG_BYTE_COUNT + " bytes");
                }
                long l2fTopValue = ByteUtil.byteArray2Long(l2fTop);
                float l2fTopCastValue = (float) l2fTopValue;
                byte [] l2fTopCastValueBytes = ByteUtil.float2ByteArray(l2fTopCastValue);
                getOperandStack().push(l2fTopCastValueBytes);
                break;
            // long转double
            case Constants.L2D:
                byte [] l2dTop = getOperandStack().pop8Bytes();
                if (l2dTop == null || l2dTop.length != Constants.LONG_BYTE_COUNT) {
                    throw new ExecuteException("long must be " + Constants.LONG_BYTE_COUNT + " bytes");
                }
                long l2dTopValue = ByteUtil.byteArray2Long(l2dTop);
                double l2dTopCastValue = (double) l2dTopValue;
                byte [] l2dTopCastValueBytes = ByteUtil.double2ByteArray(l2dTopCastValue);
                getOperandStack().push(l2dTopCastValueBytes);
                break;
             // float转int
            case Constants.F2I:
                byte [] f2iTop = getOperandStack().pop();
                if (f2iTop == null || f2iTop.length != Constants.FLOAT_BYTE_COUNT) {
                    throw new ExecuteException("long must be " + Constants.FLOAT_BYTE_COUNT + " bytes");
                }
                float f2iTopValue = ByteUtil.byteArray2Float(f2iTop);
                int f2iTopCastValue = (int) f2iTopValue;
                byte [] f2iTopCastValueBytes = ByteUtil.float2ByteArray(f2iTopCastValue);
                getOperandStack().push(f2iTopCastValueBytes);
                break;
             // float转long
            case Constants.F2L:
                byte [] f2lTop = getOperandStack().pop();
                if (f2lTop == null || f2lTop.length != Constants.FLOAT_BYTE_COUNT) {
                    throw new ExecuteException("long must be " + Constants.FLOAT_BYTE_COUNT + " bytes");
                }
                float f2lTopValue = ByteUtil.byteArray2Float(f2lTop);
                long f2lTopCastValue = (long) f2lTopValue;
                byte [] f2lTopCastValueBytes = ByteUtil.long2ByteArray(f2lTopCastValue);
                getOperandStack().push(f2lTopCastValueBytes);
                break;
             // float转double
            case Constants.F2D:
                byte [] f2dTop = getOperandStack().pop();
                if (f2dTop == null || f2dTop.length != Constants.FLOAT_BYTE_COUNT) {
                    throw new ExecuteException("long must be " + Constants.FLOAT_BYTE_COUNT + " bytes");
                }
                float f2dTopValue = ByteUtil.byteArray2Float(f2dTop);
                double f2dTopCastValue = f2dTopValue;
                byte [] f2dTopCastValueBytes = ByteUtil.double2ByteArray(f2dTopCastValue);
                getOperandStack().push(f2dTopCastValueBytes);
                break;
             // double转int
            case Constants.D2I:
                byte [] d2iTop = getOperandStack().pop8Bytes();
                if (d2iTop == null || d2iTop.length != Constants.DOUBLE_BYTE_COUNT) {
                    throw new ExecuteException("double must be " + Constants.DOUBLE_BYTE_COUNT + " bytes");
                }
                double d2iTopValue = ByteUtil.byteArray2Double(d2iTop);
                int d2iTopCastValue = (int) d2iTopValue;
                byte [] d2iTopCastValueBytes = ByteUtil.int2ByteArray(d2iTopCastValue);
                getOperandStack().push(d2iTopCastValueBytes);
                break;
             // double转long
            case Constants.D2L:
                byte [] d2lTop = getOperandStack().pop8Bytes();
                if (d2lTop == null || d2lTop.length != Constants.DOUBLE_BYTE_COUNT) {
                    throw new ExecuteException("double must be " + Constants.DOUBLE_BYTE_COUNT + " bytes");
                }
                double d2lTopValue = ByteUtil.byteArray2Double(d2lTop);
                long d2lTopCastValue = (long) d2lTopValue;
                byte [] d2lTopCastValueBytes = ByteUtil.long2ByteArray(d2lTopCastValue);
                getOperandStack().push(d2lTopCastValueBytes);
                break;
             // double转float
            case Constants.D2F:
                byte [] d2fTop = getOperandStack().pop8Bytes();
                if (d2fTop == null || d2fTop.length != Constants.DOUBLE_BYTE_COUNT) {
                    throw new ExecuteException("double must be " + Constants.DOUBLE_BYTE_COUNT + " bytes");
                }
                double d2fTopValue = ByteUtil.byteArray2Double(d2fTop);
                float d2fTopCastValue = (float) d2fTopValue;
                byte [] d2fTopCastValueBytes = ByteUtil.float2ByteArray(d2fTopCastValue);
                getOperandStack().push(d2fTopCastValueBytes);
                break;
             // int转byte
            case Constants.I2B:
                byte [] i2bTop = getOperandStack().pop();
                if (i2bTop == null || i2bTop.length != Constants.INT_BYTE_COUNT) {
                    throw new ExecuteException("int must be " + Constants.INT_BYTE_COUNT + " bytes");
                }
                int i2bTopValue = ByteUtil.byteArray2Int(i2bTop);
                byte i2bTopCastValue = (byte) i2bTopValue;
                byte [] i2bTopCastValueBytes = {i2bTopCastValue};
                getOperandStack().push(i2bTopCastValueBytes);
                break;
             // int转char
            case Constants.I2C:
                byte [] i2cTop = getOperandStack().pop();
                if (i2cTop == null || i2cTop.length != Constants.INT_BYTE_COUNT) {
                    throw new ExecuteException("int must be " + Constants.INT_BYTE_COUNT + " bytes");
                }
                int i2cTopValue = ByteUtil.byteArray2Int(i2cTop);
                char i2cTopCastValue = (char) i2cTopValue;
                byte [] i2cTopCastValueBytes = ByteUtil.short2ByteArray((short) i2cTopCastValue);
                getOperandStack().push(i2cTopCastValueBytes);
                break;
             // int转short
            case Constants.I2S:
                byte [] i2sTop = getOperandStack().pop();
                if (i2sTop == null || i2sTop.length != Constants.INT_BYTE_COUNT) {
                    throw new ExecuteException("int must be " + Constants.INT_BYTE_COUNT + " bytes");
                }
                int i2sTopValue = ByteUtil.byteArray2Int(i2sTop);
                short i2sTopCastValue = (short) i2sTopValue;
                byte [] i2sTopCastValueBytes = ByteUtil.short2ByteArray(i2sTopCastValue);
                getOperandStack().push(i2sTopCastValueBytes);
                break;
        }
    }
}
