package com.frankcooper.bank;

/**
 * Created by FrankCooper
 * Date 2020/3/13 22:25
 * Description
 */
public class _123 {
    public static void main(String[] args) {

    }


    public int maxProfit(int[] prices) {
        int max_k = 2;
        int n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][max_k][0];
    }

}
