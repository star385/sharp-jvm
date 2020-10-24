package com.sharpjvm.memory.model.stack;


/**
 * 虚拟机栈，本来如果是和heap保持一致，应该命名为Stack的，但是本身jdk中就有Stack这么个结构体。
 * 为了不造成不必要的误会，因此将其命名为VmStack。
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
public interface VmStack {

    /**
     * 创建一个栈帧的栈，在创建新线程的时候调用此方法。
     *
     * @param threadId
     */
    void createStackFrameStack(Long threadId);

    /**
     * 将某一线程的栈顶栈帧pop出来。调用时机：一个方法结束后。
     *
     * @param threadId
     * @return
     */
    StackFrame popStackFrame(Long threadId);

    /**
     * 仅仅只是获取出当前栈帧，不pop。
     *
     * @param threadId
     * @return
     */
    StackFrame getStackFrame(Long threadId);

    /**
     * 将栈帧压入某一线程的栈帧栈顶中。
     *
     * @param threadId
     * @param stackFrame
     */
    void pushStackFrame(Long threadId, StackFrame stackFrame);

    /**
     * 添加栈变化监听器
     *
     * @param listener
     */
    void addStackChangeListener(StackChangeListener listener);

    /**
     * 移除栈变化监听器
     *
     * @param listener
     */
    void removeStackChangeListener(StackChangeListener listener);
}
