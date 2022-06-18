package com.frankcooper.platform.leetcode.bank._701_800;

public class _730 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int countPalindromicSubsequences(String s) {
            int MOD = (int) 1e9 + 7;
            int n = s.length();
            //状态  dp(x,i,j) 表示在字符串区间 s[i:j] 中以字符 x 为开头和结尾的不同「回文串」总数，
            // 其中 s[i:j] 表示字符串 s 从下标 i 到下标 j 的子串（包含下标 i 和下标 j）
            int[][][] dp = new int[4][n][n];//只有 abcd四种字符
            for (int i = 0; i < n; i++) dp[s.charAt(i) - 'a'][i][i] = 1;
            for (int len = 2; len <= n; len++) {
                for (int i = 0; i + len <= n; i++) {
                    int j = i + len - 1;//选定i和j的区间 长度为len
                    for (char c = 'a'; c <= 'd'; c++) {
                        int k = c - 'a';
                        if (s.charAt(i) == c && s.charAt(j) == c) {//s[i]与s[j]均相同
                            dp[k][i][j] = (2 +
                                    //两段求和取模
                                    (dp[0][i + 1][j - 1] + dp[1][i + 1][j - 1]) % MOD +
                                    (dp[2][i + 1][j - 1] + dp[3][i + 1][j - 1]) % MOD
                            ) % MOD;
                        } else if (s.charAt(i) == c) {//s[j]不同
                            dp[k][i][j] = dp[k][i][j - 1];
                        } else if (s.charAt(j) == c) {//s[i]不同
                            dp[k][i][j] = dp[k][i + 1][j];
                        } else {//s[i]与s[j]均相不同
                            dp[k][i][j] = dp[k][i + 1][j - 1];
                        }
                    }
                }
            }
            int res = 0;
            //分步求和取模
            for (int k = 0; k < 4; k++) res = (res + dp[k][0][n - 1]) % MOD;
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int countPalindromicSubsequences(String s) {
            int MOD = (int) 1e9 + 7;
            int n = s.length();
            //定义 dp(i,j) 来表示字符串区间 s[i:j] 中的不同的回文串个数
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) dp[i][i] = 1;
            for (int len = 2; len <= n; len++) {
                for (int i = 0; i + len <= n; i++) {
                    int j = i + len - 1;
                    if (s.charAt(i) == s.charAt(j)) {
                        int lo = i + 1, hi = j - 1;
                        while (lo <= hi && s.charAt(lo) != s.charAt(i)) lo++;
                        while (lo <= hi && s.charAt(hi) != s.charAt(j)) hi--;
                        if (lo > hi) {//lo > hi
                            dp[i][j] = (2 + dp[i + 1][j - 1] * 2) % MOD;
                        } else if (lo == hi) {// lo = hi
                            dp[i][j] = (1 + dp[i + 1][j - 1] * 2) % MOD;
                        } else {//lo < hi
                            dp[i][j] = (dp[i + 1][j - 1] * 2 % MOD - dp[lo + 1][hi - 1] + MOD) % MOD;
                        }
                    } else {
                        //相减后可能会为负数 +MOD后恢复
                        dp[i][j] = ((dp[i][j - 1] + dp[i + 1][j]) % MOD - dp[i + 1][j - 1] + MOD) % MOD;
                    }
                }
            }
            return dp[0][n - 1];
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
