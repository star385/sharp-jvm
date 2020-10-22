package com.sharpjvm.interpreter.common;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.Interpreter;
import com.sharpjvm.interpreter.util.InterpreterUtil;
import com.sharpjvm.memory.runtime.RuntimeContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通的java解释器。
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 上午11:17
 * To change this template use File | Settings | File Templates.
 */
public class CommonInterpreter implements Interpreter {

    public void interpret(ClassInfo classInfo) throws ExecuteException {
        System.out.println("********************* sharp jvm start ****************");
        long id = Thread.currentThread().getId();
        RuntimeContext.getInstance().getStack().createStackFrameStack(id);
        RuntimeContext.getInstance().getProgramCounterRegister().createProgramCounter(id);
        MethodExecutorParameter parameter = new MethodExecutorParameter();
        parameter.setClassInfo(classInfo);
        parameter.setInstanceReference(null);
        List<MethodExecutorParameter.Parameter> parameters = new ArrayList<MethodExecutorParameter.Parameter>();
        MethodExecutorParameter.Parameter p = new MethodExecutorParameter.Parameter();
        p.setType(String[].class);
        p.setValue(null);
        parameters.add(p);
        parameter.setParameters(parameters);
        parameter.setMethodName("main");
        InterpreterUtil.executeMethod(parameter);
        System.out.println("********************* sharp jvm end ****************");
    }
}
