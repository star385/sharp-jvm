package com.sharpjvm.memory.model.stack;

import com.sharpjvm.bytecode.constants.Constants;

/**
 * 字节栈，没有办法，jdk里的Stack不支持基本类型，只能这么干了。
 * 之前此类实现方式是一维数组，后来发现一维数组实现很费劲，于是改成了2维数组，其实和Stack<byte[]>差不多的功能。
 * 可以用jdk里的Stack替换它，可是写这个怪累人的，还是保留它吧
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 下午9:28
 * To change this template use File | Settings | File Templates.
 */
public class ByteStack {

    private byte[][] byteArray;

    private int size;

    // 这个是code属性里的maxStack，push进来的时候，是不可以stack的size是不能超过此值，否则，就是字节码有问题
    private int maxStack;

    public ByteStack() {
        this(100);
    }

    public ByteStack(int initialLength) {
        this.byteArray = new byte[initialLength][];
        this.size = 0;
    }

    /**
     * 压入栈
     * 
     * @param value
     */
    public void push(byte[] value) {
    	// size是不可以超过maxstack的
        if (size >= maxStack) {
            throw new RuntimeException("size bigger or equals to max stack length, can not push more element");
        }
        // 其实以下代码没什么必要，毕竟我们只要一个简单的stack数据结构，最大值一开始就确定了，这个if永远不可能进去的。除非代码错了。
        if (byteArray.length <= size) {
            byte[][] bak = byteArray.clone();
            int length = byteArray.length;
            length += Math.floor(length / 2.0);
            byteArray = new byte[length][];
            System.arraycopy(bak, 0, byteArray, 0, bak.length);
        }
        // 栈顶置为加入数组，size自增
        byteArray[size++] = value;
        // 如果加进来的是8个字节的大东西，它需要占用两个slot，因此size再自增一下，空一格出来
        // 当然我们也可以将8个字节的元素拆成两个4个字节的数组，分别存成2个元素
        // 可是这样需要拷贝来拷贝去，舍弃了这种做法
        if (value != null && value.length == Constants.LONG_BYTE_COUNT) {
            size++;
        }
    }

    /**
     * 默认的pop方法，pop出4字节
     *
     * @return
     */
    public byte[] pop() {
        if (size <= 0) {
            throw new RuntimeException("no stack to pop!");
        }
        byte[] popValue = byteArray[size - 1];
        size--;
        return popValue;
    }

    /**
     * pop出8个字节来。
     * 
     * @return
     */
    public byte[] pop8Bytes() {
        if (size <= 1) {
            throw new RuntimeException("only 1 stack cannot pop 8 bytes!");
        }
        // 因为push的时候空了一格出来，因此先自减，忽略这个空元素
        size--;
        byte[] popValue = byteArray[--size];
        return popValue;
    }


    /**
     * 仅仅是获取，并不改变栈结构，默认获取4个字节
     *
     * @return
     */
    public byte[] get() {
        if (size <= 0) {
            throw new RuntimeException("no stack to get!");
        }
        byte[] getValue = byteArray[size - 1];
        return getValue;
    }
    
    /**
     * 仅仅是获取，并不改变栈结构，默认获取4个字节
     *
     * @return
     */
    public byte[] get8Bytes() {
        if (size <= 1) {
            throw new RuntimeException("no stack to get!");
        }
        byte[] getValue = byteArray[size - 2];
        return getValue;
    }

    public void setTop(byte [] bytes) {
        this.byteArray[size - 1] = bytes;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }
}
