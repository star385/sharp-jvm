package com.sharpjvm.jvm.test;

/**
 * 斐波那契测试类。
 * 
 * User: zhuguoyin
 * Date: 13-3-30
 * To change this template use File | Settings | File Templates.
 */
public class FibonacciTest {

    public static void main(String[] args) {
        int fibValue = 40;
        System.out.println("the fibonacci array from 1 to " + fibValue + " is:");
        for (int i = 1; i <= fibValue; i++) {
            System.out.print(fib2(i) + "\t");
            if (i % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * 斐波那契函数，运用递归
     * 
     * @param n
     * @return
     */
    private static long fib(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 斐波那契函数，不用递归
     * 
     * @param n
     * @return
     */
    private static long fib2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        int p = 1;
        int q = 1;
        for (int i = 3; i <= n; i++) {
            int temp = q;
            q = p + q;
            p = temp;
        }
        return q;
    }
}
