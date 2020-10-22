package com.sharpjvm.bytecode.parse;

import com.sharpjvm.bytecode.bean.ClassInfo;

/**
 * 默认的字节码解析器。
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: 上午10:07
 * To change this template use File | Settings | File Templates.
 */
public class DefaultByteCodeParser implements ByteCodeParser {

    /**
     * 解析字节码，大部分活都交给下面的人做了，所以它自己非常轻松，几行代码搞定。
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
