package com.sharpjvm.bytecode.bean.attribute;

import com.sharpjvm.bytecode.bean.ByteArrayable;
import com.sharpjvm.bytecode.util.ByteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 栈帧中局部变量表的变量与java源码中定义的变量之间的关系。
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: 上午9:34
 * To change this template use File | Settings | File Templates.
 */
public class LocalVariableTable extends Attribute {

    /**
     * 描述一个栈帧中局部变量表的变量与java源码中定义的变量之间的关系
     */
    public static class LocalVariableInfo implements ByteArrayable {

        // 变量生命周期的开始
        private short startPc;

        // 变量生命周期覆盖长度
        private short infoLength;

        // 源码中变量名称索引
        private short nameIndex;

        // 源码变量描述符索引
        private short descriptorIndex;

        // 局部变量在栈帧局部变量表中slot的位置
        private short index;

        public byte[] toBytes() {
            return new byte[0];
        }

        public void fromBytes(byte[] bytes, int startIndex) {
            if (bytes == null || bytes.length < startIndex + getLength()) {
                throw new RuntimeException("bytes length wrong!");
            }
            byte[] startPcBytes = new byte[2];
            System.arraycopy(bytes, startIndex, startPcBytes, 0, 2);
            this.startPc = ByteUtil.byteArray2Short(startPcBytes);
            byte[] infoLengthBytes = new byte[2];
            System.arraycopy(bytes, startIndex + 2, infoLengthBytes, 0, 2);
            this.infoLength = ByteUtil.byteArray2Short(infoLengthBytes);

            byte[] nameIndexBytes = new byte[2];
            System.arraycopy(bytes, startIndex + 2 + 2, nameIndexBytes, 0, 2);
            this.nameIndex = ByteUtil.byteArray2Short(nameIndexBytes);

            byte[] descriptorIndexBytes = new byte[2];
            System.arraycopy(bytes, startIndex + 2 + 2 + 2, descriptorIndexBytes, 0, 2);
            this.descriptorIndex = ByteUtil.byteArray2Short(descriptorIndexBytes);

            byte[] indexBytes = new byte[2];
            System.arraycopy(bytes, startIndex + 2 + 2 + 2 + 2, indexBytes, 0, 2);
            this.index = ByteUtil.byteArray2Short(indexBytes);
        }

        public int getLength() {
            // 四个属性均为2字节
            return 2 + 2 + 2 + 2 + 2;
        }

        public short getStartPc() {
            return startPc;
        }

        public void setStartPc(short startPc) {
            this.startPc = startPc;
        }

        public short getInfoLength() {
            return infoLength;
        }

        public void setInfoLength(short infoLength) {
            this.infoLength = infoLength;
        }

        public short getNameIndex() {
            return nameIndex;
        }

        public void setNameIndex(short nameIndex) {
            this.nameIndex = nameIndex;
        }

        public short getDescriptorIndex() {
            return descriptorIndex;
        }

        public void setDescriptorIndex(short descriptorIndex) {
            this.descriptorIndex = descriptorIndex;
        }

        public short getIndex() {
            return index;
        }

        public void setIndex(short index) {
            this.index = index;
        }
    }

    private short localVariableTableCount;

    private List<LocalVariableInfo> localVariableInfoList;

    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void parseOtherInfo(byte[] bytes, int startIndex) {
        byte[] localVariableCountBytes = new byte[2];
        System.arraycopy(bytes, startIndex, localVariableCountBytes, 0, 2);
        this.localVariableTableCount = ByteUtil.byteArray2Short(localVariableCountBytes);

        // 没有行号的对应关系，无需再解析
        if (localVariableTableCount <= 0) {
            return;
        }

        // 边界校验
        if (bytes.length < getLength()) {
            throw new RuntimeException("bytes length wrong");
        }

        this.localVariableInfoList = new ArrayList<LocalVariableInfo>();
        for (int i = 0; i < localVariableTableCount; i++) {
            LocalVariableInfo localVariableInfo = new LocalVariableInfo();
            // 除去nameIndex的两个字节，attributeLength的4个字节，再加上localVariableTableCount的两个字节，每个LocalVariableInfo占10个字节，所以每次偏移量加10
            localVariableInfo.fromBytes(bytes, startIndex + 2 + i * 10);
            localVariableInfoList.add(localVariableInfo);
        }
    }

    public int getLength() {
        return 2 + 4 + 2 + 10 * localVariableTableCount;
    }

    public short getLocalVariableTableCount() {
        return localVariableTableCount;
    }

    public void setLocalVariableTableCount(short localVariableTableCount) {
        this.localVariableTableCount = localVariableTableCount;
    }

    public List<LocalVariableInfo> getLocalVariableInfoList() {
        return localVariableInfoList;
    }

    public void setLocalVariableInfoList(List<LocalVariableInfo> localVariableInfoList) {
        this.localVariableInfoList = localVariableInfoList;
    }
}
