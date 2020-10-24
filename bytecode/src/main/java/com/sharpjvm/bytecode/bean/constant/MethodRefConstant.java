package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * Created by IntelliJ IDEA.
 * User: zhuguoyin
 * Date: 13-1-29
 * Time: обнГ1:51
 * To change this template use File | Settings | File Templates.
 */
public class MethodRefConstant extends Constant {

    private short classIndex = -1;

    private short methodIndex = -1;

    public MethodRefConstant() {
        this.tag = TAG_METHOD_REF_INFO;
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
        this.methodIndex = ByteUtil.byteArray2Short(fieldIndexBytes);
    }

    public int getLength() {
        return 5;
    }


    @Override
    public String toString() {
        ClassConstant classConstant = getClassConstant();
        NameAndTypeConstant utf8Constant = getMethodConstant();
        StringBuilder sb = new StringBuilder("method info: ");
        sb.append(classConstant);
        sb.append("method name:");
        sb.append(utf8Constant);
        return sb.toString();
    }

    public NameAndTypeConstant getMethodConstant() {
        if (methodIndex < -1) {
            throw new RuntimeException("didn't set field index, can't show field name");
        }
        if (constantList == null) {
            throw new RuntimeException("constants info is empty, can't show field name");
        }
        Constant constant = constantList.getConstant(methodIndex);
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

    public void setMethodIndex(short methodIndex) {
        this.methodIndex = methodIndex;
    }

    public void setClassIndex(short classIndex) {
        this.classIndex = classIndex;
    }
}
