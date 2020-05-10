package com.frankcooper.bank;

public class _1312 {

    static _1312 handler = new _1312();

    public static void main(String[] args) {

    }


    /**
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        // $dp[i][j]$表示子串$str[i...j]$范围内的最少添加多少个字符后，可以形成回文子串
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int j = 1; j < n; j++) {
            dp[j - 1][j] = (s.charAt(j - 1) == s.charAt(j)) ? 0 : 1;
            for (int i = j - 2; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i + 1][j - 1];
                else dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
            }
        }
        return dp[0][n - 1];
    }


}
