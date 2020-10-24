package com.sharpjvm.bytecode.sample;

import java.io.FileOutputStream;

public class ByteCodeWriter {
    public static void main(String[] args) throws Exception {
        byte[] byteArray = new byte[0x1aa];
        int i = 0;
        // ħ�� 4���ֽ�
        byteArray[i++] = (byte) 0xca;
        byteArray[i++] = (byte) 0xfe;
        byteArray[i++] = (byte) 0xba;
        byteArray[i++] = (byte) 0xbe;
        // ���汾�� 2�ֽ�
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0;
        // �ΰ汾�� 2�ֽ�
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0x32;
        // �����ش�С 4�ֽ�
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0x1d; // �����ش�С��29
        // ��һ������������ 1�ֽ�
        byteArray[i++] = (byte) 0x0a; // ������10����CANSTANT_Methodref_info
        // ָ���������������������������� 2�ֽ� 
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x06; // ָ�����������
        // ָ�����Ƽ����������� 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0f; // ָ���ʮһ������

        // �ڶ����������� 1�ֽ�
        byteArray[i++] = (byte) 0x09; // ������9����CANSTANT_Fieldref_info
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0x10; // ָ����16
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0x11; // ָ����17

        // ����������
        byteArray[i++] = (byte) 0x08; // 8����CANSTANT_String_info
        byteArray[i++] = (byte) 0;
        byteArray[i++] = (byte) 0x12; // ָ����18

        // ���ĸ�����
        byteArray[i++] = (byte) 0x0a; // ������10����CANSTANT_Methodref_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x13; // ָ��19������
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x14; // ָ���20������

        // ���������
        byteArray[i++] = (byte) 0x07; // ������CANSTANT_Class_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x15; // ָ���21������

        // ����������
        byteArray[i++] = (byte) 0x07; // ������CANSTANT_Class_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x16; // ָ���22������

        // ���߸�����
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x06; // ռ��6���ֽڣ���������6���ֽھ���6���ֽڵ�����
        byteArray[i++] = (byte) 0x3c; // <
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x74; // t
        byteArray[i++] = (byte) 0x3e; // >

        // �ڰ˸�����
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x03; // ռ��3���ֽڣ���������3���ֽھ���3���ֽڵ�����
        byteArray[i++] = (byte) 0x28; // (
        byteArray[i++] = (byte) 0x29; // )
        byteArray[i++] = (byte) 0x56; // V

        // �ھŸ�����
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x04; // ռ��4���ֽڣ���������4���ֽھ���4���ֽڵ�����
        byteArray[i++] = (byte) 0x43; // C
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x64; // d
        byteArray[i++] = (byte) 0x65; // e

        // ��ʮ������
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0f; // ռ��15���ֽڣ���������15���ֽھ���15���ֽڵ�����
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

        // ��ʮһ������
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x04; // ռ��4���ֽڣ���������4���ֽھ���4���ֽڵ�����
        byteArray[i++] = (byte) 0x6d; // m
        byteArray[i++] = (byte) 0x61; // a
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6e; // n

        // ��ʮ��������
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x16; // ռ��22���ֽڣ���������22���ֽھ���22���ֽڵ�����
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

        // ��ʮ��������
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0a; // ռ��10���ֽڣ���������10���ֽھ���10���ֽڵ�����
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

        // ��ʮ�ĸ�����
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0f; // ռ��15���ֽڣ���������15���ֽھ���15���ֽڵ�����
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

        // ��ʮ�������
        byteArray[i++] = (byte) 0x0c; // ������12����CANSTANT_NameAndType_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x07; // ָ����߸�����
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x08; // ָ��ڰ˸�����

        // ��ʮ��������
        byteArray[i++] = (byte) 0x07; // ������7����CANSTANT_Class_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x17; // ָ���23������

        // ��ʮ�߸�����
        byteArray[i++] = (byte) 0x0c; // ������12����CANSTANT_NameAndType_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x18; // ָ���24������
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x19; // ָ���25������

        // ��ʮ�˸�����
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0c; // ռ��12���ֽڣ���������12���ֽھ���12���ֽڵ�����
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

        // ��ʮ�Ÿ�����
        byteArray[i++] = (byte) 0x07; // ������7����CANSTANT_Class_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x1a; // ָ���26������

        // �ڶ�ʮ������
        byteArray[i++] = (byte) 0x0c; // ������12����CANSTANT_NameAndType_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x1b; // ָ���27������
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x1c; // ָ���28������

        // �ڶ�ʮһ������
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0a; // ռ��10���ֽڣ���������10���ֽھ���10���ֽڵ�����
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

        // �ڶ�ʮ��������
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x10; // ռ��10���ֽڣ���������10���ֽھ���10���ֽڵ�����
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

        // �ڶ�ʮ��������
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x10; // ռ��16���ֽڣ���������16���ֽھ���16���ֽڵ�����
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

        // �ڶ�ʮ�ĸ�����
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x03; // ռ��3���ֽڣ���������3���ֽھ���3���ֽڵ�����
        byteArray[i++] = (byte) 0x6f; // o
        byteArray[i++] = (byte) 0x75; // u
        byteArray[i++] = (byte) 0x74; // t

        // �ڶ�ʮ�������
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x15; // ռ��21���ֽڣ���������21���ֽھ���21���ֽڵ�����
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

        // �ڶ�ʮ��������
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x13; // ռ��19���ֽڣ���������19���ֽھ���19���ֽڵ�����
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

        // �ڶ�ʮ�߸�����
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x07; // ռ��7���ֽڣ���������7���ֽھ���7���ֽڵ�����
        byteArray[i++] = (byte) 0x70; // p
        byteArray[i++] = (byte) 0x72; // r
        byteArray[i++] = (byte) 0x69; // i
        byteArray[i++] = (byte) 0x6e; // n
        byteArray[i++] = (byte) 0x74; // t
        byteArray[i++] = (byte) 0x6c; // l
        byteArray[i++] = (byte) 0x6e; // n

        // �ڶ�ʮ�˸�����
        byteArray[i++] = (byte) 0x01; // ������1����CANSTANT_Utf8_info
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x15; // ռ��21���ֽڣ���������21���ֽھ���21���ֽڵ�����
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

        // ���ʱ�־ 2 �ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x21;

        // �������������this_class 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x05;   // ָ��5

        // �������������super_class 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x06;   // ָ��6��������java.lang.Object

        // �ӿڸ��� 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0; // 0��ʾû��ʵ���κνӿ�

        // field count 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0; // 0��ʾû�ж����κ�field
        // �����field��������Ӧ����field����

        // method count 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x02; // 2��ʾ����������
        // ��һ����������Ϣ �ֽ���δ֪������������Ŀ�й�
        // access_flag
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // 1��ʾ��public��
        // ������������ 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x07; // ָ����߸���������<init>��������Ĺ��췽��
        // ������Ϣ������ 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x08; // ָ��ڰ˸���������()V����ʾû�в���
        // ������Ŀ 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // 1��ʾ����һ������
        // ���Ե��������� 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x09; // ָ��ھŸ�����������code
        // ���������ĸ��ֽ���code���Եĳ���
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x1d; // code���Եĳ�����29
        // max_stack 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // max_stack��1
        // max_locals 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // max_locals��1
        // code length 4�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x05; // code length�Ĵ�С��5
        // ��������5���ֽ���code������
        byteArray[i++] = (byte) 0x2a;
        byteArray[i++] = (byte) 0xb7;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01;
        byteArray[i++] = (byte) 0xb1;
        // exception_table_length 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0; // exception_table_lengthΪ0
        // ���exception_table_length��Ϊ0��������Ӧ����exception table������
        // attribute_count 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // attribute_countΪ1
        // ��һ��attribute
        // attribute_name_index 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0a; // ָ���ʮ��������Ҳ����LineNumberTable�����ֽṹ
        // �������ĸ��ֽ���attribute�ĳ���
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x06; // ������6
        // �����������ֽ���line_number_table_length
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01;
        // ���������ĸ��ֽ���start_pc��line_number��ǰ�����ֽ����кţ�������javaԴ���к�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // javaԴ���к���1
        // ��һ����������Ϣ���
        // �ڶ�����������Ϣ �ֽ���δ֪������������Ŀ�й�
        // access_flag
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x09; // 9��ʾ��public static��
        // ������������ 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0b; // ָ���ʮһ����������main
        // ������Ϣ������ 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0c; // ָ���ʮ������������(Ljava/lang/String;)V����ʾ������String[]
        // ������Ŀ 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01; // 1��ʾ����һ������
        // ���Ե��������� 2�ֽ�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x09; // ָ��ھŸ�����������code
        // ���������ĸ��ֽ���code���Եĳ���
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
        // code������
        byteArray[i++] = (byte) 0xb2; // getstatic
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x02; // �ڶ�������
        byteArray[i++] = (byte) 0x12; // ldc ��int,float,String�ͳ�������������ջ��
        byteArray[i++] = (byte) 0x03; // ��3������
        byteArray[i++] = (byte) 0xb6; // invokestatic
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x04; // ��4������
        byteArray[i++] = (byte) 0xb1; // return �ӵ�ǰ��������void
        // exception_table_length
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        // attribute count
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x01;
        // attribute
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0a; // ָ���ʮ��������Ҳ����LineNumberTable�����ֽṹ
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0a; // ������10
        // �����������ֽ���line_number_table_length
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x02;
        // ���������ĸ��ֽ���start_pc��line_number��ǰ�����ֽ����кţ�������javaԴ���к�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x05; // javaԴ���к���5
        // ���������ĸ��ֽ���start_pc��line_number��ǰ�����ֽ����кţ�������javaԴ���к�
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x08;
        byteArray[i++] = (byte) 0x0;
        byteArray[i++] = (byte) 0x06; // javaԴ���к���6
        // �ڶ�����������Ϣ���

        // �����10���ֽڣ����������
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