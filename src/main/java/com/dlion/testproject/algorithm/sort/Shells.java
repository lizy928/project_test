package com.dlion.testproject.algorithm.sort;

/**
 * 希尔排序(Shell's Sort)是插入排序的一种又称“缩小增量排序”（Diminishing Increment Sort），
 * 是直接插入排序算法的一种更高效的改进版本。希尔排序是非稳定排序算法
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，
 * 每组包含的关键词越来越多，当增量减至 1 时，整个文件恰被分成一组，算法便终止
 *
 * @author lzy
 * @date 2021/1/4
 */
public class Shells {

    public static void shellsSort(int[] array) {
        int gap = array.length;
        while (true) {
            gap /= 2;
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < array.length; j += gap) {
                    int k = j - gap;
                    while (k >= 0 && array[k] > array[k + gap]) {
                        int temp = array[k];
                        array[k] = array[k + gap];
                        array[k + gap] = temp;
                        k -= gap;
                    }
                }
            }
            if (gap == 1) {
                break;
            }
        }
    }

    public static void shellsSort2(int[] array) {
        int j;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                Comparable temp = array[i];
                for (j = i; j >= gap && temp.compareTo(array[j - gap]) < 0; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = (int) temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = SortTestHelper.generateRandomArray(10, 0, 100);

        System.out.println("排序前。。。。。。。。");
        for (int j : array) {
            System.out.print(j + ",");
        }

        //排序
        shellsSort2(array);

        System.out.println("\n排序后。。。。。。。。");
        for (int j : array) {
            System.out.print(j + ",");
        }
    }
}
