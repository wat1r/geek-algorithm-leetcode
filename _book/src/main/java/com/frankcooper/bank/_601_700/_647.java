package com.frankcooper.bank._601_700;

public class _647 {

    static class _1st {


        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.countSubstrings("abc");
        }


        public int countSubstrings(String s) {
            int ans = 0, n = s.length();
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    ans += helper(s, i, j);
                }
            }
            return ans;
        }

        private int helper(String s, int start, int end) {
            if (start >= end) return 1;
            return s.charAt(start) == s.charAt(end) ? helper(s, start + 1, end - 1) : 0;
        }

    }


    static class _2nd {

        Integer[][] memo;

        public int countSubstrings(String s) {
            int ans = 0, n = s.length();
            memo = new Integer[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    ans += helper(s, i, j);
                }
            }
            return ans;
        }

        private int helper(String s, int start, int end) {
            if (start >= end) return 1;
            if (memo[start][end] != null) return memo[start][end];
            return memo[start][end] = s.charAt(start) == s.charAt(end) ? helper(s, start + 1, end - 1) : 0;
        }
    }

    static class _3rd {

        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.countSubstrings("aaa");
        }

        public int countSubstrings(String s) {
            int n = s.length();
            int[][] f = new int[n][n];
            char[] chas = s.toCharArray();
            int ans = 0;
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i; j < n; j++) {
                    if (i == j) f[i][j] = 1;
                    else if (i + 1 == j) f[i][j] = chas[i] == chas[j] ? 1 : 0;
                    else f[i][j] = chas[i] == chas[j] ? f[i + 1][j - 1] : 0;
                    ans += f[i][j];
                }
            }
            return ans;
        }
    }
}
