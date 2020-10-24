package com.sharpjvm.bytecode.sample;

import java.io.FileOutputStream;

public class ByteCodeWriter {
    public static void main(String[] args) throws Exception {
        byte[] byteArray = new byte[0x1aa];
        int i = 0;
        // 魔数 4个字节
        byteArray[i++] = (byte) 0xca;
        byteArray[i++] = (byte) 0xfe;
        byteArray[i++] = (byte) 0xba;
        byteArray[i++] = (byte) 0xbe;
        // 主版本号 2字节
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0;
        // 次版本号 2字节
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0x32;
        // 常量池大小 4字节
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0x1d; // 常量池大小是29
        // 第一个常量的类型 1字节
        byteArray[i++] = (byte) 0x0a; // 类型是10，即CANSTANT_Methodref_info
        // 指向声明方法的类描述符的索引项 2字节 
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x06; // 指向第六个常量
        // 指向名称及类型描述符 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0f; // 指向第十一个常量

        // 第二个常量类型 1字节
        byteArray[i++] = (byte) 0x09; // 类型是9，即CANSTANT_Fieldref_info
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0x10; // 指向常量16
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0x11; // 指向常量17

        // 第三个常量
        byteArray[i++] = (byte) 0x08; // 8代表CANSTANT_String_info
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0x12; // 指向常量18

        // 第四个常量
        byteArray[i++] = (byte) 0x0a; // 类型是10，即CANSTANT_Methodref_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x13; // 指向19个常量
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x14; // 指向第20个常量

        // 第五个常量
        byteArray[i++] = (byte) 0x07; // 类型是CANSTANT_Class_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x15; // 指向第21个常量

        // 第六个常量
        byteArray[i++] = (byte) 0x07; // 类型是CANSTANT_Class_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x16; // 指向第22个常量

        // 第七个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x06; // 占用6个字节，接下来的6个字节就是6个字节的内容
        byteArray[i++] = (byte) 0x3c; // <
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x74; // t
        byteArray[i++] = (byte) 0x3e; // >

        // 第八个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x03; // 占用3个字节，接下来的3个字节就是3个字节的内容
        byteArray[i++] = (byte) 0x28; // (
        byteArray[i++] = (byte) 0x29; // )
        byteArray[i++] = (byte) 0x56; // V

        // 第九个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x04; // 占用4个字节，接下来的4个字节就是4个字节的内容
        byteArray[i++] = (byte) 0x43; // C
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x64; // d
        byteArray[i++] = (byte) 0x65; // e

        // 第十个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0f; // 占用15个字节，接下来的15个字节就是15个字节的内容
        byteArray[i++] = (byte) 0x4c; // L
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x65; // e
        byteArray[i++] = (byte) 0x4e; // N
        byteArray[i++] = (byte) 0x75; // u
        byteArray[i++] = (byte) 0x6d; // m
        byteArray[i++] = (byte) 0x62; // b
        byteArray[i++] = (byte) 0x65; // e
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x54; // T
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x62; // b
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x65; // e

        // 第十一个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x04; // 占用4个字节，接下来的4个字节就是4个字节的内容
        byteArray[i++] = (byte) 0x6d; // m
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6e; // n

        // 第十二个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x16; // 占用22个字节，接下来的22个字节就是22个字节的内容
        byteArray[i++] = (byte) 0x28; // (
        byteArray[i++] = (byte) 0x5b; // [
        byteArray[i++] = (byte) 0x4c; // L
        byteArray[i++] = (byte) 0x6a; // j
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x76; // v
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x67; // g
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x53; // S
        byteArray[i++] = (byte) 0x74; // t
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x67; // g
        byteArray[i++] = (byte) 0x3b; // ;
        byteArray[i++] = (byte) 0x29; // )
        byteArray[i++] = (byte) 0x56; // V

        // 第十三个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0a; // 占用10个字节，接下来的10个字节就是10个字节的内容
        byteArray[i++] = (byte) 0x53; // S
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x75; // u
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x63; // c
        byteArray[i++] = (byte) 0x65; // e
        byteArray[i++] = (byte) 0x46; // F
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x65; // e

        // 第十四个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0f; // 占用15个字节，接下来的15个字节就是15个字节的内容
        byteArray[i++] = (byte) 0x48; // H
        byteArray[i++] = (byte) 0x65; // e
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x57; // W
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x64; // d
        byteArray[i++] = (byte) 0x2e; // .
        byteArray[i++] = (byte) 0x6a; // j
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x76; // v
        byteArray[i++] = (byte) 0x61; // a

        // 第十五个常量
        byteArray[i++] = (byte) 0x0c; // 类型是12，即CANSTANT_NameAndType_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x07; // 指向第七个常量
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x08; // 指向第八个常量

        // 第十六个常量
        byteArray[i++] = (byte) 0x07; // 类型是7，即CANSTANT_Class_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x17; // 指向第23个常量

        // 第十七个常量
        byteArray[i++] = (byte) 0x0c; // 类型是12，即CANSTANT_NameAndType_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x18; // 指向第24个常量
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x19; // 指向第25个常量

        // 第十八个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0c; // 占用12个字节，接下来的12个字节就是12个字节的内容
        byteArray[i++] = (byte) 0x48; // H
        byteArray[i++] = (byte) 0x65; // e
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x20; //
        byteArray[i++] = (byte) 0x57; // W
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x64; // d
        byteArray[i++] = (byte) 0x21; // !

        // 第十九个常量
        byteArray[i++] = (byte) 0x07; // 类型是7，即CANSTANT_Class_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x1a; // 指向第26个常量

        // 第二十个常量
        byteArray[i++] = (byte) 0x0c; // 类型是12，即CANSTANT_NameAndType_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x1b; // 指向第27个常量
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x1c; // 指向第28个常量

        // 第二十一个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0a; // 占用10个字节，接下来的10个字节就是10个字节的内容
        byteArray[i++] = (byte) 0x48; // H
        byteArray[i++] = (byte) 0x65; // e
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x57; // W
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x64; // d

        // 第二十二个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x10; // 占用10个字节，接下来的10个字节就是10个字节的内容
        byteArray[i++] = (byte) 0x6a; // j
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x76; // v
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x67; // g
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x4f; // O
        byteArray[i++] = (byte) 0x62; // b
        byteArray[i++] = (byte) 0x6a; // j
        byteArray[i++] = (byte) 0x65; // e
        byteArray[i++] = (byte) 0x63; // c
        byteArray[i++] = (byte) 0x74; // t

        // 第二十三个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x10; // 占用16个字节，接下来的16个字节就是16个字节的内容
        byteArray[i++] = (byte) 0x6a; // j
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x76; // v
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x67; // g
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x53; // S
        byteArray[i++] = (byte) 0x79; // y
        byteArray[i++] = (byte) 0x73; // s
        byteArray[i++] = (byte) 0x74; // t
        byteArray[i++] = (byte) 0x65; // e
        byteArray[i++] = (byte) 0x6d; // m

        // 第二十四个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x03; // 占用3个字节，接下来的3个字节就是3个字节的内容
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x75; // u
        byteArray[i++] = (byte) 0x74; // t

        // 第二十五个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x15; // 占用21个字节，接下来的21个字节就是21个字节的内容
        byteArray[i++] = (byte) 0x4c; // L
        byteArray[i++] = (byte) 0x6a; // j
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x76; // v
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x50; // P
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x74; // t
        byteArray[i++] = (byte) 0x53; // S
        byteArray[i++] = (byte) 0x74; // t
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x65; // e
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x6d; // m
        byteArray[i++] = (byte) 0x3b; // ;

        // 第二十六个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x13; // 占用19个字节，接下来的19个字节就是19个字节的内容
        byteArray[i++] = (byte) 0x6a; // j
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x76; // v
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x50; // P
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x74; // t
        byteArray[i++] = (byte) 0x53; // S
        byteArray[i++] = (byte) 0x74; // t
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x65; // e
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x6d; // m

        // 第二十七个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x07; // 占用7个字节，接下来的7个字节就是7个字节的内容
        byteArray[i++] = (byte) 0x70; // p
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x74; // t
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x6e; // n

        // 第二十八个常量
        byteArray[i++] = (byte) 0x01; // 类型是1，即CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x15; // 占用21个字节，接下来的21个字节就是21个字节的内容
        byteArray[i++] = (byte) 0x28; // (
        byteArray[i++] = (byte) 0x4c; // L
        byteArray[i++] = (byte) 0x6a; // j
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x76; // v
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x67; // g
        byteArray[i++] = (byte) 0x2f; // /
        byteArray[i++] = (byte) 0x53; // S
        byteArray[i++] = (byte) 0x74; // t
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x67; // g
        byteArray[i++] = (byte) 0x3b; // ;
        byteArray[i++] = (byte) 0x29; // )
        byteArray[i++] = (byte) 0x56; // V

        // 访问标志 2 字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x21;

        // 本类的索引，即this_class 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x05;   // 指向5

        // 父类的索引，即super_class 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x06;   // 指向6，父类是java.lang.Object

        // 接口个数 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0; // 0表示没有实现任何接口

        // field count 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0; // 0表示没有定义任何field
        // 如果有field，接下来应该是field定义

        // method count 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x02; // 2表示有两个方法
        // 第一个方法的信息 字节数未知，和其属性数目有关
        // access_flag
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // 1表示是public的
        // 方法名的索引 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x07; // 指向第七个常量，即<init>，就是类的构造方法
        // 描述信息的索引 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x08; // 指向第八个常量，即()V，表示没有参数
        // 属性数目 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // 1表示，有一个属性
        // 属性的名称索引 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x09; // 指向第九个常量，即：code
        // 接下来的四个字节是code属性的长度
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x1d; // code属性的长度是29
        // max_stack 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // max_stack是1
        // max_locals 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // max_locals是1
        // code length 4字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x05; // code length的大小是5
        // 接下来的5个字节是code的内容
        byteArray[i++] = (byte) 0x2a;
        byteArray[i++] = (byte) 0xb7;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01;
        byteArray[i++] = (byte) 0xb1;
        // exception_table_length 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0; // exception_table_length为0
        // 如果exception_table_length不为0，接下来应该是exception table的内容
        // attribute_count 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // attribute_count为1
        // 第一个attribute
        // attribute_name_index 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0a; // 指向第十个常量，也就是LineNumberTable，这种结构
        // 接下来四个字节是attribute的长度
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x06; // 长度是6
        // 接下来两个字节是line_number_table_length
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01;
        // 接下来的四个字节是start_pc和line_number，前者是字节码行号，后者是java源码行号
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // java源码行号是1
        // 第一个方法的信息完毕
        // 第二个方法的信息 字节数未知，和其属性数目有关
        // access_flag
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x09; // 9表示是public static的
        // 方法名的索引 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0b; // 指向第十一个常量，即main
        // 描述信息的索引 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0c; // 指向第十二个常量，即(Ljava/lang/String;)V，表示参数是String[]
        // 属性数目 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // 1表示，有一个属性
        // 属性的名称索引 2字节
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x09; // 指向第九个常量，即：code
        // 接下来的四个字节是code属性的长度
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x25; // 37
        // max_stack
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x02;
        // max_locals
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01;
        // code length
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x09; // 9
        // code的内容
        byteArray[i++] = (byte) 0xb2; // getstatic
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x02; // 第二个常量
        byteArray[i++] = (byte) 0x12; // ldc 将int,float,String型常量池中推送至栈顶
        byteArray[i++] = (byte) 0x03; // 第3个常量
        byteArray[i++] = (byte) 0xb6; // invokestatic
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x04; // 第4个常量
        byteArray[i++] = (byte) 0xb1; // return 从当前方法返回void
        // exception_table_length
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        // attribute count
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01;
        // attribute
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0a; // 指向第十个常量，也就是LineNumberTable，这种结构
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0a; // 长度是10
        // 接下来两个字节是line_number_table_length
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x02;
        // 接下来的四个字节是start_pc和line_number，前者是字节码行号，后者是java源码行号
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x05; // java源码行号是5
        // 接下来的四个字节是start_pc和line_number，前者是字节码行号，后者是java源码行号
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x08;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x06; // java源码行号是6
        // 第二个方法的信息完毕

        // 最后还有10个字节，是类的属性
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0d;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x02;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0e;

        FileOutputStream fout = new FileOutputStream(OUT_PUT_DIR + "HelloWorld.class");
        fout.write(byteArray);
        fout.close();
    }

    private final static String OUT_PUT_DIR = "d:\\";
}