package com.sharpjvm.interpreter.method;

/**
 * 此结构封装的是方法的类型信息，包括参数和返回值的信息。
 *
 * User: zhuguoyin
 * Date: 13-2-16
 * Time: 上午10:51
 * To change this template use File | Settings | File Templates.
 */
public class MethodTypeInfo {

    /**
     * 参数信息
     */
    private Class<?>[] parameterTypes;

    /**
     * 返回值类型
     */
    private Class<?> returnType;

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

}
