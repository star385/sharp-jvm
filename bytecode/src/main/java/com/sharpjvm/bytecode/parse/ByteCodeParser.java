package com.sharpjvm.bytecode.parse;

import com.sharpjvm.bytecode.bean.ClassInfo;

/**
 * 字节码解析器。
 *
 * User: zhuguoyin
 * Date: 13-2-7
 * Time: 下午4:49
 * To change this template use File | Settings | File Templates.
 */
public interface ByteCodeParser {

    /**
     * 解析字节码，将二进制字节码解析为ClassInfo数据结构。
     *
     * @param bytes
     * @return
     */
    public ClassInfo parseByteCode(byte[] bytes);
}
