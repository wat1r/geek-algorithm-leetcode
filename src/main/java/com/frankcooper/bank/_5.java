package com.frankcooper.bank;

public class _5 {
    static _5 handler = new _5();

//    public static void main(String[] args) {
//        handler.longestPalindrome("babad");
//    }


    static class _1st {
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


    static class _2nd {
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) return "";
            int n = s.length();
            boolean[][] f = new boolean[n][n];
            String ans = "";
            int maxL = 0;
            for (int j = 0; j < n; ++j) {
                for (int i = 0; i <= j; i++) {
                    if (i == j) f[i][i] = true;
                    if (s.charAt(i) == s.charAt(j) && ((j - i) <= 2 || f[i + 1][j - 1])) {
                        f[i][j] = true;
                    }
                    if (f[i][j] && (j - i + 1) > maxL) {
                        maxL = j - i + 1;
                        ans = s.substring(i, j + 1);
                    }
                }
            }
            return ans;
        }
    }

    static class _3rd {
        /**
         * 5. 最长回文子串 LeetCode Medium
         *
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }
            int n = s.length();
            int start = 0, end = 0;
            for (int i = 0; i < n; i++) {
                //获取到当前点i 的奇回文和偶回文的最大长度
                int len1 = expandBySeed(s, i, i);
                int len2 = expandBySeed(s, i, i + 1);
                //取最大长度，然后扩展
                int len = Math.max(len1, len2);
                if (len > (end - start)) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }


        /**
         * 由中心往两边扩散，返回满足最大回文的长度
         *
         * @param s
         * @param start
         * @param end
         * @return
         */
        private int expandBySeed(String s, int start, int end) {
            int n = s.length();
            while (start >= 0 && end < n && s.charAt(start) == s.charAt(end)) {
                start--;
                end++;
            }
            return end - start - 1;
        }
    }
}
