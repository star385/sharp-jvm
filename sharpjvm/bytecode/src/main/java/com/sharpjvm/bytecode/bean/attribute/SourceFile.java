package com.sharpjvm.bytecode.bean.attribute;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * class文件对应的索引文件
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: 上午10:07
 * To change this template use File | Settings | File Templates.
 */
public class SourceFile extends Attribute {

    private short sourceFileIndex;

    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void parseOtherInfo(byte[] bytes, int startIndex) {
        byte[] sourceFileIndexBytes = new byte[2];
        System.arraycopy(bytes, startIndex, sourceFileIndexBytes, 0, 2);
        this.sourceFileIndex = ByteUtil.byteArray2Short(sourceFileIndexBytes);
    }

    public short getSourceFileIndex() {
        return sourceFileIndex;
    }

    public void setSourceFileIndex(short sourceFileIndex) {
        this.sourceFileIndex = sourceFileIndex;
    }
}
