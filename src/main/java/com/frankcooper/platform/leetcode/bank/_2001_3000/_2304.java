package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2304 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int minPathCost(int[][] grid, int[][] moveCost) {
            int m = grid.length, n = grid[0].length;
            int[][] dp = new int[m][n];//dp[i][j]表示grid[i][j]为结尾，累积的路径的最小代价
            for (int i = 0; i < m; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
            //初始化:第一行的路径
            for (int j = 0; j < n; j++) dp[0][j] = grid[0][j];
            //一般化
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                    }
                }
            }
            int res = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) res = Math.min(res, dp[m - 1][j]);
            return res;
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
