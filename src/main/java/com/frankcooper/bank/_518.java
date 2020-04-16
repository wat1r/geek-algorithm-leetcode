package com.frankcooper.bank;

//518. 零钱兑换 II  518. Coin Change 2 Medium
public class _518 {
    static _518 handler = new _518();

    public static void main(String[] args) {
        handler.change1st(5, new int[]{1, 2, 5});
//        handler.change2nd(5, new int[]{1, 2, 5});
    }

    public int change1st(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                for (int k = 0; k * coins[i - 1] <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * coins[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }

    public int change2nd(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = 1;
        for (int j = 0; j <= amount; j++) dp[0][j] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - coins[i - 1] >= 0) dp[i][j] += dp[i][j - coins[i - 1]];
            }
        }
        return dp[n][amount];
    }


    public int change3rd(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

}
