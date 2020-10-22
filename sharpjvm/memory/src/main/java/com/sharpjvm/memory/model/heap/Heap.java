package com.sharpjvm.memory.model.heap;

import java.util.Collection;

/**
 * ���ڴ�ģ��
 * 
 * User: zhuguoyin
 * Date: 13-3-8
 * To change this template use File | Settings | File Templates.
 */
public interface Heap {

	/**
	 * �������û�ȡ�����Ķ���
	 * 
	 * @param reference
	 * @return
	 */
    Object get(byte[] reference);

    /**
     * ���ݶ����ȡ���ã�����ö�����δ�������ã��򴴽�һ��������
     * 
     * @param value
     * @return
     */
    byte[] getReference(Object value);

    /**
     * �������û�ȡ���������
     * 
     * @param reference
     * @return
     */
    HeapObjectContainer getContainer(byte[] reference);

    /**
     * ��Ӷ��ڴ�仯�ļ�����
     * 
     * @param heapChangeListener
     */
    void addHeapChangeListener(HeapChangeListener heapChangeListener);
    
    /**
     * �Ƴ����ڴ�仯������
     * 
     * @param heapChangeListener
     */
    void removeHeapChangeListener(HeapChangeListener heapChangeListener);

    /**
     * �Ƿ����ĳ������
     * 
     * @param key
     * @return
     */
    boolean containsReference(byte[] key);

    /**
     * �Ƿ����ĳ������
     * 
     * @param value
     * @return
     */
    boolean containsValue(Object value);

    /**
     * �������ü�������1
     * 
     * @param reference
     */
    void incReferenceCount(byte[] reference);

    /**
     * �������ü�������1
     * 
     * @param reference
     */
    void decReferenceCount(byte[] reference);

    /**
     * �������Ƴ�����
     * 
     * @param o
     */
    void remove(Object o);

    /**
     * �������ðѶ��ڴ�����Ƴ���
     * 
     * @param key
     */
    void removeByReference(byte[] key);

    /**
     * ���ö��ڴ��������������ĳЩ�����ǲ��Ǹô�����������������ˡ�
     * 
     * @param heapOrganizer
     */
    void setHeapOrganizer(HeapOrganizer heapOrganizer);

    /**
     * ��ȡ���ж���Ķ���
     * 
     * @return
     */
    Collection<HeapObjectContainer> getAllHeapObject();
}
