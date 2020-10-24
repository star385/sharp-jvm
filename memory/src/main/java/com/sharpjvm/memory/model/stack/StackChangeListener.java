package com.sharpjvm.memory.model.stack;

/**
 * 虚拟机栈变化的监听器。
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
public interface StackChangeListener {

    /**
     * 响应栈变化。
     *
     * @param event
     */
    void stackChanged(StackChangeEvent event);
}
