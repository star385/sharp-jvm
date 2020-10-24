package com.sharpjvm.bytecode.bean.command;

/**
 * ²Ù×÷ÃüÁî
 *
 * User: zhuguoyin
 * Date: 13-2-5
 * Time: ÉÏÎç11:36
 * To change this template use File | Settings | File Templates.
 */
public class Command {

    private int lineNumber;

    private String name;

    private byte type;

    private byte[] parameter;

    public void setType(byte type) {
        this.type = type;
    }

    public byte getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getParameter() {
        return parameter;
    }

    public void setParameter(byte[] parameter) {
        this.parameter = parameter;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String toString() {
        return "Command:" + name;
    }
}
