package com.frankcooper.bank._301_400;

/**
 * @Date 2020/7/30
 * @Author Frank Cooper
 * @Description
 */
public class _343 {
    static _343 handler = new _343();

    public static void main(String[] args) {
        handler.integerBreak(2);
    }


    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], j * Math.max(i - j, dp[i - j]));
            }
        }
        return dp[n];
    }
}
