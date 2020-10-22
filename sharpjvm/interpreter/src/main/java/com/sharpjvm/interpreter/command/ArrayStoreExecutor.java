package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * array store related, including iastore, fastore, aastore and so on.
 *
 * User: zhuguoyin
 * Date: 13-3-16
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
public class ArrayStoreExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        switch (command.getType()) {
        	// 整型数组存到局部变量表
            case Constants.I_A_STORE:
                byte[] intValueBytes = getStackFrame().getOperandStack().pop();
                int intValue = ByteUtil.byteArray2Int(intValueBytes);

                byte[] indexOfIntArrayBytes = getOperandStack().pop();
                int indexOfIntArray = ByteUtil.byteArray2Int(indexOfIntArrayBytes);
                byte[] referenceOfIntArrayBytes = getStackFrame().getOperandStack().pop();
                Object intArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfIntArrayBytes);
                if (!(intArrayObj instanceof int[])) {
                    throw new ExecuteException("iaload command must have int array");
                }
                int[] intArray = (int[]) intArrayObj;
                if (indexOfIntArray >= intArray.length) {
                    throw new ExecuteException("command: aaload, message: index cannot larger than length of object array");
                }
                intArray[indexOfIntArray] = intValue;
                break;
             // 长整型数组存到局部变量表
            case Constants.L_A_STORE:
                byte[] longValueBytes = getStackFrame().getOperandStack().pop8Bytes();
                long longValue = ByteUtil.byteArray2Long(longValueBytes);

                byte[] indexOfLongArrayBytes = getOperandStack().pop8Bytes();
                int indexOfLongArray = ByteUtil.byteArray2Int(indexOfLongArrayBytes);
                byte[] referenceOfLongArrayBytes = getStackFrame().getOperandStack().pop();
                Object longArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfLongArrayBytes);
                if (!(longArrayObj instanceof long[])) {
                    throw new ExecuteException("iaload command must have long array");
                }
                long[] longArray = (long[]) longArrayObj;
                if (indexOfLongArray >= longArray.length) {
                    throw new ExecuteException("command: aaload, message: index cannot larger than length of object array");
                }
                longArray[indexOfLongArray] = longValue;
                break;
             // 浮点型数组存到局部变量表
            case Constants.F_A_STORE:
                byte[] floatValueBytes = getStackFrame().getOperandStack().pop();
                float floatValue = ByteUtil.byteArray2Float(floatValueBytes);

                byte[] indexOfFloatArrayBytes = getOperandStack().pop();
                int indexOfFloatArray = ByteUtil.byteArray2Int(indexOfFloatArrayBytes);
                byte[] referenceOfFloatArrayBytes = getStackFrame().getOperandStack().pop();
                Object floatArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfFloatArrayBytes);
                if (!(floatArrayObj instanceof float[])) {
                    throw new ExecuteException("iaload command must have float array");
                }
                float[] floatArray = (float[]) floatArrayObj;
                if (indexOfFloatArray >= floatArray.length) {
                    throw new ExecuteException("command: aaload, message: index cannot larger than length of object array");
                }
                floatArray[indexOfFloatArray] = floatValue;
                break;
             // 双精度浮点型数组存到局部变量表
            case Constants.D_A_STORE:
                byte[] doubleValueBytes = getStackFrame().getOperandStack().pop8Bytes();
                double doubleValue = ByteUtil.byteArray2Double(doubleValueBytes);

                byte[] indexOfDoubleArrayBytes = getOperandStack().pop8Bytes();
                int indexOfDoubleArray = ByteUtil.byteArray2Int(indexOfDoubleArrayBytes);
                byte[] referenceOfDoubleArrayBytes = getStackFrame().getOperandStack().pop();
                Object doubleArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfDoubleArrayBytes);
                if (!(doubleArrayObj instanceof double[])) {
                    throw new ExecuteException("iaload command must have double array");
                }
                double[] doubleArray = (double[]) doubleArrayObj;
                if (indexOfDoubleArray >= doubleArray.length) {
                    throw new ExecuteException("command: aaload, message: index cannot larger than length of object array");
                }
                doubleArray[indexOfDoubleArray] = doubleValue;
                break;
             // 对象型数组存到局部变量表
            case Constants.A_A_STORE:
                byte[] objectValueBytes = getStackFrame().getOperandStack().pop();
                Object objectValue = RuntimeContext.getInstance().getHeap().get(objectValueBytes);

                byte[] indexOfObjectArrayBytes = getOperandStack().pop();
                int indexOfObjectArray = ByteUtil.byteArray2Int(indexOfObjectArrayBytes);
                byte[] referenceOfObjectArrayBytes = getStackFrame().getOperandStack().pop();
                Object objectArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfObjectArrayBytes);
                if (!(objectArrayObj instanceof Object[])) {
                    throw new ExecuteException("iaload command must have object array");
                }
                Object[] objectArray = (Object[]) objectArrayObj;
                if (indexOfObjectArray >= objectArray.length) {
                    throw new ExecuteException("command: aaload, message: index cannot larger than length of object array");
                }
                objectArray[indexOfObjectArray] = objectValue;
                break;
             // 字节或者布尔型数组存到局部变量表
            case Constants.B_A_STORE:
                byte[] byteOrBooleanValueBytes = getStackFrame().getOperandStack().pop();
                if (byteOrBooleanValueBytes == null || byteOrBooleanValueBytes.length != 1) {
                    throw new ExecuteException("byte or boolean value must be 1 byte");
                }
                byte byteOrBooleanValue = byteOrBooleanValueBytes[0];

                byte[] indexOfByteOrBooleanArrayBytes = getOperandStack().pop();
                int indexOfByteOrBooleanArray = ByteUtil.byteArray2Int(indexOfByteOrBooleanArrayBytes);
                byte[] referenceOfByteOrBooleanArrayBytes = getStackFrame().getOperandStack().pop();
                Object byteOrBooleanArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfByteOrBooleanArrayBytes);
                if (byteOrBooleanArrayObj instanceof byte[]) {
                    byte[] byteArray = (byte[]) byteOrBooleanArrayObj;
                    if (indexOfByteOrBooleanArray >= byteArray.length) {
                        throw new ExecuteException("command: bastore, message: index cannot larger than length of byte array");
                    }
                    byteArray[indexOfByteOrBooleanArray] = byteOrBooleanValue;
                    break;
                }
                if (byteOrBooleanArrayObj instanceof boolean[]) {
                    boolean[] booleanArray = (boolean[]) byteOrBooleanArrayObj;
                    if (indexOfByteOrBooleanArray >= booleanArray.length) {
                        throw new ExecuteException("command: bastore, message: index cannot larger than length of boolean array");
                    }
                    if (byteOrBooleanValue == Constants.TRUE_BYTE) {
                        booleanArray[indexOfByteOrBooleanArray] = true;
                    } else {
                        booleanArray[indexOfByteOrBooleanArray] = false;
                    }
                    break;
                }
                throw new ExecuteException("bastore command must have byte or boolean array");
             // 字符型数组存到局部变量表
            case Constants.C_A_STORE:
                byte[] charValueBytes = getStackFrame().getOperandStack().pop();
                char charValue = (char) ByteUtil.byteArray2Short(charValueBytes);

                byte[] indexOfCharArrayBytes = getOperandStack().pop();
                int indexOfCharArray = ByteUtil.byteArray2Int(indexOfCharArrayBytes);
                byte[] referenceOfCharArrayBytes = getStackFrame().getOperandStack().pop();
                Object charArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfCharArrayBytes);
                if (!(charArrayObj instanceof char[])) {
                    throw new ExecuteException("iaload command must have char array");
                }
                char[] charArray = (char[]) charArrayObj;
                if (indexOfCharArray >= charArray.length) {
                    throw new ExecuteException("command: aaload, message: index cannot larger than length of object array");
                }
                charArray[indexOfCharArray] = charValue;
                break;
             // 短整型型数组存到局部变量表
            case Constants.S_A_STORE:
                byte[] shortValueBytes = getStackFrame().getOperandStack().pop();
                short shortValue = ByteUtil.byteArray2Short(shortValueBytes);

                byte[] indexOfShortArrayBytes = getOperandStack().pop();
                int indexOfShortArray = ByteUtil.byteArray2Int(indexOfShortArrayBytes);
                byte[] referenceOfShortArrayBytes = getStackFrame().getOperandStack().pop();
                Object shortArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfShortArrayBytes);
                if (!(shortArrayObj instanceof short[])) {
                    throw new ExecuteException("iaload command must have short array");
                }
                short[] shortArray = (short[]) shortArrayObj;
                if (indexOfShortArray >= shortArray.length) {
                    throw new ExecuteException("command: aaload, message: index cannot larger than length of object array");
                }
                shortArray[indexOfShortArray] = shortValue;
                break;
        }
    }
}
