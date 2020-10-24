package com.sharpjvm.memory.model.methodArea;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.memory.model.heap.HeapChangeListener;

/**
 * ����������ӿ�
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: ����11:20
 * To change this template use File | Settings | File Templates.
 */
public interface MethodArea {

	/**
	 * ��ʵ��������Ҫ���Ƿ���ClassInfo�����
	 * 
	 * @param classLoaderClassName
	 * @param className
	 * @param classInfo
	 */
    void putClassInfo(String classLoaderClassName, String className, ClassInfo classInfo);

    /**
     * ��ȡClassInfo
     * 
     * @param classLoaderClassName
     * @param key
     * @return
     * @throws ClassNotFoundException
     */
    public ClassInfo getClassInfo(String classLoaderClassName, String key) throws ClassNotFoundException;
    
    /**
     * ���ڴ����Ƴ�classInfo��
     * 
     * @param classLoaderClassName
     * @param classInfo
     */
    void removeClassInfo(String classLoaderClassName, ClassInfo classInfo);

    /**
     * ��ȡĬ�ϵ�����������ص��ࡣ
     *
     * @param key
     * @return
     */
    public ClassInfo getClassInfo(String key) throws ClassNotFoundException;

    /**
     * ����ڴ�仯����
     * 
     * @param listener
     */
    void addHeapChangeListener(HeapChangeListener listener);

    /**
     * �Ƴ��ڴ�仯����
     * 
     * @param listener
     */
    void removeHeapChangeListener(HeapChangeListener listener);

    /**
     * �ӳ������л�ȡ������
     *
     * @param className
     * @param fieldName
     * @return
     */
    byte[] getConstantFromPool(String className, String fieldName);

    /**
     * ���������볣���ء�
     * 
     * @param className
     * @param fieldName
     * @param value
     */
    void putToConstantPool(String className, String fieldName, byte[] value);
}
