package com.sharpjvm.bytecode.bean;

import com.sharpjvm.bytecode.bean.ByteArrayable;
import com.sharpjvm.bytecode.bean.constant.Constant;
import com.sharpjvm.bytecode.bean.constant.ConstantList;

import java.util.List;

/**
 * ��������쳣��
 *
 * User: zhuguoyin
 * Date: 13-2-6
 * Time: ����2:51
 * To change this template use File | Settings | File Templates.
 */
public class CodeExceptionTable implements ByteArrayable {

    // ��ʼ��
    private short startPc;

    // ��ֹ��
    private short endPc;

    // ������
    private short handlerPc;

    // �쳣����
    private short catchType;

    // ������г���
    private ConstantList constantList;

    public byte[] toBytes() {
        return new byte[0];
    }

    public void fromBytes(byte[] bytes, int startIndex) {

    }

    public int getLength() {
        // ÿ���쳣��ռ8���ֽڣ����û�᳣�������Ǻ�ϰ�ߣ�����������ע��
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
