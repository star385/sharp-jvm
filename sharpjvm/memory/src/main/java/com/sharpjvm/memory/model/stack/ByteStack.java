package com.sharpjvm.memory.model.stack;

import com.sharpjvm.bytecode.constants.Constants;

/**
 * �ֽ�ջ��û�а취��jdk���Stack��֧�ֻ������ͣ�ֻ����ô���ˡ�
 * ֮ǰ����ʵ�ַ�ʽ��һά���飬��������һά����ʵ�ֺܷѾ������Ǹĳ���2ά���飬��ʵ��Stack<byte[]>���Ĺ��ܡ�
 * ������jdk���Stack�滻��������д��������˵ģ����Ǳ�������
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: ����9:28
 * To change this template use File | Settings | File Templates.
 */
public class ByteStack {

    private byte[][] byteArray;

    private int size;

    // �����code�������maxStack��push������ʱ���ǲ�����stack��size�ǲ��ܳ�����ֵ�����򣬾����ֽ���������
    private int maxStack;

    public ByteStack() {
        this(100);
    }

    public ByteStack(int initialLength) {
        this.byteArray = new byte[initialLength][];
        this.size = 0;
    }

    /**
     * ѹ��ջ
     * 
     * @param value
     */
    public void push(byte[] value) {
    	// size�ǲ����Գ���maxstack��
        if (size >= maxStack) {
            throw new RuntimeException("size bigger or equals to max stack length, can not push more element");
        }
        // ��ʵ���´���ûʲô��Ҫ���Ͼ�����ֻҪһ���򵥵�stack���ݽṹ�����ֵһ��ʼ��ȷ���ˣ����if��Զ�����ܽ�ȥ�ġ����Ǵ�����ˡ�
        if (byteArray.length <= size) {
            byte[][] bak = byteArray.clone();
            int length = byteArray.length;
            length += Math.floor(length / 2.0);
            byteArray = new byte[length][];
            System.arraycopy(bak, 0, byteArray, 0, bak.length);
        }
        // ջ����Ϊ�������飬size����
        byteArray[size++] = value;
        // ����ӽ�������8���ֽڵĴ���������Ҫռ������slot�����size������һ�£���һ�����
        // ��Ȼ����Ҳ���Խ�8���ֽڵ�Ԫ�ز������4���ֽڵ����飬�ֱ���2��Ԫ��
        // ����������Ҫ����������ȥ����������������
        if (value != null && value.length == Constants.LONG_BYTE_COUNT) {
            size++;
        }
    }

    /**
     * Ĭ�ϵ�pop������pop��4�ֽ�
     *
     * @return
     */
    public byte[] pop() {
        if (size <= 0) {
            throw new RuntimeException("no stack to pop!");
        }
        byte[] popValue = byteArray[size - 1];
        size--;
        return popValue;
    }

    /**
     * pop��8���ֽ�����
     * 
     * @return
     */
    public byte[] pop8Bytes() {
        if (size <= 1) {
            throw new RuntimeException("only 1 stack cannot pop 8 bytes!");
        }
        // ��Ϊpush��ʱ�����һ�������������Լ������������Ԫ��
        size--;
        byte[] popValue = byteArray[--size];
        return popValue;
    }


    /**
     * �����ǻ�ȡ�������ı�ջ�ṹ��Ĭ�ϻ�ȡ4���ֽ�
     *
     * @return
     */
    public byte[] get() {
        if (size <= 0) {
            throw new RuntimeException("no stack to get!");
        }
        byte[] getValue = byteArray[size - 1];
        return getValue;
    }
    
    /**
     * �����ǻ�ȡ�������ı�ջ�ṹ��Ĭ�ϻ�ȡ4���ֽ�
     *
     * @return
     */
    public byte[] get8Bytes() {
        if (size <= 1) {
            throw new RuntimeException("no stack to get!");
        }
        byte[] getValue = byteArray[size - 2];
        return getValue;
    }

    public void setTop(byte [] bytes) {
        this.byteArray[size - 1] = bytes;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }
}
