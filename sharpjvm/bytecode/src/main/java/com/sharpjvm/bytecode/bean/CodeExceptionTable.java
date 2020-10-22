package com.sharpjvm.bytecode.bean;

import com.sharpjvm.bytecode.bean.ByteArrayable;
import com.sharpjvm.bytecode.bean.constant.Constant;
import com.sharpjvm.bytecode.bean.constant.ConstantList;

import java.util.List;

/**
 * 方法里的异常表
 *
 * User: zhuguoyin
 * Date: 13-2-6
 * Time: 下午2:51
 * To change this template use File | Settings | File Templates.
 */
public class CodeExceptionTable implements ByteArrayable {

    // 起始行
    private short startPc;

    // 终止行
    private short endPc;

    // 处理行
    private short handlerPc;

    // 异常类型
    private short catchType;

    // 类的所有常量
    private ConstantList constantList;

    public byte[] toBytes() {
        return new byte[0];
    }

    public void fromBytes(byte[] bytes, int startIndex) {

    }

    public int getLength() {
        // 每个异常表占8个字节，这个没提常量，不是好习惯，不过尽量加注释
        return 8;
    }

    public short getStartPc() {
        return startPc;
    }

    public void setStartPc(short startPc) {
        this.startPc = startPc;
    }

    public short getEndPc() {
        return endPc;
    }

    public void setEndPc(short endPc) {
        this.endPc = endPc;
    }

    public short getHandlerPc() {
        return handlerPc;
    }

    public void setHandlerPc(short handlerPc) {
        this.handlerPc = handlerPc;
    }

    public short getCatchType() {
        return catchType;
    }

    public void setCatchType(short catchType) {
        this.catchType = catchType;
    }

    public void setConstantList(ConstantList allConstants) {
        this.constantList = allConstants;
    }
}
