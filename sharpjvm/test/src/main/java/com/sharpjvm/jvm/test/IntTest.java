package com.sharpjvm.jvm.test;

/**
 * �������ۼӲ����ࡣ
 * 
 * @author zhuguoyin
 *
 */
public class IntTest {

	public static void main(String[] args) {
		int toValue = 100;
		int sum = 0;
		for (int i = 1; i <= toValue; i++) {
			sum = sum + i;
		}
		System.out.println("the sum of 1 to " + toValue + " is: " + sum);
    }
}
