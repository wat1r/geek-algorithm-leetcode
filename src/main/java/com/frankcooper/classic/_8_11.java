package com.frankcooper.classic;

public class _8_11 {
    public static void main(String[] args) {

    }
    //同518
    int[] coins = {1, 5, 10, 25};

    public int waysToChange(int n) {
        int m = coins.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = coins[i]; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - coins[i]] )%1000000007;
            }
        }
        return dp[n];
    }

}
