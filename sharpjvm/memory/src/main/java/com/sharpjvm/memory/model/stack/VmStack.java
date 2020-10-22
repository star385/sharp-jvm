package com.sharpjvm.memory.model.stack;


/**
 * �����ջ����������Ǻ�heap����һ�£�Ӧ������ΪStack�ģ����Ǳ���jdk�о���Stack��ô���ṹ�塣
 * Ϊ�˲���ɲ���Ҫ����ᣬ��˽�������ΪVmStack��
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: ����9:52
 * To change this template use File | Settings | File Templates.
 */
public interface VmStack {

    /**
     * ����һ��ջ֡��ջ���ڴ������̵߳�ʱ����ô˷�����
     *
     * @param threadId
     */
    void createStackFrameStack(Long threadId);

    /**
     * ��ĳһ�̵߳�ջ��ջ֡pop����������ʱ����һ������������
     *
     * @param threadId
     * @return
     */
    StackFrame popStackFrame(Long threadId);

    /**
     * ����ֻ�ǻ�ȡ����ǰջ֡����pop��
     *
     * @param threadId
     * @return
     */
    StackFrame getStackFrame(Long threadId);

    /**
     * ��ջ֡ѹ��ĳһ�̵߳�ջ֡ջ���С�
     *
     * @param threadId
     * @param stackFrame
     */
    void pushStackFrame(Long threadId, StackFrame stackFrame);

    /**
     * ���ջ�仯������
     *
     * @param listener
     */
    void addStackChangeListener(StackChangeListener listener);

    /**
     * �Ƴ�ջ�仯������
     *
     * @param listener
     */
    void removeStackChangeListener(StackChangeListener listener);
}
