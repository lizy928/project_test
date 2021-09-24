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

    // 将前面额冒泡排序算法，封装成一个方法
    public static int[] bubbleSort() {
        int[] arr = {6,2,6,8,10,6};
        // 冒泡排序 的时间复杂度 O(n^2), 自己写出
        int temp = 0; // 临时变量
        boolean flag = false; // 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置 flag!!!, 进行下次判断
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] array = SortTestHelper.generateRandomArray(100, 0, 1000);

        System.out.println("排序前。。。。。。。。");
        for (int j : array) {
            System.out.print(j + ",");
        }

        //排序
        bubbleSort(array);

        System.out.println("\n排序后。。。。。。。。");
        for (int j : array) {
            System.out.print(j + ",");
        }
    }
}
