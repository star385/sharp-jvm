package com.sharpjvm.bytecode.bean.attribute;

import com.sharpjvm.bytecode.bean.ByteArrayable;
import com.sharpjvm.bytecode.util.ByteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ջ֡�оֲ�������ı�����javaԴ���ж���ı���֮��Ĺ�ϵ��
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: ����9:34
 * To change this template use File | Settings | File Templates.
 */
public class LocalVariableTable extends Attribute {

    /**
     * ����һ��ջ֡�оֲ�������ı�����javaԴ���ж���ı���֮��Ĺ�ϵ
     */
    public static class LocalVariableInfo implements ByteArrayable {

        // �����������ڵĿ�ʼ
        private short startPc;

        // �����������ڸ��ǳ���
        private short infoLength;

        // Դ���б�����������
        private short nameIndex;

        // Դ���������������
        private short descriptorIndex;

        // �ֲ�������ջ֡�ֲ���������slot��λ��
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
            // �ĸ����Ծ�Ϊ2�ֽ�
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

        // û���кŵĶ�Ӧ��ϵ�������ٽ���
        if (localVariableTableCount <= 0) {
            return;
        }

        // �߽�У��
        if (bytes.length < getLength()) {
            throw new RuntimeException("bytes length wrong");
        }

        this.localVariableInfoList = new ArrayList<LocalVariableInfo>();
        for (int i = 0; i < localVariableTableCount; i++) {
            LocalVariableInfo localVariableInfo = new LocalVariableInfo();
            // ��ȥnameIndex�������ֽڣ�attributeLength��4���ֽڣ��ټ���localVariableTableCount�������ֽڣ�ÿ��LocalVariableInfoռ10���ֽڣ�����ÿ��ƫ������10
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
