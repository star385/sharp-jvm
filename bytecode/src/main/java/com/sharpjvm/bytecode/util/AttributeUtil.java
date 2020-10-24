package com.sharpjvm.bytecode.util;

import com.sharpjvm.bytecode.bean.attribute.*;
import com.sharpjvm.bytecode.bean.attribute.Exception;
import com.sharpjvm.bytecode.constants.Constants;

/**
 * 关于属性的工具类。
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
public class AttributeUtil implements Constants {

    public static Attribute createAttributeByName(String attributeName) {
        if (ATTRIBUTE_NAME_CODE.equals(attributeName)) {
            return new Code();
        } else if (ATTRIBUTE_NAME_CONSTANT_VALUE.equals(attributeName)) {
            return new ConstantValue();
        } else if (ATTRIBUTE_NAME_DEPRECATED.equals(attributeName)) {
            return new com.sharpjvm.bytecode.bean.attribute.Deprecated();
        } else if (ATTRIBUTE_NAME_EXCEPTIONS.equals(attributeName)) {
            return new Exception();
        } else if (ATTRIBUTE_NAME_INNER_CLASSES.equals(attributeName)) {
            return new InnerClasses();
        } else if (ATTRIBUTE_NAME_LINE_NUMBER_TABLE.equals(attributeName)) {
            return new LineNumberTable();
        } else if (ATTRIBUTE_NAME_LOCAL_VARIABLE_TABLE.equals(attributeName)) {
            return new LocalVariableTable();
        } else if (ATTRIBUTE_NAME_SOURCE_FILE.equals(attributeName)) {
            return new SourceFile();
        } else if (ATTRIBUTE_NAME_SYNTHETIC.equals(attributeName)) {
            return new Synthetic();
        }
        return new UnknownAttribute();
    }
}
