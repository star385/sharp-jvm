package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * User: zhuguoyin
 * Date: 13-1-24
 * Time: ÏÂÎç11:23
 * To change this template use File | Settings | File Templates.
 */
public class Utf8Constant extends Constant {

    private short contentLength;

    private String value;

    public Utf8Constant() {
        this.tag = TAG_UTF8_INFO;
    }

    public short getContentLength() {
        return contentLength;
    }

    public void setContentLength(short contentLength) {
        this.contentLength = contentLength;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    public void fromBytes(byte[] bytes, int startIndex) {
        if (bytes == null || bytes.length == 0) {
            throw new RuntimeException("no byte array");
        }
        if (bytes.length < 2) {
            throw new RuntimeException("no contentLength");
        }
        byte[] lengthBytes = new byte[2];
        System.arraycopy(bytes, startIndex + 1, lengthBytes, 0, 2);
        contentLength = ByteUtil.byteArray2Short(lengthBytes);
        if (bytes.length - 2 < contentLength) {
            throw new RuntimeException("byte contentLength wrong");
        }
        byte[] content = new byte[contentLength];
        System.arraycopy(bytes, startIndex + 1 + 2, content, 0, contentLength);
        this.value = new String(content);
    }

    public int getLength() {
        return 1 + 2 + contentLength;
    }

    @Override
    public String toString() {
        return "UTF8 constant:" + value;
    }
}
