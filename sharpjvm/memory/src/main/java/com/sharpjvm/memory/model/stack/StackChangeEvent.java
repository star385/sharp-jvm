package com.sharpjvm.memory.model.stack;

import java.util.EventObject;

/**
 * ջ�仯�¼���
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: ����9:57
 * To change this template use File | Settings | File Templates.
 */
public class StackChangeEvent extends EventObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * pop�����¼�����
     */
    public static final int TYPE_POP_OUT = 1;

    /**
     * ѹ��ջ�¼�����
     */
    public static final int TYPE_PUSH_IN = 2;

    /**
     * ����ջ֡ջ�¼�����
     */
    public static final int TYPE_CREATE_STACK_FRAME_STACK = 3;

    /**
     * �������¼����߳�id�������߳�id�ڱ��߳������ۺδ����ǿ��Ի�ȡ�ģ�
     * ��Ϊ�˱�����¼��ڱ���̷߳�������ɳ���Ĵ���
     */
    private Long threadId;

    /**
     * �¼����͡�
     */
    private int type;

    /**
     * �����¼���ջ֡��������ʱ������Ϊnull��
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
