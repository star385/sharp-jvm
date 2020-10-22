package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * 类类型常量
 *
 * User: zhuguoyin
 * Date: 13-1-28
 * Time: 下午5:33
 * To change this template use File | Settings | File Templates.
 */
public class ClassConstant extends Constant {

    private short classIndex = -1;

    public ClassConstant() {
        this.tag = TAG_CLASS_INFO;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    public void fromBytes(byte[] bytes, int startIndex) {
        if (bytes == null || bytes.length < startIndex + getLength()) {
            throw new RuntimeException("length wrong");
        }
        this.tag = bytes[startIndex];
        byte[] indexBytes = new byte[2];
        System.arraycopy(bytes, startIndex + 1, indexBytes, 0, 2);
        this.classIndex = ByteUtil.byteArray2Short(indexBytes);
    }

    public int getLength() {
        return 3;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("class info, class name:");
        Utf8Constant utf8Constant = getNameConstant();
        sb.append(utf8Constant.getValue());
        return sb.toString();
    }

    public Utf8Constant getNameConstant() {
        if (classIndex < -1) {
            throw new RuntimeException("didn't set class index, can't show class name");
        }
        if (constantList == null) {
            throw new RuntimeException("constants info is empty, can't show class name");
        }
        Constant constant = constantList.getConstant(classIndex);
        if (!(constant instanceof Utf8Constant)) {
            throw new RuntimeException("class constant is not uft8constant.");
        }
        return (Utf8Constant) constant;
    }

    public short getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(short classIndex) {
        this.classIndex = classIndex;
    }
}
