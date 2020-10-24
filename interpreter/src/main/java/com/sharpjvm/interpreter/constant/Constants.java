package com.sharpjvm.interpreter.constant;

/**
 * 解释器模块用到的各种常量
 *
 * User: zhuguoyin
 * Date: 13-2-8
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
public interface Constants {

    // main方法名称
    String MAIN_METHOD_NAME = "main";

    // main方法的String数组参数
    String STRING_ARRAY_PARAMETER = "([Ljava/lang/String;)";

    // 返回值为void
    String VOID_RETURN_TYPE = "V";

    String METHOD_PARAMETERS_PREFIX = "(";

    String METHOD_PARAMETERS_POSTFIX = ")";

    String ARRAY_PREFIX = "[";

    String PARAMETER_SEPARATOR = ";";

    String BYTE_ABRIDGE = "B";

    String CHAR_ABRIDGE = "C";

    String DOUBLE_ABRIDGE = "D";

    String FLOAT_ABRIDGE = "F";

    String INT_ABRIDGE = "I";

    String LONG_ABRIDGE = "J";

    String SHORT_ABRIDGE = "S";

    String BOOLEAN_ABRIDGE = "Z";

    String VOID_ABRIDGE = "V";

    String OBJECT_ABRIDGE = "L";
}
