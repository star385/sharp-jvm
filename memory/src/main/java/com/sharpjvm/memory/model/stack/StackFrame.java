package com.sharpjvm.memory.model.stack;


/**
 * ջ֡���ݽṹ��
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: ����9:46
 * To change this template use File | Settings | File Templates.
 */
public class StackFrame {

    // ������ջ
    private ByteStack operandStack;

    // �ֲ�������
    private byte[][] localVariableTable;

    // ���ص�ַ
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
            throw new RuntimeException("ջ֡�в������ó�����maxLocals����ľֲ�����");
        }
        return localVariableTable[index];
    }

    public void setLocalVariable(int index, byte[] localVariable) {
        if (index > localVariableTable.length) {
            throw new RuntimeException("ջ֡�в������ó�����maxLocals����ľֲ�����");
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
