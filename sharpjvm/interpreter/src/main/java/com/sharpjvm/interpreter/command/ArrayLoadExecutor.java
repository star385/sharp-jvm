package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * aload相关的指令执行器，将指定数组类型变量推送至栈顶。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午9:34
 * To change this template use File | Settings | File Templates.
 */
public class ArrayLoadExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte commandType = command.getType();
        switch (commandType) {
        	// 对象数组推送到栈顶
            case Constants.A_A_LOAD:
                byte[] indexOfObjectArrayBytes = getStackFrame().getOperandStack().pop();
                int indexOfObjectArray = ByteUtil.byteArray2Int(indexOfObjectArrayBytes);
                byte[] referenceOfObjectArrayBytes = getStackFrame().getOperandStack().pop();
                Object objectArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfObjectArrayBytes);
                if (!(objectArrayObj instanceof Object[])) {
                    throw new ExecuteException("aaload command must have object array");
                }
                Object[] objectArray = (Object[]) objectArrayObj;
                if (indexOfObjectArray >= objectArray.length) {
                    throw new ExecuteException("command: aaload, message: index cannot larger than length of object array");
                }
                Object objectElementAtIndex = objectArray[indexOfObjectArray];
                byte[] referenceOfObjectElement = RuntimeContext.getInstance().getHeap().getReference(objectElementAtIndex);
                getOperandStack().push(referenceOfObjectElement);
                break;
            // 整型数组推送到栈顶
            case Constants.I_A_LOAD:
                byte[] indexOfIntArrayBytes = getStackFrame().getOperandStack().pop();
                int indexOfIntArray = ByteUtil.byteArray2Int(indexOfIntArrayBytes);
                byte[] referenceOfIntArrayBytes = getStackFrame().getOperandStack().pop();
                Object intArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfIntArrayBytes);
                if (!(intArrayObj instanceof int[])) {
                    throw new ExecuteException("iaload command must have int array");
                }
                int[] intArray = (int[]) intArrayObj;
                if (indexOfIntArray >= intArray.length) {
                    throw new ExecuteException("command: iaload, message: index cannot larger than length of int array");
                }
                int intElementAtIndex = intArray[indexOfIntArray];
                byte[] intElementAtIndexBytes = ByteUtil.int2ByteArray(intElementAtIndex);
                getOperandStack().push(intElementAtIndexBytes);
                break;
             // 长整型数组推送到栈顶
            case Constants.L_A_LOAD:
                byte[] indexOfLongArrayBytes = getStackFrame().getOperandStack().pop();
                int indexOfLongArray = ByteUtil.byteArray2Int(indexOfLongArrayBytes);
                byte[] referenceOfLongArrayBytes = getStackFrame().getOperandStack().pop();
                Object longArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfLongArrayBytes);
                if (!(longArrayObj instanceof long[])) {
                    throw new ExecuteException("laload command must have long array");
                }
                long[] longArray = (long[]) longArrayObj;
                if (indexOfLongArray >= longArray.length) {
                    throw new ExecuteException("command: laload, message: index cannot larger than length of long array");
                }
                long longElementAtIndex = longArray[indexOfLongArray];
                byte[] longElementAtIndexBytes = ByteUtil.long2ByteArray(longElementAtIndex);
                getOperandStack().push(longElementAtIndexBytes);
                break;
             // 双精度浮点型数组推送到栈顶
            case Constants.D_A_LOAD:
                byte[] indexOfDoubleArrayBytes = getStackFrame().getOperandStack().pop();
                int indexOfDoubleArray = ByteUtil.byteArray2Int(indexOfDoubleArrayBytes);
                byte[] referenceOfDoubleArrayBytes = getStackFrame().getOperandStack().pop();
                Object doubleArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfDoubleArrayBytes);
                if (!(doubleArrayObj instanceof double[])) {
                    throw new ExecuteException("daload command must have double array");
                }
                double[] doubleArray = (double[]) doubleArrayObj;
                if (indexOfDoubleArray >= doubleArray.length) {
                    throw new ExecuteException("command: daload, message: index cannot larger than length of double array");
                }
                double doubleElementAtIndex = doubleArray[indexOfDoubleArray];
                byte[] doubleElementAtIndexBytes = ByteUtil.double2ByteArray(doubleElementAtIndex);
                getOperandStack().push(doubleElementAtIndexBytes);
                break;
            // 字节或者布尔型数组推送到栈顶
            case Constants.B_A_LOAD:
                byte[] indexOfByteOrBooleanArrayBytes = getStackFrame().getOperandStack().pop();
                int indexOfByteOrBooleanArray = ByteUtil.byteArray2Int(indexOfByteOrBooleanArrayBytes);
                byte[] referenceOfbyteOrBooleanArrayBytes = getStackFrame().getOperandStack().pop();
                Object byteOrBooleanArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfbyteOrBooleanArrayBytes);
                if (byteOrBooleanArrayObj instanceof byte[]) {
                    byte[] byteArray = (byte[]) byteOrBooleanArrayObj;
                    if (indexOfByteOrBooleanArray >= byteArray.length) {
                        throw new ExecuteException("command: baload, message: index cannot larger than length of byte array");
                    }
                    byte byteElementAtIndex = byteArray[indexOfByteOrBooleanArray];
                    getOperandStack().push(new byte[]{byteElementAtIndex});
                }
                if (byteOrBooleanArrayObj instanceof boolean[]) {
                    boolean [] booleanArray = (boolean[]) byteOrBooleanArrayObj;
                    if (indexOfByteOrBooleanArray >= booleanArray.length) {
                        throw new ExecuteException("command: baload, message: index cannot larger than length of boolean array");
                    }
                    boolean booleanElementAtIndex = booleanArray[indexOfByteOrBooleanArray];
                    if (booleanElementAtIndex) {
                        getOperandStack().push(new byte[]{Constants.TRUE_BYTE});
                    } else {
                        getOperandStack().push(new byte[]{Constants.FALSE_BYTE});
                    }
                }
                throw new ExecuteException("baload command must have byte or boolean array");
            // 字符型数组推送到栈顶
            case Constants.C_A_LOAD:
                byte[] indexOfCharArrayBytes = getStackFrame().getOperandStack().pop();
                int indexOfCharArray = ByteUtil.byteArray2Int(indexOfCharArrayBytes);
                byte[] referenceOfCharArrayBytes = getStackFrame().getOperandStack().pop();
                Object charArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfCharArrayBytes);
                if (!(charArrayObj instanceof char[])) {
                    throw new ExecuteException("caload command must have char array");
                }
                char[] charArray = (char[]) charArrayObj;
                if (indexOfCharArray >= charArray.length) {
                    throw new ExecuteException("command: caload, message: index cannot larger than length of char array");
                }
                char charElementAtIndex = charArray[indexOfCharArray];
                byte[] charElementAtIndexBytes = ByteUtil.short2ByteArray((short) charElementAtIndex);
                getOperandStack().push(charElementAtIndexBytes);
                break;
            // 浮点型数组推送到栈顶
            case Constants.F_A_LOAD:
                byte[] indexOfFloatArrayBytes = getStackFrame().getOperandStack().pop();
                int indexOfFloatArray = ByteUtil.byteArray2Int(indexOfFloatArrayBytes);
                byte[] referenceOfFloatArrayBytes = getStackFrame().getOperandStack().pop();
                Object floatArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfFloatArrayBytes);
                if (!(floatArrayObj instanceof float[])) {
                    throw new ExecuteException("caload command must have float array");
                }
                float[] floatArray = (float[]) floatArrayObj;
                if (indexOfFloatArray >= floatArray.length) {
                    throw new ExecuteException("command: caload, message: index cannot larger than length of float array");
                }
                float floatElementAtIndex = floatArray[indexOfFloatArray];
                byte[] floatElementAtIndexBytes = ByteUtil.float2ByteArray(floatElementAtIndex);
                getOperandStack().push(floatElementAtIndexBytes);
                break;
            // 短整型数组推送到栈顶
            case Constants.S_A_LOAD:
                byte[] indexOfShortArrayBytes = getStackFrame().getOperandStack().pop();
                int indexOfShortArray = ByteUtil.byteArray2Int(indexOfShortArrayBytes);
                byte[] referenceOfShortArrayBytes = getStackFrame().getOperandStack().pop();
                Object shortArrayObj = RuntimeContext.getInstance().getHeap().get(referenceOfShortArrayBytes);
                if (!(shortArrayObj instanceof short[])) {
                    throw new ExecuteException("saload command must have short array");
                }
                short[] shortArray = (short[]) shortArrayObj;
                if (indexOfShortArray >= shortArray.length) {
                    throw new ExecuteException("command: saload, message: index cannot larger than length of short array");
                }
                short shortElementAtIndex = shortArray[indexOfShortArray];
                byte[] shortElementAtIndexBytes = ByteUtil.short2ByteArray(shortElementAtIndex);
                getOperandStack().push(shortElementAtIndexBytes);
                break;
        }
    }
}
