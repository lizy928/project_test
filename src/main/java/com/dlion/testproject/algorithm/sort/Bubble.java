package com.dlion.testproject.algorithm.sort;

/**
 * 冒泡排序
 *
 * @author lzy
 * @date 2021/1/4
 */
public class Bubble {

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = SortTestHelper.generateRandomArray(100, 0, 1000);

        System.out.println("排序前。。。。。。。。");
        for (int j : array) {
            System.out.print(j +",");
        }

        //排序
        bubbleSort(array);

        System.out.println("\n排序后。。。。。。。。");
        for (int j : array) {
            System.out.print(j + ",");
        }
    }
}
