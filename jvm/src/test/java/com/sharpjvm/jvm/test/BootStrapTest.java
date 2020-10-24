package com.sharpjvm.jvm.test;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.jvm.classloader.BootStrap;

/**
 * 测试引导类加载器好不好用
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public class BootStrapTest {

    public static void main(String[] args) throws Exception {
        ClassInfo classInfo = new BootStrap().loadClassInfo("java.lang.Object");
        System.out.println(classInfo);
        System.out.println(Integer.toHexString(-65));
    }
}
