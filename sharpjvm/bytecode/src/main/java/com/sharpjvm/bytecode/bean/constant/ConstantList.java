package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.bean.ByteArrayable;
import com.sharpjvm.bytecode.util.ConstantUtil;

/**
 * 为解析常量池而设计的类，针对字节码结构做了一些不合习惯的更改。
 *
 * User: zhuguoyin
 * Date: 13-2-6
 * Time: 下午5:33
 * To change this template use File | Settings | File Templates.
 */
public class ConstantList implements ByteArrayable {

    private int index = 1;

    /**
     * 按照java字节码的习惯多分配一个存放常量的空间，第一个不要，从下标1开始
     */
    private Constant[] constants;

    private short constantCount;

    public void setConstantCount(short count) {
        if (count < 2) {
            throw new ArrayIndexOutOfBoundsException("count can't below 2 : " + count);
        }
        constants = new Constant[count];
        this.constantCount = count;
    }

    private void addConstant(Constant constant) {
//        if (constants == null) {
//            throw new RuntimeException("didn't set constant count!");
//        }

        if (index > constants.length - 1) {
            throw new RuntimeException("index can't bigger than constant length");
        }
        constants[index++] = constant;
    }

    public Constant getConstant(int index) {
        return constants[index];
    }

    public byte[] toBytes() {
        return new byte[0];
    }

    public void fromBytes(byte[] bytes, int startIndex) {
        int startIndexLocal = startIndex;
        for (int i = 0; i < constantCount - 1; i++) {
            byte type = bytes[startIndexLocal];
            Constant constant = ConstantUtil.createConstantByType(type);
            constant.setConstantList(this);
            constant.fromBytes(bytes, startIndexLocal);
            addConstant(constant);
            if (constant instanceof LongConstant ||
                    constant instanceof DoubleConstant) {
                addConstant(null);
                i++;
            }
            startIndexLocal += constant.getLength();
        }
    }

    public int getLength() {
        if (constants == null) {
            return 0;
        }
        int length = 0;
        for (Constant constant : constants) {
            if (constant != null) {
                length += constant.getLength();
            }
        }
        return length;
    }
}
