package com.sharpjvm.interpreter.common;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.MethodInfo;
import com.sharpjvm.bytecode.bean.attribute.Code;
import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.command.CommandChain;
import com.sharpjvm.interpreter.common.MethodExecutorParameter.Parameter;
import com.sharpjvm.interpreter.util.InterpreterUtil;
import com.sharpjvm.memory.model.stack.StackFrame;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * 默认的方法执行器
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 下午4:48
 * To change this template use File | Settings | File Templates.
 */
public class DefaultMethodExecutor implements MethodExecutor {

    public StackFrame execute(MethodExecutorParameter parameter) throws ExecuteException {
        String name = parameter.getMethodName();
        ClassInfo classInfo = parameter.getClassInfo();
        byte[] reference = parameter.getInstanceReference();
        // 从字节码信息中找到main方法
        MethodInfo mainMethodInfo;
        ClassInfo executeClassInfo;
        List<Object> methodAndClassInfo = InterpreterUtil.findMethod(classInfo, name, InterpreterUtil.getParameterTypes(parameter));
        if (methodAndClassInfo == null || methodAndClassInfo.size() < 2) {
            throw new ExecuteException("没有找到类" + classInfo.getClassName() + "的" + name + "方法");
        }
        mainMethodInfo = (MethodInfo) methodAndClassInfo.get(0);
        if (mainMethodInfo == null) {
            throw new ExecuteException("没有找到类" + classInfo.getClassName() + "的" + name + "方法");
        }
        executeClassInfo = (ClassInfo) methodAndClassInfo.get(1);

        if (isSpecialMethod(executeClassInfo, mainMethodInfo)) {
        	Object o = executeSpecialMethod(parameter, reference);
            StackFrame stackFrame = new StackFrame(0, 0);
            stackFrame.setReturnAddress(InterpreterUtil.getBytesByType(o, parameter.getReturnType()));
            return stackFrame;
        }

        // 找到main方法的
        Code codeAttribute = mainMethodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            throw new ExecuteException("类" + classInfo.getClassName() + "的" + name + "方法没有Code属性");
        }

        // 指令列表
        List<Command> commandList = codeAttribute.getCommandList();
        if (commandList == null || commandList.isEmpty()) {
            throw new ExecuteException("类" + classInfo.getClassName() + "的" + name + "方法指令为空！");
        }

        Long threadId = Thread.currentThread().getId();
        StackFrame stackFrame = new StackFrame(codeAttribute.getMaxStack(), codeAttribute.getMaxLocals());
        List<Parameter> parameters = parameter.getParameters();
        int localVariableIndex = 0;
        // 首先应该把this添加到第一个局部变量
        if (reference != null) {
            stackFrame.setLocalVariable(localVariableIndex, reference);
            localVariableIndex++;
        }
        if (parameters != null && parameters.size() != 0) {
            for (int i = 0; i < parameters.size(); i++) {
            	Parameter o = parameters.get(i);
            	stackFrame.setLocalVariable(localVariableIndex++, o.getValue());
                if (o.getType() == long.class || o.getType() == double.class) {
                    localVariableIndex++;
                }
            }
        }
        RuntimeContext.getInstance().getStack().pushStackFrame(threadId, stackFrame);

        CommandChain commandChain = new CommandChain(commandList, classInfo, mainMethodInfo);
        commandChain.setExecuteClassInfo(executeClassInfo);
        commandChain.execute();

        return RuntimeContext.getInstance().getStack().popStackFrame(threadId);
    }

	/**
	 * 执行本地方法，用反射执行宿主虚拟机的本地方法。本虚拟机不具备执行本地方法的能力。
	 * 
	 * @param parameter
	 * @param reference
	 * @throws ExecuteException
	 */
	private Object executeSpecialMethod(MethodExecutorParameter parameter,
                                        byte[] reference) throws ExecuteException {
		String className = "";
		String methodName = "";
		try {
			ClassInfo classInfo = parameter.getClassInfo();
			className = classInfo.getClassName();
            className = className.replace("/", ".");
			Class<?> clazz = Class.forName(className);
			Class<?>[] parameterTypes = InterpreterUtil.getParameterTypes(parameter);
			methodName = parameter.getMethodName();
			Method m = findNativeMethod(clazz, methodName, parameterTypes);
			m.setAccessible(true);
			Object o = null;
            if (reference != null) {
                o = RuntimeContext.getInstance().getHeap().get(reference);
            }
			return m.invoke(o, getParameterValues(parameter));
		} catch (Exception e) {
			throw new ExecuteException("执行本地方法出错：" + className + "." + methodName, e);
		}
	}

    private Method findNativeMethod(Class<?> clazz, String name,  Class<?>[] parameterTypes) {
        Class<?> loopClass = clazz;
        do {
            try {
                Method m = clazz.getDeclaredMethod(name, parameterTypes);
                return m;
            } catch (NoSuchMethodException e) {
                loopClass = loopClass.getSuperclass();
            }
        } while (loopClass != Object.class);
        return null;
    }

	private Object[] getParameterValues(MethodExecutorParameter parameter) {
		List<Parameter> parameters = parameter.getParameters();
		if (parameters == null || parameters.size() == 0) {
			return new Object[0];
		}
		Object[] parameterValues = new Object[parameters.size()];
		int i = 0;
		for (Parameter p : parameters) {
			parameterValues[i++] = InterpreterUtil.getObjectByType(p.getValue(), p.getType());
		}
		return parameterValues;
	}

	private boolean isSpecialMethod(ClassInfo classInfo, MethodInfo mainMethodInfo) {
		short accessFlags = mainMethodInfo.getAccessFlags();
		boolean isNativeMethod = Modifier.isNative(accessFlags);
        if (isNativeMethod) {
            return true;
        }
        String className = classInfo.getClassName();
        className = className.replace("/", ".");
        return className.startsWith("sun");
	}
}
