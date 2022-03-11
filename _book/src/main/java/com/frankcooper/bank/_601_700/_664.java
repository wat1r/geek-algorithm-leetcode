package com.frankcooper.bank._601_700;

public class _664 {

    static _664 handler = new _664();

    public static void main(String[] args) {
        handler.strangePrinter("aaabbb");
    }


    public int strangePrinter(String s) {

        int n = s.length();
        if (n == 0) return 0;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) dp[i][i] = 1;
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n; i++) {
                int j = i + len - 1;
                if (j >= n) break;
                dp[i][j] = dp[i + 1][j] + 1;
                for (int k = i + 1; k <= j; k++) {
                    if (s.charAt(i) == s.charAt(k)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k + 1][j]);
                    }
                }

            }
        }
        return dp[0][n - 1];
    }


}
