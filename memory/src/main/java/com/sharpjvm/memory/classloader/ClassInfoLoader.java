package com.sharpjvm.memory.classloader;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.parse.ByteCodeParser;
import com.sharpjvm.bytecode.parse.DefaultByteCodeParser;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * �Լ�������ֽ����������֮ǰ��������jdk���java.lang.ClassLoader�ģ������Ǹ������Ľ��java.lang.Class
 * ���ǲ���ҪClass������Ҫ����ClassInfo
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: ����9:47
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
        		// ��������쳣������ô����ô�Ű�
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
