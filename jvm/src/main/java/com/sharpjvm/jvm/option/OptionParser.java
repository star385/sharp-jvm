package com.sharpjvm.jvm.option;

import com.sharpjvm.memory.option.Options;

/**
 * 选项的解析器。
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
public interface OptionParser {

    /**
     *解析选项。
     *
     * @param parameters 穿进来的参数。
     * @return
     */
    Options parseOption(String[] parameters);
}
