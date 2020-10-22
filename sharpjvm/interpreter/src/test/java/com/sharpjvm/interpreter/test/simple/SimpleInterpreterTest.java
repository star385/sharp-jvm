package com.sharpjvm.interpreter.test.simple;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.parse.ByteCodeParser;
import com.sharpjvm.bytecode.parse.DefaultByteCodeParser;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.Interpreter;
import com.sharpjvm.interpreter.simple.SimpleInterpreter;

import java.io.FileInputStream;

/**
 * 最简单的解释器测试类
 *
 * User: zhuguoyin
 * Date: 13-2-8
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
public class SimpleInterpreterTest {

    public static void main(String[] args) throws Exception {
        String classFilePath;
        if (args == null || args.length < 1) {
            System.out.println("didn't have parameters, use the default class file!");
            classFilePath = "/home/zhuguoyin/HelloWorld.class";
        } else {
            classFilePath = args[0];
        }
        FileInputStream fis = new FileInputStream(classFilePath);
        byte [] byteCode = new byte[fis.available()];
        fis.read(byteCode);

        ByteCodeParser parser = new DefaultByteCodeParser();
        ClassInfo classInfo = parser.parseByteCode(byteCode);

        Interpreter interpreter = new SimpleInterpreter();
        try{
            interpreter.interpret(classInfo);
        } catch (ExecuteException e) {
            System.err.println("执行过程中出错");
            e.printStackTrace();
        }
        fis.close();
    }
}
