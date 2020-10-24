package com.sharpjvm.interpreter.command;

import java.util.ArrayList;
import java.util.List;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.bean.constant.*;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.common.MethodExecutorParameter;
import com.sharpjvm.interpreter.common.MethodExecutorParameter.Parameter;
import com.sharpjvm.interpreter.method.MethodTypeInfo;
import com.sharpjvm.interpreter.util.InterpreterUtil;
import com.sharpjvm.memory.model.stack.StackFrame;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * InvokeInterface 指令执行器
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午8:43
 * To change this template use File | Settings | File Templates.
 */
public class InvokeInterfaceExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
    	Command command = commandChain.getCurrentCommand();
        byte[] methodConstantsIndexBytes = command.getParameter();
        short methodConstantIndex = ByteUtil.byteArray2Short(methodConstantsIndexBytes);
        ClassInfo classInfo = commandChain.getExecuteClassInfo();
        Constant methodConstant = classInfo.getConstantList().getConstant(methodConstantIndex);
        if (!(methodConstant instanceof InterfaceMethodRefConstant)) {
            throw new ExecuteException("字节码不正确。");
        }
        InterfaceMethodRefConstant methodConstantEx = (InterfaceMethodRefConstant) methodConstant;
        ClassConstant classConstant = methodConstantEx.getClassConstant();
        String className = classConstant.getNameConstant().getValue();
        className = className.replace("/", ".");
        NameAndTypeConstant nameAndTypeConstant = methodConstantEx.getMethodConstant();
        String methodName = nameAndTypeConstant.getNameConstant().getValue();
        String methodDescription = nameAndTypeConstant.getDescriptionConstant().getValue();
        try {
            MethodTypeInfo methodTypeInfo = InterpreterUtil.parseMethodParameterTypes(methodDescription);
            Class<?>[] parameterTypes = methodTypeInfo.getParameterTypes();
            List<Parameter> parameters = getParameter(parameterTypes);
            byte [] reference = getOperandStack().pop();
            MethodExecutorParameter parameter = new MethodExecutorParameter();
            Object instance = RuntimeContext.getInstance().getHeap().get(reference);
            if (instance == null) {
            	throw new ExecuteException("invoke interface error, instance = null");
            }
            Class<?> c = instance.getClass();
            Class<?> interfaceClass = Class.forName(className);
            if (!interfaceClass.isAssignableFrom(c)) {
            	throw new ExecuteException("invoke interface error, " + c.getCanonicalName() 
            			+ "must be instance of " + interfaceClass.getCanonicalName());
            }
            ClassInfo executeClassInfo = RuntimeContext.getInstance().getMethodArea().getClassInfo(c.getName());
            parameter.setClassInfo(executeClassInfo);
            parameter.setInstanceReference(reference);
//            parameter.setParameterTypes(parameterTypes);
            parameter.setParameters(parameters);
            parameter.setMethodName(methodName);
            parameter.setParameters(parameters);
            parameter.setReturnType(methodTypeInfo.getReturnType());
            StackFrame stackFrame = InterpreterUtil.executeMethod(parameter);
            byte[] returnAddress = stackFrame.getReturnAddress();
            if (returnAddress != null) {
                getOperandStack().push(returnAddress);
            }
        } catch (Exception e) {
            throw new ExecuteException("error occurred when execute invoke virtual", e);
        }
    }

    private List<Parameter> getParameter(Class<?>[] parameterTypes) {
        if (parameterTypes == null || parameterTypes.length <= 0) {
            return new ArrayList<MethodExecutorParameter.Parameter>();
        }
        int length = parameterTypes.length;
        List<Parameter> result = new ArrayList<Parameter>();
        for (int i = length - 1; i >= 0; i--) {
            byte[] bytes;
            if (parameterTypes[i] == long.class || parameterTypes[i] == double.class) {
                bytes = getOperandStack().pop8Bytes();
            } else {
                bytes = getOperandStack().pop();
            }
//            result[i] = bytes;
            Parameter p = new Parameter();
            p.setType(parameterTypes[i]);
            p.setValue(bytes);
            result.add(p);
        }
        List<Parameter> converse = new ArrayList<Parameter>();
        for (int i = result.size() - 1; i >= 0; i--) {
            converse.add(result.get(i));
        }
        return converse;
    }
}
