package com.sharpjvm.bytecode.parse;

import com.sharpjvm.bytecode.bean.ClassInfo;

/**
 * �ֽ����������
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: ����4:49
 * To change this template use File | Settings | File Templates.
 */
public interface ByteCodeParser {

    /**
     * �����ֽ��룬���������ֽ������ΪClassInfo���ݽṹ��
     *
     * @param bytes
     * @return
     */
    public ClassInfo parseByteCode(byte[] bytes);
}
