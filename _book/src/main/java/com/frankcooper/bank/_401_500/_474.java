package com.frankcooper.bank._401_500;

import com.frankcooper.utils.PrintUtils;

/**
 * @Date 2020/9/15
 * @Author Frank Cooper
 * @Description
 */
public class _474 {

    static _474 handler = new _474();

    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        handler.findMaxForm(strs, m, n);
    }


    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] counter = counter(str);
            for (int i = m; i >= counter[0]; i--) {
                for (int j = n; j >= counter[1]; j--) {
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i - counter[0]][j - counter[1]] + 1);
                }
            }
            PrintUtils.printMatrix(dp);
        }
        return dp[m][n];
    }


    public int findMaxForm1st(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int k = 1; k < len + 1; ++k) {
            int[] counter = counter(strs[k - 1]);
            for (int i = 0; i < m + 1; ++i) {
                for (int j = 0; j < n + 1; ++j) {
                    if (i >= counter[0] && j >= counter[1]) {
                        dp[k][i][j] = Math.max(dp[k][i][j],
                                dp[k - 1][i - counter[0]][j - counter[1]] + 1);
                    }
                    dp[k][i][j] = Math.max(dp[k][i][j], dp[k - 1][i][j]);
                }
            }
        }
        return dp[len][m][n];
    }


    /**
     * 计算str字符串中的 0和1的个数， counter[0] 是 "0"的个数，counter[1]是"1"的个数
     *
     * @param str
     * @return
     */
    public int[] counter(String str) {
        int[] counter = new int[2];
        for (char c : str.toCharArray()) counter[c - '0']++;
        return counter;
    }


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String[] strs = {"10", "0001", "111001", "1", "0"};
            int m = 5, n = 3;
            handler.findMaxForm(strs, m, n);

        }


        public int findMaxForm(String[] strs, int m, int n) {
            int o = strs.length;
            //f[k][i][j]表示在处理「k-1」个字符时，使用i个0和j个1的前提下，能够拼接出来的字符串的数量
            int[][][] f = new int[o + 1][m + 1][n + 1];
            for (int k = 1; k <= o; k++) {
                int[] cnt = count(strs[k - 1]);
                for (int i = 0; i <= m; i++) {
                    for (int j = 0; j <= n; j++) {
                        if (i >= cnt[0] && j >= cnt[1]) {
                            f[k][i][j] = Math.max(f[k][i][j], f[k - 1][i - cnt[0]][j - cnt[1]] + 1);
                        }
                        f[k][i][j] = Math.max(f[k][i][j], f[k - 1][i][j]);
                    }
                }
            }
            return f[o][m][n];
        }


        private int[] count(String s) {
            int[] res = new int[2];
            for (char c : s.toCharArray()) res[c - '0']++;
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int findMaxForm(String[] strs, int m, int n) {
            int o = strs.length;
            int[][] f = new int[m + 1][n + 1];
            for (String s : strs) {
                int[] cnt = count(s);
                for (int i = m; i >= cnt[0]; --i) {
                    for (int j = n; j >= cnt[1]; --j) {
                        f[i][j] = Math.max(f[i][j], f[i - cnt[0]][j - cnt[1]] + 1);
                    }
                }
            }

            return f[m][n];
        }


        private int[] count(String s) {
            int[] res = new int[2];
            for (char c : s.toCharArray()) res[c - '0']++;
            return res;
        }
    }


    static class _3rd {

        public static void main(String[] args) {

        }

        /**
         * int[][][] dp = new int[len + 1][m + 1][n + 1];
         * //len是字符数组的个数，m相当于背包容量0的个数，n相当于背包容量1的个数
         * //要求的是dp[k][m][n]，即第k个字符strs[k-1]，一路走来，m个0，和n个1全部消耗掉，能拼出的字符串的数量，即个数
         */

        public int findMaxForm(String[] strs, int m, int n) {
            int len = strs.length;
            int[][][] f = new int[len + 1][m + 1][n + 1];
            for (int k = 1; k <= len; k++) {
                int[] res = count(strs[k - 1]);
                for (int i = 0; i <= m; i++) {
                    for (int j = 0; j <= n; j++) {
                        if (i >= res[0] && j >= res[1]) {
                            f[k][i][j] = Math.max(f[k][i][j], f[k - 1][i - res[0]][j - res[1]] + 1);
                        }
                        f[k][i][j] = Math.max(f[k][i][j], f[k - 1][i][j]);
                    }
                }
            }
            return f[len][m][n];
        }


        private int[] count(String s) {
            int[] res = new int[2];
            for (char c : s.toCharArray()) res[c - '0']++;
            return res;
        }


    }


    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            String[] strs = {"10", "0001", "111001", "1", "0"};
            int m = 5, n = 3;
            handler.findMaxForm(strs, m, n);
        }


        public int findMaxForm(String[] strs, int m, int n) {
            return helper(strs, strs.length - 1, m, n);
        }


        /**
         * 当前[0....idx]范围内，i个0 j 个1能拼出的字符串的数量，即个数
         *
         * @param strs
         * @param idx
         * @param i
         * @param j
         * @return
         */
        private int helper(String[] strs, int idx, int i, int j) {
            if (idx < 0) return 0;
            int[] t = count(strs[idx]);
            int zeros = t[0], ones = t[1];//当前字符0 和 1 的数量
            if (i >= zeros && j >= ones)
                return Math.max(helper(strs, idx - 1, i, j),
                        helper(strs, idx - 1, i - zeros, j - ones) + 1);
            else return helper(strs, idx - 1, i, j);

        }


        private int[] count(String s) {
            int[] res = new int[2];
            for (char c : s.toCharArray()) res[c - '0']++;
            return res;
        }
    }


    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            String[] strs = {"10", "0001", "111001", "1", "0"};
            int m = 5, n = 3;
            handler.findMaxForm(strs, m, n);
        }

        Integer[][][] cache;


        public int findMaxForm(String[] strs, int m, int n) {
            cache = new Integer[strs.length][m + 1][n + 1];
            return helper(strs, strs.length - 1, m, n);
        }


        /**
         * 当前[0....idx]范围内，i个0 j 个1能拼出的字符串的数量，即个数
         *
         * @param strs
         * @param idx
         * @param i
         * @param j
         * @return
         */
        private int helper(String[] strs, int idx, int i, int j) {
            if (idx < 0) return 0;
            if (cache[idx][i][j] != null) return cache[idx][i][j];
            int[] t = count(strs[idx]);
            int zeros = t[0], ones = t[1];//当前字符0 和 1 的数量
            if (i >= zeros && j >= ones)
                cache[idx][i][j] = Math.max(helper(strs, idx - 1, i, j),
                        helper(strs, idx - 1, i - zeros, j - ones) + 1);
            else cache[idx][i][j] = helper(strs, idx - 1, i, j);
            return cache[idx][i][j];

        }


        private int[] count(String s) {
            int[] res = new int[2];
            for (char c : s.toCharArray()) res[c - '0']++;
            return res;
        }
    }


    static class _6th {

    }


}
