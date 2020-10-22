package com.sharpjvm.bytecode.bean.attribute;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * ConstantValue���ԣ���Ŀ����֪ͨ�����Ϊ�����Զ���ֵ
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: ����10:12
 * To change this template use File | Settings | File Templates.
 */
public class ConstantValue extends Attribute {

    private short constantValueIndex;

    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void parseOtherInfo(byte[] bytes, int i) {
        byte[] constantValueIndexBytes = new byte[2];
        System.arraycopy(bytes, i, constantValueIndexBytes, 0, 2);
        this.constantValueIndex = ByteUtil.byteArray2Short(constantValueIndexBytes);
    }

    public short getConstantValueIndex() {
        return constantValueIndex;
    }

    public void setConstantValueIndex(short constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }
}
