package com.sharpjvm.jvm.option;

import com.sharpjvm.memory.option.Options;

/**
 * ѡ��Ľ�������
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: ����11:47
 * To change this template use File | Settings | File Templates.
 */
public interface OptionParser {

    /**
     *����ѡ�
     *
     * @param parameters �������Ĳ�����
     * @return
     */
    Options parseOption(String[] parameters);
}
