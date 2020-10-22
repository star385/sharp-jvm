package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * Created by IntelliJ IDEA.
 * User: zhuguoyin
 * Date: 13-1-29
 * Time: ионГ10:59
 * To change this template use File | Settings | File Templates.
 */
public class StringConstant extends Constant {

    private short index;

    public StringConstant() {
        this.tag = TAG_STRING_INFO;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    public void fromBytes(byte[] bytes, int startIndex) {
        if (bytes == null || bytes.length < startIndex + 3) {
            throw new RuntimeException("length wrong");
        }
        this.tag = bytes[startIndex];
        byte[] indexBytes = new byte[2];
        System.arraycopy(bytes, startIndex + 1, indexBytes, 0, 2);
        this.index = ByteUtil.byteArray2Short(indexBytes);
    }

    public int getLength() {
        return 3;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("string info, string value:");
        Utf8Constant utf8Constant = getNameConstant();
        sb.append(utf8Constant.getValue());
        return sb.toString();
    }

    public Utf8Constant getNameConstant() {
        if (index < -1) {
            throw new RuntimeException("didn't set string index, can't show class name");
        }
        if (constantList == null) {
            throw new RuntimeException("constants info is empty, can't show class name");
        }
        Constant constant = constantList.getConstant(index);
        if (!(constant instanceof Utf8Constant)) {
            throw new RuntimeException("string constant is not uft8constant.");
        }
        return (Utf8Constant) constant;
    }
}
