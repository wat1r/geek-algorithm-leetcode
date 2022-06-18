package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.Arrays;

/**
 * @Date 2020/9/12
 * @Author Frank Cooper
 * @Description
 */
public class _1262 {


    public static void main(String[] args) {

    }


    public int maxSumDivThree(int[] nums) {
        int[] dp = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        int[] arr = new int[3];
        for (int num : nums) {
            int mod = num % 3;
            for (int j = 0; j < 3; j++) {
                arr[(j + mod) % 3] = Math.max(dp[(j + mod) % 3], dp[j] + num);
            }
            dp = Arrays.copyOf(arr,3);
        }
        return dp[0];
    }
}
