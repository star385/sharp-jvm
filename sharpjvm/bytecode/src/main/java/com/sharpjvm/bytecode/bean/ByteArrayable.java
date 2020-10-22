package com.sharpjvm.bytecode.bean;

/**
 * ���Ա����л����ֽ�����ʹ��ֽ������б������л������ݽṹ���������еĽṹ����ʵ�ִ˽ӿڡ�
 *
 * User: zhuguoyin
 * Date: 13-1-29
 * Time: ����11:00
 * To change this template use File | Settings | File Templates.
 */
public interface ByteArrayable {

    /**
     * �������󣨼�this�����л�Ϊ�ֽ����顣
     * ����ֻ��Ϊ�˶Գƣ�ʵ����Ŀǰ��û���õ�������д��������ʱ���ðɡ�
     *
     * @return
     */
    byte[] toBytes();

    /**
     * ���ֽ�����������Լ���������Ϣ
     *
     * @param bytes ��һ��class���ֽ�������ڴ��е��ֽ�����
     * @param startIndex ��ʼ�������༴���Ķ���ʼ����
     */
    void fromBytes(byte[] bytes, int startIndex);

    /**
     * ��Ϊ�ϲ������ʱ�򣬲��˽ṹռ�ö����ֽڣ���Ҫ���Ǽ���������ϲ㣬�Ա��ϲ�ṹ֪����һ���ṹ���Ķ���ʼ������
     *
     * @return
     */
    int getLength();
}
