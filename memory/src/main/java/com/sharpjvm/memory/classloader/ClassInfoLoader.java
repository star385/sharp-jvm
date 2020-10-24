package com.sharpjvm.memory.classloader;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.parse.ByteCodeParser;
import com.sharpjvm.bytecode.parse.DefaultByteCodeParser;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * 自己定义的字节码加载器，之前本来想用jdk里的java.lang.ClassLoader的，但是那个类最后的结果java.lang.Class
 * 我们不需要Class，我们要的是ClassInfo
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: 上午9:47
 * To change this template use File | Settings | File Templates.
 */
public abstract class ClassInfoLoader {

    private ByteCodeParser byteCodeParser;

    private ClassInfoLoader parent;

    protected ClassInfoLoader() {
        this(RuntimeContext.getInstance().getBootStrap());
    }

    protected ClassInfoLoader(ClassInfoLoader parent) {
        this.parent = parent;
    }

    public ClassInfo loadClassInfo(String name) throws ClassNotFoundException {
    	ClassInfo classInfo = null;
        if (parent != null) {
        	try {
                classInfo = parent.loadClassInfo(name);
        	} catch (ClassNotFoundException e) {
        		classInfo = null;
        		// 不管这个异常，爱怎么着怎么着吧
        	}
        }
        if (classInfo != null) {
        	return classInfo;
        }
        byte[] byteCode = getByteCodeFromClassName(name);
        classInfo = getByteCodeParser().parseByteCode(byteCode);
        String classLoaderClassName = this.getClass().getCanonicalName();
        RuntimeContext.getInstance().getMethodArea().putClassInfo(classLoaderClassName, name, classInfo);
        return classInfo;
    }

    protected byte[] getByteCodeFromClassName(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException();
    }

    private ByteCodeParser getByteCodeParser() {
        if (byteCodeParser == null) {
            byteCodeParser = new DefaultByteCodeParser();
        }
        return byteCodeParser;
    }

    public void setByteCodeParser(ByteCodeParser byteCodeParser) {
        this.byteCodeParser = byteCodeParser;
    }
}
