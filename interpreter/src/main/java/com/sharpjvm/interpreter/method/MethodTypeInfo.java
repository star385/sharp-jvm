package com.sharpjvm.interpreter.method;

/**
 * �˽ṹ��װ���Ƿ�����������Ϣ�����������ͷ���ֵ����Ϣ��
 *
 * User: zhuguoyin
 * Date: 13-2-16
 * Time: ����10:51
 * To change this template use File | Settings | File Templates.
 */
public class MethodTypeInfo {

    /**
     * ������Ϣ
     */
    private Class<?>[] parameterTypes;

    /**
     * ����ֵ����
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
