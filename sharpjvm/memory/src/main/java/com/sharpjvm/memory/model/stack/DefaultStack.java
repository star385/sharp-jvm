package com.sharpjvm.memory.model.stack;

import java.util.*;

/**
 * 默认的虚拟机栈实现。
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 上午10:34
 * To change this template use File | Settings | File Templates.
 */
public class DefaultStack implements VmStack {

	/**
	 * 嗯，这是个相当复杂的数据结构，如果不好好理清思路，很容易迷惑。
	 * 首先是一个Long和Stack的映射。
	 * Long是线程id，Stack是栈帧的栈，也就是说一个线程对应一个栈帧的堆栈
	 * 栈帧堆栈的元素是StackFrame，一个StackFrame对应一个方法，也就是说一个线程是由很多正在运行的方法构成。
	 * @see StackFrame 栈帧的结构也是比较复杂的，它包含了一个操作数栈和一个临时变量表，以及返回地址等别的信息。
	 * 再来理清一下：
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
            throw new RuntimeException("此线程尚未创建！");
        }
        return stackFrameStack.pop();
    }

    public StackFrame getStackFrame(Long threadId) {
        Stack<StackFrame> stackFrameStack = getStackFrameStackMap().get(threadId);
        if (stackFrameStack == null) {
            throw new RuntimeException("此线程尚未创建！");
        }
        return stackFrameStack.peek();
    }

    public void pushStackFrame(Long threadId, StackFrame stackFrame) {
        Stack<StackFrame> stackFrameStack = getStackFrameStackMap().get(threadId);
        if (stackFrameStack == null) {
            throw new RuntimeException("此线程尚未创建！");
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
