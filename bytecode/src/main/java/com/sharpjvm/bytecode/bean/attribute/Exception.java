package com.sharpjvm.bytecode.bean.attribute;

import com.sharpjvm.bytecode.util.ByteUtil;

/**
 * “Ï≥£ Ù–‘
 *
 * User: zhuguoyin
 * Date: 13-2-6
 * Time: œ¬ŒÁ6:00
 * To change this template use File | Settings | File Templates.
 */
public class Exception extends Attribute {

    private short numberOfExceptions;

    private short[] exceptionIndexTable;

    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void parseOtherInfo(byte[] bytes, int index) {
        byte[] numberOfExceptionsBytes = new byte[2];
        System.arraycopy(bytes, index, numberOfExceptionsBytes, 0, 2);
        this.numberOfExceptions = ByteUtil.byteArray2Short(numberOfExceptionsBytes);

        if (numberOfExceptions <= 0) {
            return;
        }

        this.exceptionIndexTable = new short[numberOfExceptions];
        for (int i = 0; i < numberOfExceptions; i++) {
            byte[] exceptionIndexBytes = new byte[2];
            System.arraycopy(bytes, index + 2 + 2 * i, exceptionIndexBytes, 0, 2);
            exceptionIndexTable[i] = ByteUtil.byteArray2Short(exceptionIndexBytes);
        }
    }

    public short getNumberOfExceptions() {
        return numberOfExceptions;
    }

    public void setNumberOfExceptions(short numberOfExceptions) {
        this.numberOfExceptions = numberOfExceptions;
    }

    public short[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public void setExceptionIndexTable(short[] exceptionIndexTable) {
        this.exceptionIndexTable = exceptionIndexTable;
    }
}
