package com.sharpjvm.bytecode.bean;

import com.sharpjvm.bytecode.bean.attribute.Attribute;
import com.sharpjvm.bytecode.bean.constant.Constant;
import com.sharpjvm.bytecode.bean.constant.ConstantList;
import com.sharpjvm.bytecode.bean.constant.Utf8Constant;
import com.sharpjvm.bytecode.util.AttributeUtil;
import com.sharpjvm.bytecode.util.ByteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * һ���ֶε���Ϣ
 *
 * User: zhuguoyin
 * Date: 13-2-5
 * Time: ����12:51
 * To change this template use File | Settings | File Templates.
 */
public class FieldInfo implements ByteArrayable {

    // ���ʱ�־�������Ƿ�public����abstract��
    private short accessFlags;

    // �������ֶ�Ӧ�ĳ�������
    private short nameIndex;

    // �������������ĳ�������
    private short nameDescriptionIndex;

    // ������Ŀ
    private short attributeCount;

    // ����
    private List<Attribute> attributeList;

    // ������г���
    private ConstantList constantList;

    public byte[] toBytes() {
        return new byte[0];
    }

    public void fromBytes(byte[] bytes, int startIndex) {
        int startIndexLocal = startIndex;
        byte[] accessFlagsBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, accessFlagsBytes, 0, 2);
        this.accessFlags = ByteUtil.byteArray2Short(accessFlagsBytes);

        startIndexLocal += 2;
        byte[] nameIndexBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, nameIndexBytes, 0, 2);
        this.nameIndex = ByteUtil.byteArray2Short(nameIndexBytes);

        startIndexLocal += 2;
        byte[] nameDescriptionBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, nameDescriptionBytes, 0, 2);
        this.nameDescriptionIndex = ByteUtil.byteArray2Short(nameDescriptionBytes);

        startIndexLocal += 2;
        byte[] attributeCountBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, attributeCountBytes, 0, 2);
        this.attributeCount = ByteUtil.byteArray2Short(attributeCountBytes);

        startIndexLocal += 2;
        if (attributeCount > 0) {
            attributeList = new ArrayList<Attribute>();
            for (int i = 0; i < attributeCount; i++) {
                byte[] attributeBytes = new byte[2];
                System.arraycopy(bytes, startIndexLocal, attributeBytes, 0, 2);
                short attributeNameIndex = ByteUtil.byteArray2Short(attributeBytes);
                Constant constant = constantList.getConstant(attributeNameIndex);
                if (!(constant instanceof Utf8Constant)) {
                    throw new RuntimeException("byte code format wrong");
                }
                String attributeName = ((Utf8Constant) constant).getValue();
                Attribute attribute = AttributeUtil.createAttributeByName(attributeName);
                attribute.setConstantList(constantList);
                attribute.fromBytes(bytes, startIndexLocal);
                attributeList.add(attribute);
                startIndexLocal += attribute.getLength();
            }

        }
    }

    public Constant getNameConstant() {
        return constantList.getConstant(nameIndex);
    }

    public Constant getDescriptionConstant() {
        return constantList.getConstant(nameDescriptionIndex);
    }

    public int getLength() {
        int length = 0;
        length += 2;
        length += 2;
        length += 2;
        length += 2;
        if (attributeList == null) {
            return length;
        }
        for (Attribute attribute : attributeList) {
            length += attribute.getLength();
        }
        return length;
    }

    public short getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(short accessFlags) {
        this.accessFlags = accessFlags;
    }

    public short getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(short nameIndex) {
        this.nameIndex = nameIndex;
    }

    public short getNameDescriptionIndex() {
        return nameDescriptionIndex;
    }

    public void setNameDescriptionIndex(short nameDescriptionIndex) {
        this.nameDescriptionIndex = nameDescriptionIndex;
    }

    public short getAttributeCount() {
        return attributeCount;
    }

    public void setAttributeCount(short attributeCount) {
        this.attributeCount = attributeCount;
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public void setConstantList(ConstantList constantList) {
        this.constantList = constantList;
    }
}
