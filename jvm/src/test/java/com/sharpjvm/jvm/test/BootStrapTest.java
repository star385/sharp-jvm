package com.sharpjvm.jvm.test;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.jvm.classloader.BootStrap;

/**
 * ����������������ò�����
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: ����4:32
 * To change this template use File | Settings | File Templates.
 */
public class BootStrapTest {

    public static void main(String[] args) throws Exception {
        ClassInfo classInfo = new BootStrap().loadClassInfo("java.lang.Object");
        System.out.println(classInfo);
        System.out.println(Integer.toHexString(-65));
    }
}
