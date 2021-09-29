package com.dlion.testproject.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序
 * <p>
 * 其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {10, 2, 35, 100, 235, 28};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        int max = 0;
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        // 按位数循环
        for (int i = 1; max / i > 0; i = i * 10) {
            int[][] buckets = new int[arr.length][10];
            for (int j = 0; j < arr.length; j++) {
                int index = (arr[j] / i) % 10;
                buckets[j][index] = arr[j];
            }
            //清理
            int index = 0;
            for (int k = 0; k < 10; k++) {
                for (int l = 0; l < arr.length; l++) {
                    if (buckets[l][k] != 0) {
                        arr[index++] = buckets[l][k];
                    }
                }
            }
        }
    }
}
