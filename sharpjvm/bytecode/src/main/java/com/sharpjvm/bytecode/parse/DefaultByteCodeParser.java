package com.sharpjvm.bytecode.parse;

import com.sharpjvm.bytecode.bean.ClassInfo;

/**
 * Ĭ�ϵ��ֽ����������
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: ����10:07
 * To change this template use File | Settings | File Templates.
 */
public class DefaultByteCodeParser implements ByteCodeParser {

    /**
     * �����ֽ��룬�󲿷ֻ��������������ˣ��������Լ��ǳ����ɣ����д���㶨��
     *
     * @param bytes
     * @return
     */
    public ClassInfo parseByteCode(byte[] bytes) {
        ClassInfo classInfo = new ClassInfo();
        classInfo.fromBytes(bytes, 0);
        return classInfo;
    }
}
