package com.sharpjvm.jvm.test;

/**
 * 双精度型累加结算测试类。
 * 
 * User: zhuguoyin
 * Date: 13-3-30
 * To change this template use File | Settings | File Templates.
 */
public class DoubleTest {
	
    public static void main(String[] args) {
    	// 从1加到某个数
        int toValue = 100;
        double sum = 0;
        for (int i = 1; i <= toValue; i++) {
            sum = sum + i;
        }
        System.out.println("the sum of 1 to " + toValue + " is: " + sum);
    }
}
