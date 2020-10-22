package com.sharpjvm.jvm.test;

/**
 * �۰���ҷ����ԡ�
 * 
 * User: zhuguoyin
 * Date: 13-3-30
 * To change this template use File | Settings | File Templates.
 */
public class BinSearchTest {

    public static void main(String[] args) {
    	// ��һ���ź�����������ҵ�Ҫ�ҵ�ֵ
        int findValue = 61;
        int [] sortedArray = {23, 44, 56, 61, 99, 123, 342, 543, 628, 987};
        int index = findIndex(sortedArray, findValue);
        if (index == -1) {
            System.out.println("didn't find " + findValue);
        } else {
            System.out.println("the index of " + findValue + " is: " + (index + 1));
        }
    }

    private static int findIndex(int[] sortedArray, int findValue) {
        int start = 0;
        int end = sortedArray.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (sortedArray[mid] == findValue) {
                return mid;
            }
            if (findValue < sortedArray[mid]) {
                end = mid - 1;
                continue;
            }
            start = mid + 1;
        }
        return -1;
    }
}
