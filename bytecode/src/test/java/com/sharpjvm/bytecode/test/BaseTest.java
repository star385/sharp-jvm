package com.sharpjvm.bytecode.test;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.FieldInfo;
import com.sharpjvm.bytecode.bean.MethodInfo;
import com.sharpjvm.bytecode.bean.attribute.Attribute;
import com.sharpjvm.bytecode.bean.constant.Constant;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhuguoyin
 * Date: 13-2-8
 * Time: ÏÂÎç12:55
 * To change this template use File | Settings | File Templates.
 */
public class BaseTest {

    public static String classInfo2String(ClassInfo classInfo) {
        StringBuilder sb = new StringBuilder("class info:\n");
        byte [] magic = classInfo.getMagic();
        sb.append("\t" + "magic:" + toHexString(magic) + "\n");
        sb.append("\t minor version:" + classInfo.getMinorVersion() + "\n");
        sb.append("\t major version:" + classInfo.getMajorVersion() + "\n");
        sb.append("\t constant pool count:" + classInfo.getConstantCount() + "\n");
        for (int i = 1; i < classInfo.getConstantCount(); i++) {
            sb.append("\t\t constant " + i + ": " + classInfo.getConstantList().getConstant(i) + "\n");
        }
        sb.append("\t access description:" + getAccessDescription(classInfo.getAccessFlags()) + "\n");
        sb.append("\t class name:" + classInfo.getThisClassConstant() + "\n");
        sb.append("\t parent class name:" + classInfo.getSuperClassConstant() + "\n");

        sb.append("\t interface count:" + classInfo.getInterfaceCount() + "\n");
        if (classInfo.getInterfaceClassConstants() != null) {
            for (Constant constant : classInfo.getInterfaceClassConstants())  {
                sb.append("\t\t" + constant + "\n");
            }
        }

        sb.append("\t field count:" + classInfo.getFieldCount() + "\n");
        if (classInfo.getFieldInfoList() != null) {
            for (FieldInfo fieldInfo : classInfo.getFieldInfoList())  {
                sb.append(field2String(fieldInfo));
            }
        }

        sb.append("\t method count:" + classInfo.getMethodCount() + "\n");
        if (classInfo.getMethodInfoList() != null) {
            for (MethodInfo methodInfo : classInfo.getMethodInfoList())  {
                sb.append("\t method *** start" + "\n");
                sb.append(method2String(methodInfo));
                sb.append("\tmethod *** end" + "\n");
            }
        }

        sb.append("\t attribute count:" + classInfo.getAttributeCount() + "\n");
        sb.append(attributes2String(classInfo.getAttributeList(), 1));
        return sb.toString();
    }

    public static String field2String(FieldInfo fieldInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t field name:" + fieldInfo.getNameConstant() + "\n");
        sb.append("\t\t field description:" + fieldInfo.getDescriptionConstant() + "\n");
        sb.append("\t\t access description:" + getAccessDescription(fieldInfo.getAccessFlags()) + "\n");
        sb.append("\t\t attribute count:" + fieldInfo.getAttributeCount() + "\n");
        sb.append(attributes2String(fieldInfo.getAttributeList(), 2));
        return sb.toString();
    }

    public static String method2String(MethodInfo methodInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t method name:" + methodInfo.getNameConstant() + "\n");
        sb.append("\t\t method description:" + methodInfo.getDescriptionConstant() + "\n");
        sb.append("\t\t access description:" + getAccessDescription(methodInfo.getAccessFlags()) + "\n");
        sb.append("\t\t attribute count:" + methodInfo.getAttributeCount() + "\n");
        sb.append(attributes2String(methodInfo.getAttributeList(), 2));
        return sb.toString();
    }

    private static Object attributes2String(List<Attribute> attributeList, int tabCount) {
        if (attributeList == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Attribute attribute : attributeList) {
            for (int i = 0; i < tabCount; i++) {
                sb.append("\t");
            }
            sb.append("\t attribute " + attribute + "\n");
        }
        return sb.toString();
    }

    public static String getAccessDescription(short accessFlags) {
        StringBuilder sb = new StringBuilder();
        if (Modifier.isAbstract(accessFlags)) {
            sb.append("abstract  ");
        }
        if (Modifier.isPublic(accessFlags)) {
            sb.append("public  ");
        }
        if (Modifier.isFinal(accessFlags)) {
            sb.append("final  ");
        }
        if (Modifier.isInterface(accessFlags)) {
            sb.append("interface  ");
        }
        if (Modifier.isNative(accessFlags)) {
            sb.append("native  ");
        }
        if (Modifier.isPrivate(accessFlags)) {
            sb.append("private  ");
        }
        if (Modifier.isProtected(accessFlags)) {
            sb.append("protected  ");
        }
        if (Modifier.isStatic(accessFlags)) {
            sb.append("static  ");
        }
        if (Modifier.isStrict(accessFlags)) {
            sb.append("strict  ");
        }
        if (Modifier.isSynchronized(accessFlags)) {
            sb.append("synchronized  ");
        }
        if (Modifier.isTransient(accessFlags)) {
            sb.append("transient  ");
        }
        if (Modifier.isVolatile(accessFlags)) {
            sb.append("volatile  ");
        }
        return sb.toString();
    }

    public static String toHexString(byte[] magic) {
        StringBuilder sb = new StringBuilder();
        for (byte b : magic) {
            sb.append(Integer.toHexString(b).substring(6));
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
