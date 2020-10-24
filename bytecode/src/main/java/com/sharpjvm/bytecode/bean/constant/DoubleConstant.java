package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * double型的常量
 *
 * User: zhuguoyin
 * Date: 13-1-28
 * Time: 下午5:28
 * To change this template use File | Settings | File Templates.
 */
public class DoubleConstant extends Constant {

    private double value;

    public DoubleConstant() {
        this.tag = TAG_DOUBLE_INFO;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    public void fromBytes(byte[] bytes, int startIndex) {
        if (bytes == null || bytes.length < startIndex + 9) {
            throw new RuntimeException("length wrong");
        }
        this.tag = bytes[startIndex];
        byte[] valueBytes = new byte[8];
        System.arraycopy(bytes, startIndex + 1, valueBytes, 0, 8);
        this.value = ByteUtil.byteArray2Double(valueBytes);
    }

    public int getLength() {
        return 9;
    }

    @Override
    public String toString() {
        return "double constant value is" + value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
