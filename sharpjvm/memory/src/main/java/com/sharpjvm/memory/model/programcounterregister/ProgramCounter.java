package com.sharpjvm.memory.model.programcounterregister;

/**
 * 程序计数器。
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
public class ProgramCounter {

    private int codeLine = 0;

    public int getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(int codeLine) {
        this.codeLine = codeLine;
    }
}
