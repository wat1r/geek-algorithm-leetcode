package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1335 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] A = new int[]{6, 5, 4, 3, 2, 1};
            int d = 2;
            Assert.assertEquals(handler.minDifficulty(A, d), 7);

        }


        public int minDifficulty(int[] A, int d) {
            int n = A.length;
            if (n < d) return -1;
            int[][] dp = new int[d][n];//dp[i][j]表示前j个任务[0...j-1]  在i-1天完成 最小难度
            int max = 0;
            for (int j = 0; j < n; j++) {
                max = Math.max(max, A[j]);
                dp[0][j] = max;
            }
            for (int i = 1; i < d; i++) {
                for (int j = i; j < n; j++) {
                    max = A[j];
                    for (int k = j - 1; k >= i - 1; k--) {
                        max = Math.max(max, A[k + 1]);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + max);
                    }
                }
            }
            return dp[d - 1][n - 1];
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
