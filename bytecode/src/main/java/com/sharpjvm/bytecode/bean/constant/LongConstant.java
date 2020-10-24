package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * Created by IntelliJ IDEA.
 * User: zhuguoyin
 * Date: 13-1-28
 * Time: обнГ5:26
 * To change this template use File | Settings | File Templates.
 */
public class LongConstant extends Constant {

    private long value;

    public LongConstant() {
        this.tag = TAG_LONG_INFO;
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
        this.value = ByteUtil.byteArray2Long(valueBytes);
    }

    public int getLength() {
        return 9;
    }

    @Override
    public String toString() {
        return "long constant:" + value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
