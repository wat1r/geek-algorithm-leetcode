package com.frankcooper.bank._1_100;

/**
 * @Date 2020/7/29
 * @Author Frank Cooper
 * @Description
 */
public class _96 {

    public static void main(String[] args) {

    }

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
