package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _01_05 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        /**
         * @param first
         * @param second
         * @return
         */
        public boolean oneEditAway(String first, String second) {

            int m = first.length(), n = second.length();
            int[][] dp = new int[m + 1][n + 1];//两个字符的前i j 个字符变成一样的，编辑距离
            for (int i = 0; i <= m; i++) dp[i][0] = i;
            for (int j = 0; j <= n; j++) dp[0][j] = j;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (first.charAt(i - 1) == second.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                    else dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
            return dp[m][n] <= 1;//不编辑或者只有一次编辑的机会
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
