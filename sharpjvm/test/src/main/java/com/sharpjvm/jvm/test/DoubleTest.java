package com.sharpjvm.jvm.test;

/**
 * ˫�������ۼӽ�������ࡣ
 * 
 * User: zhuguoyin
 * Date: 13-3-30
 * To change this template use File | Settings | File Templates.
 */
public class DoubleTest {
	
    public static void main(String[] args) {
    	// ��1�ӵ�ĳ����
        int toValue = 100;
        double sum = 0;
        for (int i = 1; i <= toValue; i++) {
            sum = sum + i;
        }
        System.out.println("the sum of 1 to " + toValue + " is: " + sum);
    }
}
