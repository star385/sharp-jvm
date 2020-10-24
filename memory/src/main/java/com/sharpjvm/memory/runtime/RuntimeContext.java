package com.sharpjvm.memory.runtime;

import com.sharpjvm.memory.classloader.ClassInfoLoader;
import com.sharpjvm.memory.model.heap.Heap;
import com.sharpjvm.memory.model.methodArea.DefaultMethodArea;
import com.sharpjvm.memory.model.methodArea.MethodArea;
import com.sharpjvm.memory.model.programcounterregister.ProgramCounterRegister;
import com.sharpjvm.memory.model.stack.VmStack;
import com.sharpjvm.memory.option.Options;

/**
 * ����ʱ�����Ļ�����
 */
public class RuntimeContext {

    private static RuntimeContext context;

    /**
     * �����������
     */
    private ClassInfoLoader bootStrap = null;

    /**
     * ��
     */
    private Heap heap;

    /**
     * �����ջ
     */
    private VmStack stack;

    /**
     * ��������Ҳ�������ô�
     */
    private MethodArea methodArea = null;

    /**
     * �����������
     */
    private ProgramCounterRegister programCounterRegister;

    private Options options = null;

    private RuntimeContext() {

    }

    public static RuntimeContext getInstance() {
        if (context == null) {
            context = new RuntimeContext();
        }
        return context;
    }

    public ClassInfoLoader getBootStrap() {
        return bootStrap;
    }

    public void setBootStrap(ClassInfoLoader bootStrap) {
        this.bootStrap = bootStrap;
    }

    public MethodArea getMethodArea() {
        if (methodArea == null) {
            methodArea = new DefaultMethodArea();
        }
        return methodArea;
    }

    public void setMethodArea(MethodArea methodArea) {
        this.methodArea = methodArea;
    }

    public Options getOptions() {
        if (options == null) {
            options = new Options();
        }
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Heap getHeap() {
        return heap;
    }

    public void setHeap(Heap heap) {
        this.heap = heap;
    }

    public VmStack getStack() {
        return stack;
    }

    public void setStack(VmStack stack) {
        this.stack = stack;
    }

    public ProgramCounterRegister getProgramCounterRegister() {
        return programCounterRegister;
    }

    public void setProgramCounterRegister(ProgramCounterRegister programCounterRegister) {
        this.programCounterRegister = programCounterRegister;
    }
}

