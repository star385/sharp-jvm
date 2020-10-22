package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * Created by IntelliJ IDEA.
 * User: zhuguoyin
 * Date: 13-1-29
 * Time: ионГ11:25
 * To change this template use File | Settings | File Templates.
 */
public class FieldRefConstant extends Constant {

    private short classIndex = -1;

    private short fieldIndex = -1;

    public FieldRefConstant() {
        this.tag = TAG_FIELD_REF_INFO;
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
        this.classIndex = ByteUtil.byteArray2Short(classIndexBytes);
        byte[] fieldIndexBytes = new byte[2];
        System.arraycopy(bytes, startIndex + 3, fieldIndexBytes, 0, 2);
        this.fieldIndex = ByteUtil.byteArray2Short(fieldIndexBytes);
    }

    public int getLength() {
        return 5;
    }

    @Override
    public String toString() {
        ClassConstant classConstant = getClassConstant();
        NameAndTypeConstant utf8Constant = getFieldConstant();
        StringBuilder sb = new StringBuilder("field info: ");
        sb.append(classConstant);
        sb.append("field name:");
        sb.append(utf8Constant);
        return sb.toString();
    }

    public NameAndTypeConstant getFieldConstant() {
        if (fieldIndex < -1) {
            throw new RuntimeException("didn't set field index, can't show field name");
        }
        if (constantList == null) {
            throw new RuntimeException("constants info is empty, can't show field name");
        }
        Constant constant = constantList.getConstant(fieldIndex);
        if (!(constant instanceof NameAndTypeConstant)) {
            throw new RuntimeException("field constant is not NameAndTypeConstant.");
        }
        return (NameAndTypeConstant) constant;
    }

    public ClassConstant getClassConstant() {
        if (classIndex < -1) {
            throw new RuntimeException("didn't set class index, can't show class name");
        }
        if (constantList == null) {
            throw new RuntimeException("constants info is empty, can't show class name");
        }
        Constant constant = constantList.getConstant(classIndex);
        if (!(constant instanceof ClassConstant)) {
            throw new RuntimeException("class constant is not ClassConstant.");
        }
        return (ClassConstant) constant;
    }

    public void setFieldIndex(short fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public void setClassIndex(short classIndex) {
        this.classIndex = classIndex;
    }
}
