package com.sharpjvm.bytecode.bean;

import com.sharpjvm.bytecode.bean.attribute.Attribute;
import com.sharpjvm.bytecode.bean.constant.ClassConstant;
import com.sharpjvm.bytecode.bean.constant.Constant;
import com.sharpjvm.bytecode.bean.constant.ConstantList;
import com.sharpjvm.bytecode.bean.constant.Utf8Constant;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.AttributeUtil;
import com.sharpjvm.bytecode.util.ByteUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个字节码的信息
 *
 * User: zhuguoyin
 * Date: 13-1-24
 * Time: 下午8:16
 * To change this template use File | Settings | File Templates.
 */
public class ClassInfo implements ByteArrayable, Serializable {

    // 源码文件
    private String sourceFile;

    private byte[] magic;

    // 次版本号
    private short minorVersion;

    // 主版本号
    private short majorVersion;

    // 常量数目，是实际常量数目+1
    private short constantCount;

    // 所有常量
    private ConstantList constantList;

    // 访问标志，比如是否public或者abstract等
    private short accessFlags;

    // 本类的索引
    private short thisClassIndex;

    // 本类对应的常量
    private Constant thisClassConstant;

    // 父类的索引
    private short superClassIndex;

    // 父类对应的常量
    private Constant superClassConstant;

    // 实现接口的数目
    private short interfaceCount;

    // 实现的接口的索引
    private short[] interfaceClassIndexes;

    // 实现的接口所对应的常量
    private List<Constant> interfaceClassConstants;

    // 属性数目
    private short fieldCount;

    // 属性列表
    private List<FieldInfo> fieldInfoList;

    // 方法数目
    private short methodCount;

    // 方法列表
    private List<MethodInfo> methodInfoList;

    // 属性数目
    private short attributeCount;

    // 属性列表
    private List<Attribute> attributeList;

    public byte[] toBytes() {
        return new byte[0];
    }

    public void fromBytes(byte[] bytes, int startIndex) {
        int startIndexLocal = 0;
        byte [] magicDigital = new byte[4];
        System.arraycopy(bytes, startIndexLocal, magicDigital, 0, 4);
        if (!ByteUtil.contentEquals(magicDigital, Constants.MAGIC_DIGITAL)) {
            throw new RuntimeException("byte code format wrong!");
        }
        this.magic = magicDigital;

        startIndexLocal += 4;
        byte [] minorVersionBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, minorVersionBytes, 0, 2);
        this.minorVersion = ByteUtil.byteArray2Short(minorVersionBytes);

        startIndexLocal += 2;
        byte [] majorVersionBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, majorVersionBytes, 0, 2);
        this.majorVersion = ByteUtil.byteArray2Short(majorVersionBytes);

        startIndexLocal += 2;
        byte [] constantCountBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, constantCountBytes, 0, 2);
        this.constantCount = ByteUtil.byteArray2Short(constantCountBytes);

        startIndexLocal += 2;
        constantList = new ConstantList();
        constantList.setConstantCount(constantCount);
        constantList.fromBytes(bytes, startIndexLocal);

        startIndexLocal += constantList.getLength();
        byte[] accessFlagsBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, accessFlagsBytes, 0, 2);
        this.accessFlags = ByteUtil.byteArray2Short(accessFlagsBytes);

        startIndexLocal += 2;
        byte[] thisClassIndexBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, thisClassIndexBytes, 0, 2);
        this.thisClassIndex = ByteUtil.byteArray2Short(thisClassIndexBytes);
        this.thisClassConstant = constantList.getConstant(thisClassIndex);

        startIndexLocal += 2;
        byte[] superClassIndexBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, superClassIndexBytes, 0, 2);
        this.superClassIndex = ByteUtil.byteArray2Short(superClassIndexBytes);
        this.superClassConstant = constantList.getConstant(superClassIndex);

        startIndexLocal += 2;
        byte[] interfaceCountBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, interfaceCountBytes, 0, 2);
        this.interfaceCount = ByteUtil.byteArray2Short(interfaceCountBytes);

        startIndexLocal += 2;
        if (interfaceCount > 0) {
            this.interfaceClassIndexes = new short[interfaceCount];
            interfaceClassConstants = new ArrayList<Constant>();
            for (int i = 0; i < interfaceCount; i++) {
                byte[] interfaceIndexBytes = new byte[2];
                System.arraycopy(bytes, startIndexLocal, interfaceIndexBytes, 0, 2);
                interfaceClassIndexes[i] = ByteUtil.byteArray2Short(interfaceIndexBytes);
                interfaceClassConstants.add(constantList.getConstant(interfaceClassIndexes[i]));
                startIndexLocal += 2;
            }
        }

        byte[] fieldCountBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, fieldCountBytes, 0, 2);
        this.fieldCount = ByteUtil.byteArray2Short(fieldCountBytes);

        startIndexLocal += 2;
        if (fieldCount > 0) {
            fieldInfoList = new ArrayList<FieldInfo>();
            for (int i = 0; i < fieldCount; i++) {
                FieldInfo fieldInfo = new FieldInfo();
                fieldInfo.setConstantList(constantList);
                fieldInfo.fromBytes(bytes, startIndexLocal);
                fieldInfoList.add(fieldInfo);
                startIndexLocal += fieldInfo.getLength();
            }
        }

        byte[] methodCountBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, methodCountBytes, 0, 2);
        this.methodCount = ByteUtil.byteArray2Short(methodCountBytes);

        startIndexLocal += 2;
        if (methodCount > 0) {
            methodInfoList = new ArrayList<MethodInfo>();
            for (int i = 0; i < methodCount; i++) {
                MethodInfo methodInfo = new MethodInfo();
                methodInfo.setConstantList(constantList);
                methodInfo.fromBytes(bytes, startIndexLocal);
                methodInfoList.add(methodInfo);
                startIndexLocal += methodInfo.getLength();
            }
        }

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

        return 0;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public short getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(short minorVersion) {
        this.minorVersion = minorVersion;
    }

    public short getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(short majorVersion) {
        this.majorVersion = majorVersion;
    }

    public short getConstantCount() {
        return constantCount;
    }

    public void setConstantCount(short constantCount) {
        this.constantCount = constantCount;
    }

    public ConstantList getConstantList() {
        return constantList;
    }

    public void setConstantList(ConstantList constantList) {
        this.constantList = constantList;
    }

    public short getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(short accessFlags) {
        this.accessFlags = accessFlags;
    }

    public Constant getThisClassConstant() {
        return thisClassConstant;
    }

    public void setThisClassConstant(Constant thisClassConstant) {
        this.thisClassConstant = thisClassConstant;
    }

    public ClassConstant getSuperClassConstant() {
        if (superClassConstant instanceof ClassConstant) {
            return (ClassConstant) superClassConstant;
        }
        throw new RuntimeException("super class constant must be type of ClassConstant");
    }

    public void setSuperClassConstant(Constant superClassConstant) {
        this.superClassConstant = superClassConstant;
    }

    public short getInterfaceCount() {
        return interfaceCount;
    }

    public void setInterfaceCount(short interfaceCount) {
        this.interfaceCount = interfaceCount;
    }

    public List<Constant> getInterfaceClassConstants() {
        return interfaceClassConstants;
    }

    public void setInterfaceClassConstants(List<Constant> interfaceClassConstants) {
        this.interfaceClassConstants = interfaceClassConstants;
    }

    public short getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(short fieldCount) {
        this.fieldCount = fieldCount;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(short methodCount) {
        this.methodCount = methodCount;
    }

    public List<FieldInfo> getFieldInfoList() {
        return fieldInfoList;
    }

    public void setFieldInfoList(List<FieldInfo> fieldInfoList) {
        this.fieldInfoList = fieldInfoList;
    }

    public List<MethodInfo> getMethodInfoList() {
        return methodInfoList;
    }

    public void setMethodInfoList(List<MethodInfo> methodInfoList) {
        this.methodInfoList = methodInfoList;
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

    public short getThisClassIndex() {
        return thisClassIndex;
    }

    public void setThisClassIndex(short thisClassIndex) {
        this.thisClassIndex = thisClassIndex;
    }

    public short getSuperClassIndex() {
        return superClassIndex;
    }

    public void setSuperClassIndex(short superClassIndex) {
        this.superClassIndex = superClassIndex;
    }

    public short[] getInterfaceClassIndexes() {
        return interfaceClassIndexes;
    }

    public void setInterfaceClassIndexes(short[] interfaceClassIndexes) {
        this.interfaceClassIndexes = interfaceClassIndexes;
    }

    public String getClassName() {
    	ClassConstant classConstant = (ClassConstant) constantList.getConstant(thisClassIndex);
        return classConstant.getNameConstant().getValue();
    }

    public byte[] getMagic() {
        return magic;
    }

    public String toString() {
        String description = "ClassInfo:";
        if (thisClassConstant == null) {
            description += "";
        } else {
            description += thisClassConstant;
        }
        return description;
    }
}
