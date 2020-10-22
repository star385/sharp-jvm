package com.sharpjvm.bytecode.util;

import com.sharpjvm.bytecode.bean.constant.*;
import com.sharpjvm.bytecode.constants.Constants;

/**
 * 关于常量的工具类
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: 下午3:56
 * To change this template use File | Settings | File Templates.
 */
public class ConstantUtil implements Constants {

    public static Constant createConstantByType(byte type) {
        if (type == TAG_UTF8_INFO) {
            return new Utf8Constant();
        } else if (type == TAG_LONG_INFO) {
            return new LongConstant();
        } else if (type == TAG_INTEGER_INFO) {
            return new IntegerConstant();
        } else if (type == TAG_FLOAT_INFO) {
            return new FloatConstant();
        } else if (type == TAG_DOUBLE_INFO) {
            return new DoubleConstant();
        } else if (type == TAG_CLASS_INFO) {
            return new ClassConstant();
        } else if (type == TAG_STRING_INFO) {
            return new StringConstant();
        } else if (type == TAG_FIELD_REF_INFO) {
            return new FieldRefConstant();
        } else if (type == TAG_METHOD_REF_INFO) {
            return new MethodRefConstant();
        } else if (type == TAG_INTERFACE_METHOD_REF_INFO) {
            return new InterfaceMethodRefConstant();
        } else if (type == TAG_NAME_AND_TYPE_INFO) {
            return new NameAndTypeConstant();
        }
        throw new RuntimeException("unknown constant type");
    }
}
