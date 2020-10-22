package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.bean.ByteArrayable;
import com.sharpjvm.bytecode.util.ConstantUtil;

/**
 * Ϊ���������ض���Ƶ��࣬����ֽ���ṹ����һЩ����ϰ�ߵĸ��ġ�
 *
 * User: zhuguoyin
 * Date: 13-2-6
 * Time: ����5:33
 * To change this template use File | Settings | File Templates.
 */
public class ConstantList implements ByteArrayable {

    private int index = 1;

    /**
     * ����java�ֽ����ϰ�߶����һ����ų����Ŀռ䣬��һ����Ҫ�����±�1��ʼ
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
