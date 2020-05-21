package com.frankcooper.bank;

public class _5 {
    static _5 handler = new _5();

    public static void main(String[] args) {
        handler.longestPalindrome("babad");
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        char[] chas = s.toCharArray();
        int n = chas.length;
        boolean[][] dp = new boolean[n][n];
        String res = "";
        int max = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (chas[i] == chas[j] && ((j - i) <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
                if (dp[i][j] && max < (j - i + 1)) {
                    max = j - i + 1;
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }
}
