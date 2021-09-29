package com.dlion.testproject.algorithm.sort;

import java.util.Arrays;

public class CountSort {

    public static void main(String[] args) {
        int[] arr = {6, 7, 1, 8, 2, 10};
        int[] countSort = countSort(arr);
        System.out.println(Arrays.toString(countSort));
    }

    public static int[] countSort(int[] arr) {
        int max = arr[0];
        for (int j : arr) {
            if (j > max) {
                max = j;
            }
        }
        int[] temp = new int[max + 1];
        for (int i : arr) {
            temp[i] ++;
        }
        int index = 0;
        int[] result = new int[arr.length];
        for (int i = 0; i < temp.length; i++) {
            while (temp[i] > 0){
                result[index++] = i;
                temp[i]--;
            }
        }
        return result;
    }
}
