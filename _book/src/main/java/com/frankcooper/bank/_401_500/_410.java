package com.frankcooper.bank._401_500;

import java.util.Arrays;

/**
 * @Date 2020/7/28
 * @Author Frank Cooper
 * @Description
 */
public class _410 {

    static _410 handler = new _410();

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        handler.splitArray(nums, m);
    }


    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = nums[i] + prefix[i];
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(m, i); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], prefix[i] - prefix[k]));
                }
            }
        }
//        System.out.println(`);
        return dp[n][m];
    }
}
