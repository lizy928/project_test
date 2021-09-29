package com.dlion.testproject.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 桶排序
 *
 * 将待排序的序列分到若干个桶中，每个桶内的元素再进行个别排序。
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] arr = {3,9,13,25,8, 23,45,32,31};
        int[] bucketSort = bucketSort(arr);
        System.out.println(Arrays.toString(bucketSort));
    }
    public static int[] bucketSort(int[] arr){
        List[] bucket = new ArrayList[5];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<>();
        }
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i]/10;
            bucket[index].add(arr[i]);
        }
        for (List list : bucket) {
            list.sort(null);
        }
        int[] result = new int[arr.length];
        int index = 0;
        for (List list : bucket) {
            for (Object o : list) {
                result[index ++ ] = (int) o;
            }
        }
        return result;
    }
}
