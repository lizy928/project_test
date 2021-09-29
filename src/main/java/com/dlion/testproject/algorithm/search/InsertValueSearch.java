package com.dlion.testproject.algorithm.search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 3));
    }

    public static int insertValueSearch(int[] arr, int left, int right, int num) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) * (num - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (midVal < num) {
            return insertValueSearch(arr, left, mid - 1, num);
        } else if (midVal > num) {
            return insertValueSearch(arr, mid + 1, right, num);
        } else return mid;
    }

}
