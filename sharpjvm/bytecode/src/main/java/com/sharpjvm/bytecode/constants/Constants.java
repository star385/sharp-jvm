package com.sharpjvm.bytecode.constants;

/**
 * 常量类，放置的是各类常量
 *
 * User: zhuguoyin
 * Date: 13-2-6
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
public interface Constants {

    //  Constant types start
    //  各种常量类型，解析常量池的时候第一个字节是常量的类型，此常量和本类这个常量不同，请注意甄别

    // utf8类型
    byte TAG_UTF8_INFO = 1;

    // int类型
    byte TAG_INTEGER_INFO = 3;

    // float类型
    byte TAG_FLOAT_INFO = 4;

    // long类型
    byte TAG_LONG_INFO = 5;

    // double
    byte TAG_DOUBLE_INFO = 6;

    // class
    byte TAG_CLASS_INFO = 7;

    // string
    byte TAG_STRING_INFO = 8;

    // field ref
    byte TAG_FIELD_REF_INFO = 9;

    // method ref
    byte TAG_METHOD_REF_INFO = 10;

    // interface method ref
    byte TAG_INTERFACE_METHOD_REF_INFO = 11;

    // name and type
    byte TAG_NAME_AND_TYPE_INFO = 12;
    // Constants types end

    // 在方法的code属性里，有很多指令，也就是方法的具体执行内容
    // 指令常量 start

    // 获取静态方法
    byte GET_STATIC = (byte)0xb2;

    // ldc是什么的缩写不知道，它的功能是将int，float或String型常量值从常量池中推送至栈顶
    byte LDC = (byte)0x12;
    
    byte LDC_W = (byte)0x13;
    
    byte LDC2_W = (byte)0x14;

    // 执行静态方法
    byte INVOKE_STATIC = (byte) 0xb8;

    // 执行实例方法
    byte INVOKE_VIRTUAL = (byte) 0xb6;

    // 从当前方法返回void
    byte RETURN = (byte) 0xb1;

    // 将第一个引用类型推送至栈顶
    byte ALOAD_0 = (byte) 0x2a;

    // 调用超类构造方法
    byte INVOKE_SPECIAL = (byte) 0xb7;

    byte NOP = 0;

    byte A_CONST_NULL = 0x01;

    byte I_CONST_M1 = 0x02;

    byte I_CONST_0 = 0x03;

    byte I_CONST_1 = 0x04;

    byte I_CONST_2 = 0x05;

    byte I_CONST_3 = 0x06;

    byte I_CONST_4 = 0x07;

    byte I_CONST_5 = 0x08;

    byte L_CONST_0 = 0x09;

    byte L_CONST_1 = 0x0a;

    byte F_CONST_0 = 0x0b;

    byte F_CONST_1 = 0x0c;
    
    byte F_CONST_2 = 0x0d;

    byte D_CONST_0 = 0x0e;

    byte D_CONST_1 = 0x0f;

    byte BI_PUSH = 0x10;

    byte SI_PUSH = 0x11;

    byte I_LOAD = 0x15;

    byte I_LOAD_0 = 0x1a;

    byte I_LOAD_1 = 0x1b;

    byte I_LOAD_2 = 0x1c;

    byte I_LOAD_3 = 0x1d;

    byte ISTORE = 0x36;

    byte ISTORE_0 = 0x3b;

    byte ISTORE_1 = 0x3c;

    byte ISTORE_2 = 0x3d;

    byte ISTORE_3 = 0x3e;

    byte I_RETURN = (byte) 0xac;

    byte L_RETURN = (byte) 0xad;

    byte F_RETURN = (byte) 0xae;

    byte D_RETURN = (byte) 0xaf;

    byte A_RETURN = (byte) 0xb0;

    byte F_LOAD = 0x17;

    byte F_LOAD_0 = 0x22;

    byte F_LOAD_1 = 0x23;

    byte F_LOAD_2 = 0x24;

    byte F_LOAD_3 = 0x25;

    byte L_LOAD = 0x16;

    byte L_LOAD_0 = 0x1e;

    byte L_LOAD_1 = 0x1f;

    byte L_LOAD_2 = 0x20;

    byte L_LOAD_3 = 0x21;

    byte D_LOAD = 0x18;

    byte D_LOAD_0 = 0x26;

    byte D_LOAD_1 = 0x27;

    byte D_LOAD_2 = 0x28;

    byte D_LOAD_3 = 0x29;

    byte A_LOAD = 0x19;

    byte A_LOAD_0 = 0x2a;

    byte A_LOAD_1 = 0x2b;

    byte A_LOAD_2 = 0x2c;

    byte A_LOAD_3 = 0x2d;

    byte A_A_LOAD = 0x32;

    byte I_A_LOAD = 0x2e;

    byte L_A_LOAD = 0x2f;

    byte F_A_LOAD = 0x30;

    byte D_A_LOAD = 0x31;

    byte B_A_LOAD = 0x33;

    byte C_A_LOAD = 0x34;

    byte S_A_LOAD = 0x35;

    byte L_STORE = 0x37;

    byte L_STORE_0 = 0x3f;

    byte L_STORE_1 = 0x40;

    byte L_STORE_2 = 0x41;

    byte L_STORE_3 = 0x42;

    byte F_STORE = 0x38;

    byte F_STORE_0 = 0x43;

    byte F_STORE_1 = 0x44;

    byte F_STORE_2 = 0x45;

    byte F_STORE_3 = 0x46;

    byte D_STORE = 0x39;

    byte D_STORE_0 = 0x47;

    byte D_STORE_1 = 0x48;

    byte D_STORE_2 = 0x49;

    byte D_STORE_3 = 0x4a;

    byte A_STORE = 0x3a;

    byte A_STORE_0 = 0x4b;

    byte A_STORE_1 = 0x4c;

    byte A_STORE_2 = 0x4d;

    byte A_STORE_3 = 0x4e;

    byte I_A_STORE = 0x4f;

    byte L_A_STORE = 0x50;

    byte F_A_STORE = 0x51;

    byte D_A_STORE = 0x52;

    byte A_A_STORE = 0x53;

    byte B_A_STORE = 0x54;

    byte C_A_STORE = 0x55;

    byte S_A_STORE = 0x56;

    byte POP = 0x57;

    byte POP2 = 0x58;

    byte DUP = 0x59;

    byte DUP_X1 = 0x5a;

    byte DUP_X2 = 0x5b;

    byte DUP2 = 0x5c;

    byte DUP2_X1 = 0x5d;

    byte DUP2_X2 = 0x5e;

    byte I_ADD = 0x60;

    byte L_ADD = 0x61;

    byte F_ADD = 0x62;

    byte D_ADD = 0x63;

    byte I_SUB = 0x64;

    byte L_SUB = 0x65;

    byte F_SUB = 0x66;

    byte D_SUB = 0x67;

    byte I_MUL = 0x68;

    byte L_MUL = 0x69;

    byte F_MUL = 0x6a;

    byte D_MUL = 0x6b;

    byte I_DIV = 0x6c;

    byte L_DIV = 0x6d;

    byte F_DIV = 0x6e;

    byte D_DIV = 0x6f;

    byte I_REM = 0x70;

    byte L_REM = 0x71;

    byte F_REM = 0x72;

    byte D_REM = 0x73;

    byte I_NEG = 0x74;

    byte L_NEG = 0x75;

    byte F_NEG = 0x76;

    byte D_NEG = 0x77;

    byte I_SHL = 0x78;

    byte L_SHL = 0x79;

    byte I_SHR = 0x7a;

    byte L_SHR = 0x7b;

    byte I_U_SHR = 0x7c;

    byte L_U_SHR = 0x7d;

    byte I_AND = 0x7e;

    byte L_AND = 0x7f;

    byte I_OR = (byte) 0x80;

    byte L_OR = (byte) 0x81;

    byte I_XOR = (byte) 0x82;

    byte L_XOR = (byte) 0x83;

    byte I_INC = (byte) 0x84;

    byte I2L = (byte) 0x85;

    byte I2F = (byte) 0x86;

    byte I2D = (byte) 0x87;

    byte L2I = (byte) 0x88;

    byte L2F = (byte) 0x89;

    byte L2D = (byte) 0x8a;

    byte F2I = (byte) 0x8b;

    byte F2L = (byte) 0x8c;

    byte F2D = (byte) 0x8d;

    byte D2I = (byte) 0x8e;

    byte D2L = (byte) 0x8f;

    byte D2F = (byte) 0x90;

    byte I2B = (byte) 0x91;

    byte I2C = (byte) 0x92;

    byte I2S = (byte) 0x93;

    byte L_CMP = (byte) 0x94;

    byte F_CMP_L = (byte) 0x95;

    byte F_CMP_G = (byte) 0x96;

    byte D_CMP_L = (byte) 0x97;

    byte D_CMP_G = (byte) 0x98;

    byte IF_EQ = (byte) 0x99;

    byte IF_NE = (byte) 0x9a;

    byte IF_LT = (byte) 0x9b;

    byte IF_GE = (byte) 0x9c;

    byte IF_GT = (byte) 0x9d;

    byte IF_LE = (byte) 0x9e;

    byte IF_ICMP_EQ = (byte) 0x9f;

    byte IF_ICMP_NE = (byte) 0xa0;

    byte IF_ICMP_LT = (byte) 0xa1;

    byte IF_ICMP_GE = (byte) 0xa2;

    byte IF_ICMP_GT = (byte) 0xa3;

    byte IF_ICMP_LE = (byte) 0xa4;

    byte IF_ACMP_EQ = (byte) 0xa5;

    byte IF_ACMP_NE = (byte) 0xa6;
    
    byte GOTO = (byte) 0xa7;
    
    byte PUT_STATIC = (byte) 0xb3;
    
    byte GET_FIELD = (byte) 0xb4;
    
    byte PUT_FIELD = (byte) 0xb5;
    
    byte INVOKE_INTERFACE = (byte) 0xb9;

    byte NEW = (byte) 0xbb;

    byte NEW_ARRAY = (byte) 0xbc;

    byte A_NEW_ARRAY = (byte) 0xbd;

    byte ARRAY_LENGTH = (byte) 0xbe;

    byte A_THROW = (byte) 0xbf;

    byte CHECK_CAST = (byte) 0xc0;

    byte INSTANCE_OF = (byte) 0xc1;

    byte MONITOR_ENTER = (byte) 0xc2;

    byte MONITOR_EXIT = (byte) 0xc3;
    
    byte IF_NULL = (byte) 0xc6;
    
    byte IF_NON_NULL = (byte) 0xc7;

    byte TRUE_BYTE= 1;

    byte FALSE_BYTE = 0;

    int INT_BYTE_COUNT = 4;

    int SHORT_BYTE_COUNT = 2;

    int FLOAT_BYTE_COUNT = 4;

    int BOOLEAN_BYTE_COUNT = 1;

    int LONG_BYTE_COUNT = 8;

    int DOUBLE_BYTE_COUNT = 8;

    int REFERENCE_BYTE_COUNT = 4;

    // 指令常量 end

    // 属性名称 start
    String ATTRIBUTE_NAME_CODE = "Code";

    String ATTRIBUTE_NAME_LINE_NUMBER_TABLE = "LineNumberTable";

    String ATTRIBUTE_NAME_CONSTANT_VALUE = "ConstantValue";

    String ATTRIBUTE_NAME_DEPRECATED = "Deprecated";

    String ATTRIBUTE_NAME_EXCEPTIONS = "Exceptions";

    String ATTRIBUTE_NAME_INNER_CLASSES = "InnerClasses";

    String ATTRIBUTE_NAME_LOCAL_VARIABLE_TABLE = "LocalVariableTable";

    String ATTRIBUTE_NAME_SOURCE_FILE = "SourceFile";

    String ATTRIBUTE_NAME_SYNTHETIC = "Synthetic";
    // 属性名称 end

    byte [] MAGIC_DIGITAL = {(byte) 0xca, (byte) 0xfe, (byte) 0xba, (byte) 0xbe};
}
