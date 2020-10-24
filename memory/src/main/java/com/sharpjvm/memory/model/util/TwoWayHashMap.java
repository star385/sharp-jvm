package com.sharpjvm.memory.model.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 双向哈希映射。
 * 使用此Map应该注意，key和value都必须是唯一的，否则运行结果可能不正确
 *
 * User: zhuguoyin
 * Date: 13-2-16
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
public class TwoWayHashMap<K, V> extends HashMap<K, V> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 根据value获取key，用到的场景不多，但是是特殊情况，也就是说key和value必须是一对一的情况，不然获取到的就是第一个key。
     *
     * @param value
     * @return
     */
    public K getKeyByValue(V value) {
        if (value == null) {
            return null;
        }
        Set<Map.Entry<K,V>> entrySet = entrySet();
        if (entrySet == null || entrySet.isEmpty()) {
            return null;
        }
        for (Map.Entry<K, V> entry : entrySet) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

}
