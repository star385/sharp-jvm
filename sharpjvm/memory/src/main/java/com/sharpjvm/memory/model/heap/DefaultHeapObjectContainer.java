package com.sharpjvm.memory.model.heap;

/**
 * 默认的装入堆的容器
 *
 * User: zhuguoyin
 * Date: 13-3-8
 * Time: 下午10:26
 * To change this template use File | Settings | File Templates.
 */
public class DefaultHeapObjectContainer implements HeapObjectContainer {

    private Object value;

    public Object getValue() {
        return value;
    }

    public void putValue(Object o) {
        this.value = o;
    }

    public boolean equals(Object o) {
        if (!(o instanceof DefaultHeapObjectContainer)) {
            return false;
        }
        DefaultHeapObjectContainer instance = (DefaultHeapObjectContainer) o;
        return instance.value == value;
    }
}
