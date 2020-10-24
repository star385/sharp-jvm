package com.sharpjvm.bytecode.bean;

import com.sharpjvm.bytecode.bean.attribute.Attribute;
import com.sharpjvm.bytecode.bean.attribute.Code;
import com.sharpjvm.bytecode.bean.constant.Constant;
import com.sharpjvm.bytecode.bean.constant.ConstantList;
import com.sharpjvm.bytecode.bean.constant.Utf8Constant;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.AttributeUtil;
import com.sharpjvm.bytecode.util.ByteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个方法的信息
 *
 * User: zhuguoyin
 * Date: 13-1-29
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class MethodInfo implements ByteArrayable {

    // 访问标志，比如是否public或者abstract等
    private short accessFlags;

    // 方法名字对应的常量索引
    private short nameIndex;

    // 方法名称描述的常量索引
    private short nameDescriptionIndex;

    // 属性数目
    private short attributeCount;

    // 属性
    private List<Attribute> attributeList;

    // 类的所有常量
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

    public Code getCodeAttribute() {
        if (attributeList == null) {
            return null;
        }
        for (Attribute attribute : attributeList) {
            if (Constants.ATTRIBUTE_NAME_CODE.equals(attribute.getNameConstant().getValue())) {
                return (Code) attribute;
            }
        }
        return null;
    }

    public Utf8Constant getNameConstant() {
        return (Utf8Constant) constantList.getConstant(nameIndex);
    }

    public Utf8Constant getDescriptionConstant() {
        return (Utf8Constant) constantList.getConstant(nameDescriptionIndex);
    }

    public String toString() {
        return "method name:" + getNameConstant().getValue() + " description: " + getDescriptionConstant().getValue();
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
