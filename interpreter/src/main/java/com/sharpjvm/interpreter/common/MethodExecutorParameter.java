package com.sharpjvm.interpreter.common;

import java.io.Serializable;
import java.util.List;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.MethodInfo;

/**
 * 执行某方法的时候传递参数的结构体。
 * 
 * User: zhuguoyin
 * Date: 13-3-19
 * Time: 下午10:53
 * To change this template use File | Settings | File Templates.
 */
public class MethodExecutorParameter implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static class Parameter {
		private Class<?> type;
		
		private byte[] value;

		public Class<?> getType() {
			return type;
		}

		public void setType(Class<?> type) {
			this.type = type;
		}

		public byte[] getValue() {
			return value;
		}

		public void setValue(byte[] value) {
			this.value = value;
		}
	}

	private ClassInfo classInfo;

    private String methodName;

    private MethodInfo methodInfo;

//    private Class<?>[] parameterTypes;

    private List<Parameter> parameters;

    private Class<?> returnType;

    private byte[] instanceReference;

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public MethodInfo getMethodInfo() {
        return methodInfo;
    }

    public void setMethodInfo(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public byte[] getInstanceReference() {
        return instanceReference;
    }

    public void setInstanceReference(byte[] instanceReference) {
        this.instanceReference = instanceReference;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }
}
