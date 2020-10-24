package com.sharpjvm.memory.model.programcounterregister;

import java.util.HashMap;
import java.util.Map;

/**
 * 程序计数器。实质代码中压根没有用到程序计数器，不过为了接近jvm规范一些，添加了程序计数器区域。
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 上午11:06
 * To change this template use File | Settings | File Templates.
 */
public class ProgramCounterRegister {

    private Map<Long, ProgramCounter> programCounterMap;

    public ProgramCounter getProgramCounter(Long threadId) {
        return getProgramCounterMap().get(threadId);
    }

    public void createProgramCounter(Long threadId) {
        ProgramCounter programCounter = new ProgramCounter();
        getProgramCounterMap().put(threadId, programCounter);
    }

    private Map<Long, ProgramCounter> getProgramCounterMap() {
        if (programCounterMap == null) {
            programCounterMap = new HashMap<Long, ProgramCounter>();
        }
        return programCounterMap;
    }
}
