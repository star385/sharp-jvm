package com.sharpjvm.memory.model.programcounterregister;

import java.util.HashMap;
import java.util.Map;

/**
 * �����������ʵ�ʴ�����ѹ��û���õ����������������Ϊ�˽ӽ�jvm�淶һЩ������˳������������
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: ����11:06
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
