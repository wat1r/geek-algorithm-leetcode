package com.frankcooper.bank._601_700;

import java.util.Arrays;

public class _674 {

    public static void main(String[] args) {

    }


    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    public int findLengthOfLCIS2nd(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int max = 1;
        int[] dp = new int[2];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i % 2] = 1;
            if (nums[i] > nums[i - 1]) {
                dp[i % 2] += dp[(i - 1) % 2];
            }
            max = Math.max(max, dp[i % 2]);
        }
        return max;
    }

}
