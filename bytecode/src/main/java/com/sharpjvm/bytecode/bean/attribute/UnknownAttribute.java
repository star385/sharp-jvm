package com.sharpjvm.bytecode.bean.attribute;

/**
 * δ֪���͵�����
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: ����3:45
 * To change this template use File | Settings | File Templates.
 */
public class UnknownAttribute extends Attribute {

    @Override
    protected void parseOtherInfo(byte[] bytes, int startIndex) {
    }

    public byte[] toBytes() {
        return new byte[0];
    }
}
