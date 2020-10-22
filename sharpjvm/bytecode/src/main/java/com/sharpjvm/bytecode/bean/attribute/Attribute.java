package com.sharpjvm.bytecode.bean.attribute;

import com.sharpjvm.bytecode.bean.ByteArrayable;
import com.sharpjvm.bytecode.bean.constant.ConstantList;
import com.sharpjvm.bytecode.bean.constant.Utf8Constant;
import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * 属性的接口
 *
 * User: zhuguoyin
 * Date: 13-1-30
 * Time: 下午5:44
 * To change this template use File | Settings | File Templates.
 */
public abstract class Attribute implements ByteArrayable {

    // 名称索引
    protected short nameIndex;

    // 索引的长度
    protected int attributeLength;

    // 类的所有常量
    protected ConstantList constantList;

    /**
     * 从字节数组中填充自己的所有信息
     *
     * @param bytes 即一个class的字节码读入内存中的字节数组
     * @param startIndex 起始索引，亦即从哪儿开始解析
     */
    public void fromBytes(byte[] bytes, int startIndex) {
        // 边界校验
        if (bytes == null || bytes.length < (startIndex + getLength())) {
            throw new RuntimeException("bytes length wrong!");
        }

        byte[] nameIndexBytes = new byte[2];
        System.arraycopy(bytes, startIndex, nameIndexBytes, 0, 2);
        this.nameIndex = ByteUtil.byteArray2Short(nameIndexBytes);

        byte[] lengthBytes = new byte[4];
        System.arraycopy(bytes, startIndex + 2, lengthBytes, 0, 4);
        this.attributeLength = ByteUtil.byteArray2Int(lengthBytes);

        parseOtherInfo(bytes, startIndex + 2 + 4);
    }

    public int getLength() {
        return 2 + 4 + attributeLength;
    }

    protected abstract void parseOtherInfo(byte[] bytes, int startIndex);

    public short getNameIndex() {
        return nameIndex;
    }

    public String toString() {
        return constantList.getConstant(nameIndex).toString();
    }

    public Utf8Constant getNameConstant() {
        return (Utf8Constant) constantList.getConstant(nameIndex);
    }

    public void setNameIndex(short nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(int attributeLength) {
        this.attributeLength = attributeLength;
    }

    public void setConstantList(ConstantList constantList) {
        this.constantList = constantList;
    }
}
