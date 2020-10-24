package com.sharpjvm.memory.model.stack;

import java.util.EventObject;

/**
 * 栈变化事件。
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
public class StackChangeEvent extends EventObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * pop出来事件类型
     */
    public static final int TYPE_POP_OUT = 1;

    /**
     * 压入栈事件类型
     */
    public static final int TYPE_PUSH_IN = 2;

    /**
     * 创建栈帧栈事件类型
     */
    public static final int TYPE_CREATE_STACK_FRAME_STACK = 3;

    /**
     * 发生此事件的线程id，本来线程id在本线程中无论何处都是可以获取的，
     * 但为了避免此事件在别的线程发生，造成程序的错误。
     */
    private Long threadId;

    /**
     * 事件类型。
     */
    private int type;

    /**
     * 发生事件的栈帧，当创建时此属性为null。
     */
    private StackFrame stackFrame;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException
     *          if source is null.
     */
    public StackChangeEvent(Object source) {
        super(source);
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public StackFrame getStackFrame() {
        return stackFrame;
    }

    public void setStackFrame(StackFrame stackFrame) {
        this.stackFrame = stackFrame;
    }
}
