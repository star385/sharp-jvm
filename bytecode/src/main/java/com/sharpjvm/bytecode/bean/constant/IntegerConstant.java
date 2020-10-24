package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * Created by IntelliJ IDEA.
 * User: zhuguoyin
 * Date: 13-1-24
 * Time: ÏÂÎç11:42
 * To change this template use File | Settings | File Templates.
 */
public class IntegerConstant extends Constant {

    private int value;

    public IntegerConstant() {
        tag = TAG_INTEGER_INFO;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    public void fromBytes(byte[] bytes, int startIndex) {
        if (bytes == null || bytes.length < startIndex + 5) {
            throw new RuntimeException("length wrong");
        }
        this.tag = bytes[startIndex];
        byte[] valueBytes = new byte[4];
        System.arraycopy(bytes, startIndex + 1, valueBytes, 0, 4);
        this.value = ByteUtil.byteArray2Int(valueBytes);
    }

    public int getLength() {
        return 5;
    }

    @Override
    public String toString() {
        return "int constant:" + value;
    }
}
