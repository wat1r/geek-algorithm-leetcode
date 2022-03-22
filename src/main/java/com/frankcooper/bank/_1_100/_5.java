package com.frankcooper.bank._1_100;

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


    static class _4th {
        Boolean[][] memo;

        public String longestPalindrome(String s) {
            memo = new Boolean[s.length()][s.length()];
            String ans = "";
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j < s.length(); j++) {
                    if (helper(s, i, j) && j - i + 1 > ans.length()) {
                        ans = s.substring(i, j + 1);
                    }
                }
            }
            return ans;
        }


        private boolean helper(String s, int start, int end) {
            if (start == end) return true;
            if (start + 1 == end) return s.charAt(start) == s.charAt(end);
            if (memo[start][end] != null) return memo[start][end];
            boolean ans = false;
            if (s.charAt(start) == s.charAt(end)) {
                ans = helper(s, start + 1, end - 1);
            }
            return memo[start][end] = ans;
        }
    }


    static class _5th {


        public static void main(String[] args) {
            _5th handler = new _5th();
            handler.longestPalindrome("cbbd");
        }

        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) return "";
            int n = s.length();
            boolean[][] f = new boolean[n][n];
            for (int i = 0; i < n; ++i) f[i][i] = true;
            int maxLen = 1, start = 0;
            for (int k = 2; k <= n; k++) {
                // System.out.printf("k:%d\n", k);
                for (int i = 0; i < n - k + 1; i++) {
                    int j = i + k - 1;
                    // System.out.printf("i:%d,j:%d\n", i, j);
                    if (s.charAt(i) == s.charAt(j) && (k == 2 || f[i + 1][j - 1])) {
                        f[i][j] = true;
                        if (maxLen < k) {
                            maxLen = k;
                            start = i;
                        }
                    }
                }
            }
            return s.substring(start, start + maxLen);
        }
    }

    static class _6th {

        public static void main(String[] args) {
            _6th handler = new _6th();
            handler.longestPalindrome("cbbd");
        }

        public String longestPalindrome(String s) {
            if (s == null || s.length() <= 0) return s;
            int n = s.length();
            boolean[][] f = new boolean[n][n];
            for (int i = 0; i < n; i++) f[i][i] = true;
            int maxLen = 1, start = 0;
            for (int i = n - 1; i >= 0; i--) {
                for (int k = 1; k < n - i; k++) {
                    int j = k + i;
                    if (s.charAt(i) == s.charAt(j)) {
                        f[i][j] = (k == 1) || f[i + 1][j - 1];
                    }
                    if (f[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        start = i;
                    }
                }
            }
            return s.substring(start, start + maxLen);
        }

    }

    static class _7th {
        public String longestPalindrome(String s) {
            int n = s.length();
            //f[i][j]表示s中[i...j]下标范围内是否是一个回文子串
            boolean[][] f = new boolean[n][n];
            for (int i = 0; i < n; i++) f[i][i] = true;
            int maxLen = 1, startIdx = 0;
            for (int k = 2; k <= n; k++) {
                for (int i = 0; i < n - k + 1; i++) {
                    int j = i + k - 1;
                    if (s.charAt(i) == s.charAt(j) && (k == 2 || f[i + 1][j - 1])) {
                        f[i][j] = true;
                        if (k > maxLen) {
                            startIdx = i;
                            maxLen = k;
                        }
                    }
                }
            }
            return s.substring(startIdx, startIdx + maxLen);
        }
    }


    static class _8th {
        public String longestPalindrome(String s) {
            int n = s.length();
            //f[j][i] 表示 s[j...i]这个区间的子串是否是回文，true是回文 false不是回文
            boolean[][] f = new boolean[n][n];
            int maxx = 0;//最长的回文子串的长度
            String res = "";//结果
            for (int i = 0; i < n; i++) {//枚举右区间
                for (int j = 0; j <= i; j++) {//枚举左区间，界限是右区间
                    //s[i] == s[j]时，且去掉头尾的字符串是回文子串，当前s[j...i]也是回文子串
                    if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || f[j + 1][i - 1])) {
                        f[j][i] = true;
                    }
                    //更新长度s[0..2]长度是3 2-0+1=3
                    if (f[j][i] && maxx < i - j + 1) {
                        maxx = i - j + 1;
                        res = s.substring(j, i + 1);
                    }
                }
            }
            return res;
        }
    }

}
