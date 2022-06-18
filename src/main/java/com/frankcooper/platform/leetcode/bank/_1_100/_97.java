package com.frankcooper.platform.leetcode.bank._1_100;


public class _97 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean isInterleave(String s1, String s2, String s3) {

            int m = s1.length(), n = s2.length(), o = s3.length();
            if (m + n != o) return false;
            boolean[][] f = new boolean[m + 1][n + 1];//f[i][j]表示 s1的 [0...i-1]  s2 的[0...j-1]能否形成s3[0...i+j-1] 能否形成交错字符
            f[0][0] = true;
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    int t = i + j - 1;
                    if (i >= 1) f[i][j] = f[i][j] || (s1.charAt(i - 1) == s3.charAt(t) && f[i - 1][j]);
                    if (j >= 1) f[i][j] = f[i][j] || (s2.charAt(j - 1) == s3.charAt(t) && f[i][j - 1]);
                }
            }
            return f[m][n];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
