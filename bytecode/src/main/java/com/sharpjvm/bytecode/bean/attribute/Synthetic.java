package com.sharpjvm.bytecode.bean.attribute;

/**
 * Synthetic����
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: ����2:31
 * To change this template use File | Settings | File Templates.
 */
public class Synthetic extends Attribute {

    @Override
    protected void parseOtherInfo(byte[] bytes, int startIndex) {
    }

    public byte[] toBytes() {
        return new byte[0];
    }
}
