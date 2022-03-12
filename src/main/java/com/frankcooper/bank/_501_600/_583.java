package com.frankcooper.bank._501_600;

import java.util.*;

import org.junit.Assert;

public class _583 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int minDistance(String word1, String word2) {
            if (word1 == null || word2 == null) {
                return 0;
            }
            char[] chas1 = word1.toCharArray();
            char[] chas2 = word2.toCharArray();
            int row = chas1.length + 1;
            int col = chas2.length + 1;
            int[][] dp = new int[row][col];
            //init first col
            for (int i = 0; i < row; i++) {
                dp[i][0] = i;
            }
            //init first row
            for (int j = 0; j < col; j++) {
                dp[0][j] = j;
            }
            //general case
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    if (chas1[i - 1] == chas2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 2;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
            return dp[row - 1][col - 1];
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
