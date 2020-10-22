package com.sharpjvm.bytecode.bean;

/**
 * 可以被序列化成字节数组和从字节数组中被反序列化的数据结构，几乎所有的结构都会实现此接口。
 *
 * User: zhuguoyin
 * Date: 13-1-29
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
public interface ByteArrayable {

    /**
     * 将本对象（即this）序列化为字节数组。
     * 这里只是为了对称，实际上目前并没有用到，留着写编译器的时候用吧。
     *
     * @return
     */
    byte[] toBytes();

    /**
     * 从字节数组中填充自己的所有信息
     *
     * @param bytes 即一个class的字节码读入内存中的字节数组
     * @param startIndex 起始索引，亦即从哪儿开始解析
     */
    void fromBytes(byte[] bytes, int startIndex);

    /**
     * 因为上层解析的时候，并此结构占用多少字节，需要我们计算出来供上层，以便上层结构知道下一个结构从哪儿开始解析。
     *
     * @return
     */
    int getLength();
}
