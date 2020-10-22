package com.sharpjvm.jvm.test;

/**
 * 长整型累加测试类
 *
 * User: zhuguoyin
 * Date: 13-3-28
 * To change this template use File | Settings | File Templates.
 */
public class LongTest {

    public static void main(String[] args) {
        int toValue = 1000;
        long sum = 0;
        for (int i = 1; i <= toValue; i++) {
            sum = sum + i;
        }
        System.out.println("the sum of 1 to " + toValue +  " is: " + sum);
    }
}
