package com.sharpjvm.memory.model.stack;


/**
 * 栈帧数据结构。
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
public class StackFrame {

    // 操作数栈
    private ByteStack operandStack;

    // 局部便量表
    private byte[][] localVariableTable;

    // 返回地址
    private byte[] returnAddress;

    private Throwable exception;

    public StackFrame(int maxStack, int maxLocals) {
        operandStack = new ByteStack(maxStack);
        operandStack.setMaxStack(maxStack);
        localVariableTable = new byte[maxLocals][];
    }

    public ByteStack getOperandStack() {
        return operandStack;
    }

    public void setOperandStack(ByteStack operandStack) {
        this.operandStack = operandStack;
    }

//    public byte[][] getLocalVariableTable() {
//        return localVariableTable;
//    }

    public byte[] getLocalVariableTableAt(int index) {
    	if (index > localVariableTable.length) {
            throw new RuntimeException("栈帧中不能设置超过比maxLocals更多的局部变量");
        }
        return localVariableTable[index];
    }

    public void setLocalVariable(int index, byte[] localVariable) {
        if (index > localVariableTable.length) {
            throw new RuntimeException("栈帧中不能设置超过比maxLocals更多的局部变量");
        }
        localVariableTable[index] = localVariable;
    }

    public byte[] getReturnAddress() {
        return returnAddress;
    }

    public void setReturnAddress(byte[] returnAddress) {
        this.returnAddress = returnAddress;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
