package com.sharpjvm.bytecode.bean.attribute;

/**
 * 未知类型的属性
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: 下午3:45
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
