package com.sharpjvm.memory.model.stack;

/**
 * �����ջ�仯�ļ�������
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: ����10:25
 * To change this template use File | Settings | File Templates.
 */
public interface StackChangeListener {

    /**
     * ��Ӧջ�仯��
     *
     * @param event
     */
    void stackChanged(StackChangeEvent event);
}
