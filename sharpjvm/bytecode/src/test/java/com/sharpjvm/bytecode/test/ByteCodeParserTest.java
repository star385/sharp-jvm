package com.sharpjvm.bytecode.test;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.parse.ByteCodeParser;
import com.sharpjvm.bytecode.parse.DefaultByteCodeParser;

import java.io.FileInputStream;

/**
 * 测试字节码解析的类。
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: 下午4:52
 * To change this template use File | Settings | File Templates.
 */
public class ByteCodeParserTest extends BaseTest {

    public static void main(String[] args) throws Exception {
        FileInputStream fos = new FileInputStream("/Users/zhuguoyin/TestGotoLine.class");
        byte [] bytes = new byte[fos.available()];
        fos.read(bytes);
        ByteCodeParser parser = new DefaultByteCodeParser();
        ClassInfo classInfo = parser.parseByteCode(bytes);
        System.out.println(classInfo2String(classInfo));
    }
}
