package com.dlion.testproject.algorithm.calculate;

import java.util.Arrays;

/**
 * 整数数组中求最大连续子序列之和
 *
 */
public class MaxSubsequences {

    public static void main(String[] args) {
        int[] num=new int[]{0,-1,-5,2,3,1,-1,5,-3,2};
        getMax(num);
        System.out.println("______________");
        int array = maxSubArray(num);
        System.out.println(Arrays.toString(args));
    }

    // 求最大值
    public static void getMax(int[] num){
        int s=0;	// 起始下标
        int e=0;	// 结束下标
        int max=0;  // 最大值
        int temp=0;	// 中间变量
        int ts=0;
        for(int i=0;i<num.length;i++){
            temp=temp+num[i];
            if(temp<0){
                ts=i+1;
                e=i+1;
                temp=0;
            }else{
                if(temp>max){
                    s=ts;
                    e=i;
                    max=temp;
                }
            }
        }
        System.out.println("maxsum="+max+",start:"+s+",end="+e);
    }

    public static int maxSubArray(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        int res = nums[0];
        for(int i = 1;i<nums.length;i++){
            nums[i] = Math.max(nums[i-1]+nums[i], nums[i]);
            if(res<nums[i]){
                res = nums[i];
            }
        }
        return res;
    }
}
