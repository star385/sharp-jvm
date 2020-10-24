package com.sharpjvm.jvm.test;

/**
 * �鲢��������ࡣ
 * 
 * User: zhuguoyin
 * Date: 13-3-30
 * To change this template use File | Settings | File Templates.
 */
public class MergeSortTest {

    public static void main(String[] args) {
    	// ��һ�����߰�������鰴��С��������
        int[] originalArray = {45, 332, 52, 9, 232, 34, 43};
        System.out.println("int array before sort is: ");
        for (int i = 0; i < originalArray.length; i++) {
            System.out.print(originalArray[i] + "\t");
        }
        System.out.println();
        int[] arrayAfterSort = mergeSort(originalArray, 0, originalArray.length - 1);
        // ������Ϻ󣬰��������
        System.out.println("int array after sort is: ");
        for (int i = 0; i < arrayAfterSort.length; i++) {
            System.out.print(arrayAfterSort[i] + "\t");
        }
        System.out.println();
    }

    private static int[] mergeSort(int[] originalArray, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(originalArray, start, mid);
            mergeSort(originalArray, mid + 1, end);
            merge(originalArray, start, mid, end);
        }
        return originalArray;
    }

    private static void merge(int[] originalArray, int start, int mid, int end) {
        int[] tmpArr=new int[originalArray.length];
        int i = start;
        int j = mid + 1;
        int k = start;
        while (i <= mid && j <= end) {
            if (originalArray[i] <= originalArray[j]) {
                tmpArr[k++] = originalArray[i++];
            } else {
                tmpArr[k++] = originalArray[j++];
            }
        }
        while (i <= mid) {
            tmpArr[k++] = originalArray[i++];
        }
        while (j <= end) {
            tmpArr[k++] = originalArray[j++];
        }
        for (i = start; i <= end; i++) {
            originalArray[i] = tmpArr[i];
        }
    }

}
