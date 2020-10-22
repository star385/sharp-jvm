package com.sharpjvm.memory.model.methodArea;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.memory.model.heap.HeapChangeListener;

/**
 * 方法区抽象接口
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 上午11:20
 * To change this template use File | Settings | File Templates.
 */
public interface MethodArea {

	/**
	 * 其实方法区主要就是放置ClassInfo对象的
	 * 
	 * @param classLoaderClassName
	 * @param className
	 * @param classInfo
	 */
    void putClassInfo(String classLoaderClassName, String className, ClassInfo classInfo);

    /**
     * 获取ClassInfo
     * 
     * @param classLoaderClassName
     * @param key
     * @return
     * @throws ClassNotFoundException
     */
    public ClassInfo getClassInfo(String classLoaderClassName, String key) throws ClassNotFoundException;
    
    /**
     * 从内存中移除classInfo。
     * 
     * @param classLoaderClassName
     * @param classInfo
     */
    void removeClassInfo(String classLoaderClassName, ClassInfo classInfo);

    /**
     * 获取默认的类加载器加载的类。
     *
     * @param key
     * @return
     */
    public ClassInfo getClassInfo(String key) throws ClassNotFoundException;

    /**
     * 添加内存变化监听
     * 
     * @param listener
     */
    void addHeapChangeListener(HeapChangeListener listener);

    /**
     * 移除内存变化监听
     * 
     * @param listener
     */
    void removeHeapChangeListener(HeapChangeListener listener);

    /**
     * 从常量池中获取常量。
     *
     * @param className
     * @param fieldName
     * @return
     */
    byte[] getConstantFromPool(String className, String fieldName);

    /**
     * 将常量放入常量池。
     * 
     * @param className
     * @param fieldName
     * @param value
     */
    void putToConstantPool(String className, String fieldName, byte[] value);
}
