package com.sharpjvm.bytecode.bean.attribute;

import com.sharpjvm.bytecode.bean.ByteArrayable;
import com.sharpjvm.bytecode.util.ByteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * �ֽ����кź�Դ���кŵĶ�Ӧ��ϵ
 *
 * User: zhuguoyin
 * Date: 13-2-6
 * Time: ����6:03
 * To change this template use File | Settings | File Templates.
 */
public class LineNumberTable extends Attribute {

    /**
     * �ֽ����кź�Դ���кŵĶ�Ӧ��ϵ��һ��code���Կ����ж����Ӧ��ϵ������һ����Ӧ��ϵ�����ݽṹ
     */
    public static class LineNumberInfo implements ByteArrayable {

        // �ֽ����к�
        private short startPc;

        // Դ���к�
        private short lineNumber;

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
            byte[] lineNumberBytes = new byte[2];
            System.arraycopy(bytes, startIndex + 2, lineNumberBytes, 0, 2);
            this.lineNumber = ByteUtil.byteArray2Short(lineNumberBytes);
        }

        public int getLength() {
            // LineNumberInfoӵ��startPc��lineNumber���ֱ������ֽڣ��ܹ�4�ֽ�
            return 4;
        }

        public short getStartPc() {
            return startPc;
        }

        public void setStartPc(short startPc) {
            this.startPc = startPc;
        }

        public short getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(short lineNumber) {
            this.lineNumber = lineNumber;
        }
    }

    private short lineNumberInfoCount;

    private List<LineNumberInfo> lineNumberInfoList;

    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void parseOtherInfo(byte[] bytes, int startIndex) {
        byte[] lineNumberInfoCountBytes = new byte[2];
        System.arraycopy(bytes, startIndex, lineNumberInfoCountBytes, 0, 2);
        this.lineNumberInfoCount = ByteUtil.byteArray2Short(lineNumberInfoCountBytes);

        // û���кŵĶ�Ӧ��ϵ�������ٽ���
        if (lineNumberInfoCount <= 0) {
            return;
        }

        // �߽�У��
        if (bytes.length < getLength()) {
            throw new RuntimeException("bytes length wrong");
        }

        this.lineNumberInfoList = new ArrayList<LineNumberInfo>();
        for (int i = 0; i < lineNumberInfoCount; i++) {
            LineNumberInfo lineNumberInfo = new LineNumberInfo();
            lineNumberInfo.fromBytes(bytes, startIndex + 2 + i * 4);
            lineNumberInfoList.add(lineNumberInfo);
        }
    }

    public short getLineNumberInfoCount() {
        return lineNumberInfoCount;
    }

    public void setLineNumberInfoCount(short lineNumberInfoCount) {
        this.lineNumberInfoCount = lineNumberInfoCount;
    }

    public List<LineNumberInfo> getLineNumberInfoList() {
        return lineNumberInfoList;
    }

    public void setLineNumberInfoList(List<LineNumberInfo> lineNumberInfoList) {
        this.lineNumberInfoList = lineNumberInfoList;
    }
}
