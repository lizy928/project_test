package com.dlion.testproject.algorithm.sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {

        int[] arr = {7, 5, 3, 2, 4};

        sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        // 插入排序
        // 外层循环，从第二个开始比较
        for (int i = 1; i < arr.length; i++) {
            //内存循环，与前面排好序的数据比较，如果后面的数据小于前面的则交换
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    //如果不小于，说明插入完毕，退出内层循环
                    break;
                }
            }
        }
    }
}
