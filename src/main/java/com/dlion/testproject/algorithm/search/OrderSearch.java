package com.dlion.testproject.algorithm.search;

public class OrderSearch {

    public static void main(String[] args) {
        int[] arr = {6, 1, 5, 2, 0, 2};
        System.out.println(orderSearch(arr, 5));
    }

    public static int orderSearch(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == num){
                return i;
            }
        }
        return -1;
    }

}
