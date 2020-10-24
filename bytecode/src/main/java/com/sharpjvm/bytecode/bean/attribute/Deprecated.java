package com.sharpjvm.bytecode.bean.attribute;

/**
 * Deprecated
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: ионГ10:46
 * To change this template use File | Settings | File Templates.
 */
public class Deprecated extends Attribute {

    @Override
    protected void parseOtherInfo(byte[] bytes, int startIndex) {
    }

    public byte[] toBytes() {
        return new byte[0];
    }

}
