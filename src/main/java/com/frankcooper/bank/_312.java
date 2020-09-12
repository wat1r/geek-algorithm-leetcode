package com.frankcooper.bank;

import java.util.Map;

/**
 * @Date 2020/9/12
 * @Author Frank Cooper
 * @Description
 */
public class _312 {

    static _312 handler = new _312();


    public static void main(String[] args) {

        int[] nums = new int[]{3, 1, 5, 8};
        handler.maxCoins(nums);
    }


    public int maxCoins(int[] nums) {

        int n = nums.length;
        //弄一个辅助数组
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        for (int i = 1; i <= n; i++) val[i] = nums[i - 1];
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][n + 1];
    }

}
