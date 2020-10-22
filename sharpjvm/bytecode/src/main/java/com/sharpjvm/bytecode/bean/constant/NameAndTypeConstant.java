package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * 名称和类型常量类型，指向另外两个常量，这个之前没整明白，命名不太正确。
 *
 * User: zhuguoyin
 * Date: 13-1-29
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 */
public class NameAndTypeConstant extends Constant {

    private short constantIndex = -1;

    private short constantDescriptionIndex = -1;

    public NameAndTypeConstant() {
        this.tag = TAG_NAME_AND_TYPE_INFO;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    public void fromBytes(byte[] bytes, int startIndex) {
        if (bytes == null || bytes.length < startIndex + 5) {
            throw new RuntimeException("length wrong");
        }
        this.tag = bytes[startIndex];
        byte[] classIndexBytes = new byte[2];
        System.arraycopy(bytes, startIndex + 1, classIndexBytes, 0, 2);
        this.constantIndex = ByteUtil.byteArray2Short(classIndexBytes);
        byte[] fieldIndexBytes = new byte[2];
        System.arraycopy(bytes, startIndex + 3, fieldIndexBytes, 0, 2);
        this.constantDescriptionIndex = ByteUtil.byteArray2Short(fieldIndexBytes);
    }

    public int getLength() {
        return 5;
    }

    public Utf8Constant getNameConstant() {
        if (constantIndex < -1) {
            throw new RuntimeException("didn't set constant index, can't show constant name");
        }
        if (constantList == null) {
            throw new RuntimeException("constants info is empty, can't show constant name");
        }
        Constant constant = constantList.getConstant(constantIndex);
        if (!(constant instanceof Utf8Constant)) {
            throw new RuntimeException("name Constant is not Utf8Constant");
        }
        return (Utf8Constant) constant;
    }

    public Utf8Constant getDescriptionConstant() {
        if (constantDescriptionIndex < -1) {
            throw new RuntimeException("didn't set constant description index, can't show constant description");
        }
        if (constantList == null) {
            throw new RuntimeException("constants info is empty, can't show constant description");
        }
        Constant constant = constantList.getConstant(constantDescriptionIndex);
        if (!(constant instanceof Utf8Constant)) {
            throw new RuntimeException("description Constant is not Utf8Constant");
        }
        return (Utf8Constant) constant;
    }

    @Override
    public String toString() {
        Constant constantConstant = getNameConstant();
        Constant constantDescriptionConstant = getDescriptionConstant();
        StringBuilder sb = new StringBuilder();
        sb.append("name and type info, name:");
        sb.append(constantConstant);
        sb.append("description:" + constantDescriptionConstant);
        return sb.toString();
    }

    public void setConstantIndex(short constantIndex) {
        this.constantIndex = constantIndex;
    }

    public void setConstantDescriptionIndex(short constantDescriptionIndex) {
        this.constantDescriptionIndex = constantDescriptionIndex;
    }
}
