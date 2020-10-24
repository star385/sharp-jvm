package com.sharpjvm.jvm;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.Interpreter;
import com.sharpjvm.interpreter.common.CommonInterpreter;
import com.sharpjvm.jvm.classloader.BootStrap;
import com.sharpjvm.jvm.option.DefaultOptionParser;
import com.sharpjvm.jvm.option.OptionParser;
import com.sharpjvm.memory.model.config.SystemConfig;
import com.sharpjvm.memory.model.heap.DefaultHeap;
import com.sharpjvm.memory.model.heap.Heap;
import com.sharpjvm.memory.model.heap.HeapChangeListener;
import com.sharpjvm.memory.model.methodArea.DefaultMethodArea;
import com.sharpjvm.memory.model.methodArea.MethodArea;
import com.sharpjvm.memory.model.programcounterregister.ProgramCounterRegister;
import com.sharpjvm.memory.model.stack.DefaultStack;
import com.sharpjvm.memory.model.stack.StackChangeListener;
import com.sharpjvm.memory.model.stack.VmStack;
import com.sharpjvm.memory.option.Options;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * ����������ࡣ
 * ����������ĵ��ְ࣬���ǣ�
 * 1.��������
 * 2.��ʼ����������л�����
 * 3.��������ࡣ
 * 4.ִ�н�����������ִ��java class��
 * 
 * ������÷���
 * java com.sharpjvm.jvm.JVM -classpath dir TestClass
 * Ϊ�˾�����ʹʹ���߲��Ի�SharpJvm�����в�������hotspotһ����-classpath��ָ�����������·����
 * 
 * User: zhuguoyin Date: 13-3-18 Time: ����1:37 To change this template use File |
 * Settings | File Templates.
 */
public class JVM {

	public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
		OptionParser optionParser = new DefaultOptionParser();
		Options options = optionParser.parseOption(args);
		RuntimeContext.getInstance().setOptions(options);
		initHeap();
		initStack();
		initMethodArea();
		RuntimeContext.getInstance().setBootStrap(new BootStrap());
		RuntimeContext.getInstance().setProgramCounterRegister(new ProgramCounterRegister());
		
		String mainClassName = RuntimeContext.getInstance().getOptions().getMainClass();
		ClassInfo classInfo;
		try {
			classInfo = RuntimeContext.getInstance().getBootStrap().loadClassInfo(mainClassName);
			if (classInfo == null) {
				System.err.println("û���ҵ�����࣡");
				return;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("û���ҵ�����࣡");
			return;
		}
		Interpreter interpreter = getInterpreter();
		try {
			interpreter.interpret(classInfo);
		} catch (ExecuteException e) {
			System.err.println("ִ�й����г���");
			e.printStackTrace();
		}
        long time2 = System.currentTimeMillis();
        System.out.println("ִ��" + (time2 - time1) / 1000.0 + "s");
    }

	/**
	 * ��ʼ��������
	 * 
	 * @return
	 */
	private static Interpreter getInterpreter() {
		Interpreter interpreter;
		String interpreterClassName = SystemConfig.getInstance().getConfigByKey(
				"interpreter.class.name");
		if (interpreterClassName != null && !interpreterClassName.isEmpty()) {
			try {
				interpreter = (Interpreter) Class.forName(interpreterClassName).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				interpreter = new CommonInterpreter();
			}
		} else {
			interpreter = new CommonInterpreter();
		}
		return interpreter;
	}

	/**
	 * ��ʼ��������
	 */
	private static void initMethodArea() {
		MethodArea methodArea;
		String heapClassName = SystemConfig.getInstance().getConfigByKey(
				"methodArea.class.name");
		if (heapClassName != null && !heapClassName.isEmpty()) {
			try {
				methodArea = (MethodArea) Class.forName(heapClassName).newInstance();
			} catch (Exception e) {
				System.err.println("��ʼ��ջ��Ϣ����ʹ��Ĭ��ջʵ�ַ�ʽ");
				methodArea = new DefaultMethodArea();
			}
		} else {
			methodArea = new DefaultMethodArea();
		}
		RuntimeContext.getInstance().setMethodArea(methodArea);

		String methodAreaListenerClassName = SystemConfig.getInstance()
				.getConfigByKey("methodArea.listener.class.name");
		if (methodAreaListenerClassName != null && !methodAreaListenerClassName.isEmpty()) {
			String[] heapListenerClassNameArray = methodAreaListenerClassName
					.split(";");
			for (String aHeapListenerClassName : heapListenerClassNameArray) {
				try {
					HeapChangeListener listner = (HeapChangeListener) Class
							.forName(aHeapListenerClassName).newInstance();
					methodArea.addHeapChangeListener(listner);
				} catch (Exception e) {
					System.err.println("��ʼ��ջ��Ϣ����ʹ��Ĭ��ջʵ�ַ�ʽ");
				}
			}
		}
	}

	/**
	 * ��ʼ��ջ��
	 */
	private static void initStack() {
		VmStack stack;
		String stackClassName = SystemConfig.getInstance().getConfigByKey(
				"stack.class.name");
		if (stackClassName != null && !stackClassName.isEmpty()) {
			try {
				stack = (VmStack) Class.forName(stackClassName).newInstance();
			} catch (Exception e) {
				System.err.println("��ʼ������Ϣ����ʹ��Ĭ�϶�ʵ�ַ�ʽ");
				stack = new DefaultStack();
			}
		} else {
			stack = new DefaultStack();
		}
		RuntimeContext.getInstance().setStack(stack);
		
		String stackListenerClassName = SystemConfig.getInstance()
				.getConfigByKey("stack.listener.class.name");
		if (stackListenerClassName != null && !stackListenerClassName.isEmpty()) {
			String[] stackListenerClassNameArray = stackListenerClassName
					.split(";");
			for (String aStackListenerClassName : stackListenerClassNameArray) {
				try {
					StackChangeListener listner = (StackChangeListener) Class
							.forName(aStackListenerClassName).newInstance();
					stack.addStackChangeListener(listner);
				} catch (Exception e) {
					System.err.println("��ʼ��ջ��Ϣ����ʹ��Ĭ��ջʵ�ַ�ʽ");
				}
			}
		}

	}

	/**
	 * ��ʼ������
	 */
	private static void initHeap() {
		Heap heap;
		String heapClassName = SystemConfig.getInstance().getConfigByKey(
				"heap.class.name");
		if (heapClassName != null && !heapClassName.isEmpty()) {
			try {
				heap = (Heap) Class.forName(heapClassName).newInstance();
			} catch (Exception e) {
				System.err.println("��ʼ��ջ��Ϣ����ʹ��Ĭ��ջʵ�ַ�ʽ");
				heap = new DefaultHeap();
			}
		} else {
			heap = new DefaultHeap();
		}
		RuntimeContext.getInstance().setHeap(heap);

		String heapListenerClassName = SystemConfig.getInstance()
				.getConfigByKey("heap.listener.class.name");
		if (heapListenerClassName != null && !heapListenerClassName.isEmpty()) {
			String[] heapListenerClassNameArray = heapListenerClassName
					.split(";");
			for (String aHeapListenerClassName : heapListenerClassNameArray) {
				try {
					HeapChangeListener listner = (HeapChangeListener) Class
							.forName(aHeapListenerClassName).newInstance();
					heap.addHeapChangeListener(listner);
				} catch (Exception e) {
					System.err.println("��ʼ��ջ��Ϣ����ʹ��Ĭ��ջʵ�ַ�ʽ");
				}
			}
		}
	}
}
