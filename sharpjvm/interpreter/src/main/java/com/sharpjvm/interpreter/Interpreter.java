package com.sharpjvm.interpreter;

import com.sharpjvm.bytecode.bean.ClassInfo;

/**
 * 解释器接口
 *
 * User: zhuguoyin
 * Date: 13-2-8
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public interface Interpreter {

    /**
     * 解释字节码
     *
     * @param classInfo
     */
    void interpret(ClassInfo classInfo) throws ExecuteException;
}
