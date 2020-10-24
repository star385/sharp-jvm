package com.sharpjvm.interpreter.constant;

/**
 * ������ģ���õ��ĸ��ֳ���
 *
 * User: zhuguoyin
 * Date: 13-2-8
 * Time: ����4:22
 * To change this template use File | Settings | File Templates.
 */
public interface Constants {

    // main��������
    String MAIN_METHOD_NAME = "main";

    // main������String�������
    String STRING_ARRAY_PARAMETER = "([Ljava/lang/String;)";

    // ����ֵΪvoid
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
