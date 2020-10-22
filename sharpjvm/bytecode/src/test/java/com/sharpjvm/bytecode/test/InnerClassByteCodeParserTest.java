package com.sharpjvm.bytecode.test;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.parse.ByteCodeParser;
import com.sharpjvm.bytecode.parse.DefaultByteCodeParser;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: zhuguoyin
 * Date: 13-2-8
 * Time: ÉÏÎç9:19
 * To change this template use File | Settings | File Templates.
 */
public class InnerClassByteCodeParserTest extends BaseTest {

    public static void main(String[] args) throws Exception {
        InputStream in = InnerClassByteCodeParserTest.class.getResourceAsStream("/com/sharpjvm/bytecode/test/testclass/InnerClassTest.class");
        byte [] bytes = new byte[in.available()];
        in.read(bytes);
        ByteCodeParser parser = new DefaultByteCodeParser();
        ClassInfo classInfo = parser.parseByteCode(bytes);
        System.out.println(classInfo2String(classInfo));

        InputStream innerClassIn = InnerClassByteCodeParserTest.class.getResourceAsStream("/com/sharpjvm/bytecode/test/testclass/InnerClassTest$TestInnerClass.class");
        byte [] innerClassBytes = new byte[innerClassIn.available()];
        in.read(innerClassBytes);
        ByteCodeParser innerClassParser = new DefaultByteCodeParser();
        ClassInfo innerClassClassInfo = innerClassParser.parseByteCode(bytes);
        System.out.println(classInfo2String(innerClassClassInfo));
    }
}
