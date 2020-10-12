package com.frankcooper.bank;

import com.frankcooper.swordoffer.utils.PrintUtils;

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


}
