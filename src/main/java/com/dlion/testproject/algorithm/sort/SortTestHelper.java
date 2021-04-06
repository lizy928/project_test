package com.dlion.testproject.algorithm.sort;

/**
 * 排序工具类
 *
 * @author lzy
 * @date 2021/1/4
 */
public class SortTestHelper {

    /**
     * 生成随机数
     *
     * @param num      位数
     * @param startNum 最小数
     * @param endNum   最大数
     * @return
     */
    public static int[] generateRandomArray(int num, int startNum, int endNum) {
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            int random = (int) (Math.random() * (endNum - startNum) + startNum);
            arr[i] = random;
        }
        return arr;
    }
}
