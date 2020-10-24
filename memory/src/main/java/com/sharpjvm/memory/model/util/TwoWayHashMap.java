package com.sharpjvm.memory.model.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ˫���ϣӳ�䡣
 * ʹ�ô�MapӦ��ע�⣬key��value��������Ψһ�ģ��������н�����ܲ���ȷ
 *
 * User: zhuguoyin
 * Date: 13-2-16
 * Time: ����3:50
 * To change this template use File | Settings | File Templates.
 */
public class TwoWayHashMap<K, V> extends HashMap<K, V> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * ����value��ȡkey���õ��ĳ������࣬���������������Ҳ����˵key��value������һ��һ���������Ȼ��ȡ���ľ��ǵ�һ��key��
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
