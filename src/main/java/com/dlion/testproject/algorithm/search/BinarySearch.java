package com.dlion.testproject.algorithm.search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {5, 6, 1, 3, 6};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 3));

    }

    public static int binarySearch(int[] arr, int left, int right, int num) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (midVal > num) {
            return binarySearch(arr, left, mid - 1, num);
        } else if (midVal < num) {
            return binarySearch(arr, mid + 1, right, num);
        } else return mid;
    }

}
