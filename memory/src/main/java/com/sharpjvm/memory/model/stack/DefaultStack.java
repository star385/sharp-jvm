package com.sharpjvm.memory.model.stack;

import java.util.*;

/**
 * Ĭ�ϵ������ջʵ�֡�
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: ����10:34
 * To change this template use File | Settings | File Templates.
 */
public class DefaultStack implements VmStack {

	/**
	 * �ţ����Ǹ��൱���ӵ����ݽṹ��������ú�����˼·���������Ի�
	 * ������һ��Long��Stack��ӳ�䡣
	 * Long���߳�id��Stack��ջ֡��ջ��Ҳ����˵һ���̶߳�Ӧһ��ջ֡�Ķ�ջ
	 * ջ֡��ջ��Ԫ����StackFrame��һ��StackFrame��Ӧһ��������Ҳ����˵һ���߳����ɺܶ��������еķ������ɡ�
	 * @see StackFrame ջ֡�ĽṹҲ�ǱȽϸ��ӵģ���������һ��������ջ��һ����ʱ�������Լ����ص�ַ�ȱ����Ϣ��
	 * ��������һ�£�
	 * thread-->Stack<StackFrame>
	 * method-->StackFrame
	 * StackFrame-->operatorStack+localVariableTable+returnAddress+....
	 */
    private Map<Long, Stack<StackFrame>> stackFrameStackMap;

    private List<StackChangeListener> listeners;

    public void createStackFrameStack(Long threadId) {
        Stack<StackFrame> stackFrameStack = new Stack<StackFrame>();
        getStackFrameStackMap().put(threadId, stackFrameStack);
        fireStackChangedEvent(threadId, StackChangeEvent.TYPE_CREATE_STACK_FRAME_STACK, null);
    }


    public StackFrame popStackFrame(Long threadId) {
        Stack<StackFrame> stackFrameStack = getStackFrameStackMap().get(threadId);
        if (stackFrameStack == null) {
            throw new RuntimeException("���߳���δ������");
        }
        return stackFrameStack.pop();
    }

    public StackFrame getStackFrame(Long threadId) {
        Stack<StackFrame> stackFrameStack = getStackFrameStackMap().get(threadId);
        if (stackFrameStack == null) {
            throw new RuntimeException("���߳���δ������");
        }
        return stackFrameStack.peek();
    }

    public void pushStackFrame(Long threadId, StackFrame stackFrame) {
        Stack<StackFrame> stackFrameStack = getStackFrameStackMap().get(threadId);
        if (stackFrameStack == null) {
            throw new RuntimeException("���߳���δ������");
        }
        stackFrameStack.push(stackFrame);
    }

    public void addStackChangeListener(StackChangeListener listener) {
        if (listeners == null) {
            listeners = new ArrayList<StackChangeListener>();
        }
        listeners.add(listener);
    }

    public void removeStackChangeListener(StackChangeListener listener) {
        if (listeners == null) {
            return;
        }
        listeners.remove(listener);
    }

    private Map<Long, Stack<StackFrame>> getStackFrameStackMap() {
        if (stackFrameStackMap == null) {
            stackFrameStackMap = new HashMap<Long, Stack<StackFrame>>();
        }
        return stackFrameStackMap;
    }

    private void fireStackChangedEvent(Long threadId, int type, StackFrame o) {
        if (listeners == null) {
            return;
        }
        for (StackChangeListener listener : listeners) {
            StackChangeEvent event = new StackChangeEvent(this);
            event.setThreadId(threadId);
            event.setType(type);
            event.setStackFrame(o);
            listener.stackChanged(event);
        }
    }
}
