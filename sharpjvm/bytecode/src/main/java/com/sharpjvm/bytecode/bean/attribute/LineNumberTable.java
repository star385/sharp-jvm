package com.sharpjvm.bytecode.bean.attribute;

import com.sharpjvm.bytecode.bean.ByteArrayable;
import com.sharpjvm.bytecode.util.ByteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 字节码行号和源码行号的对应关系
 *
 * User: zhuguoyin
 * Date: 13-2-6
 * Time: 下午6:03
 * To change this template use File | Settings | File Templates.
 */
public class LineNumberTable extends Attribute {

    /**
     * 字节码行号和源码行号的对应关系，一个code属性可能有多个对应关系，这是一个对应关系的数据结构
     */
    public static class LineNumberInfo implements ByteArrayable {

        // 字节码行号
        private short startPc;

        // 源码行号
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
            // LineNumberInfo拥有startPc和lineNumber，分别是两字节，总共4字节
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

        // 没有行号的对应关系，无需再解析
        if (lineNumberInfoCount <= 0) {
            return;
        }

        // 边界校验
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
