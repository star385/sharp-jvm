package com.sharpjvm.bytecode.bean.attribute;

import com.sharpjvm.bytecode.bean.ByteArrayable;
import com.sharpjvm.bytecode.util.ByteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 记录内部类与宿主类之间关联的数据结构。
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 */
public class InnerClasses extends Attribute {

    /**
     * 记录一个内部类与宿主类之间关联的数据结构
     */
    public static class InnerClassInfo implements ByteArrayable {

        private short innerClassInfoIndex;

        private short outerClassInfoIndex;

        private short innerNameIndex;

        private short innerClassAccessFlags;

        public byte[] toBytes() {
            return new byte[0];
        }

        public void fromBytes(byte[] bytes, int startIndex) {
            if (bytes == null || bytes.length < startIndex + getLength()) {
                throw new RuntimeException("bytes length wrong!");
            }
            byte[] innerClassInfoIndexBytes = new byte[2];
            System.arraycopy(bytes, startIndex, innerClassInfoIndexBytes, 0, 2);
            this.innerClassInfoIndex = ByteUtil.byteArray2Short(innerClassInfoIndexBytes);

            byte[] outerClassInfoIndexBytes = new byte[2];
            System.arraycopy(bytes, startIndex + 2, outerClassInfoIndexBytes, 0, 2);
            this.outerClassInfoIndex = ByteUtil.byteArray2Short(outerClassInfoIndexBytes);

            byte[] innerNameIndexBytes = new byte[2];
            System.arraycopy(bytes, startIndex + 2 + 2, innerNameIndexBytes, 0, 2);
            this.innerNameIndex = ByteUtil.byteArray2Short(innerNameIndexBytes);

            byte[] innerClassAccessFlagsBytes = new byte[2];
            System.arraycopy(bytes, startIndex + 2 + 2 + 2, innerClassAccessFlagsBytes, 0, 2);
            this.innerClassAccessFlags = ByteUtil.byteArray2Short(innerClassAccessFlagsBytes);

        }

        public int getLength() {
            // 四个2个字节的
            return 2 + 2 + 2 + 2;
        }

        public short getInnerClassInfoIndex() {
            return innerClassInfoIndex;
        }

        public void setInnerClassInfoIndex(short innerClassInfoIndex) {
            this.innerClassInfoIndex = innerClassInfoIndex;
        }

        public short getOuterClassInfoIndex() {
            return outerClassInfoIndex;
        }

        public void setOuterClassInfoIndex(short outerClassInfoIndex) {
            this.outerClassInfoIndex = outerClassInfoIndex;
        }

        public short getInnerNameIndex() {
            return innerNameIndex;
        }

        public void setInnerNameIndex(short innerNameIndex) {
            this.innerNameIndex = innerNameIndex;
        }

        public short getInnerClassAccessFlags() {
            return innerClassAccessFlags;
        }

        public void setInnerClassAccessFlags(short innerClassAccessFlags) {
            this.innerClassAccessFlags = innerClassAccessFlags;
        }
    }

    private short innerClassCount;

    private List<InnerClassInfo> innerClassInfoList;

    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void parseOtherInfo(byte[] bytes, int startIndex) {
        byte[] innerClassInfoCountBytes = new byte[2];
        System.arraycopy(bytes, startIndex, innerClassInfoCountBytes, 0, 2);
        this.innerClassCount = ByteUtil.byteArray2Short(innerClassInfoCountBytes);

        // 没有行号的对应关系，无需再解析
        if (innerClassCount <= 0) {
            return;
        }

        // 边界校验
        if (bytes.length < getLength()) {
            throw new RuntimeException("bytes length wrong");
        }

        this.innerClassInfoList = new ArrayList<InnerClassInfo>();
        for (int i = 0; i < innerClassCount; i++) {
            InnerClassInfo lineNumberInfo = new InnerClassInfo();
            lineNumberInfo.fromBytes(bytes, startIndex + 2 + i * 8);
            innerClassInfoList.add(lineNumberInfo);
        }
    }

    public short getInnerClassCount() {
        return innerClassCount;
    }

    public void setInnerClassCount(short innerClassCount) {
        this.innerClassCount = innerClassCount;
    }

    public List<InnerClassInfo> getInnerClassInfoList() {
        return innerClassInfoList;
    }

    public void setInnerClassInfoList(List<InnerClassInfo> innerClassInfoList) {
        this.innerClassInfoList = innerClassInfoList;
    }
}
