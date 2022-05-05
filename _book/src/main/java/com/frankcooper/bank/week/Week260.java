package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week260 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        public int maximumDifference(int[] nums) {
            int res = -1;
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (nums[j] > nums[i]) {
                        res = Math.max(res, nums[j] - nums[i]);
                    }
                }
            }
            return res;
        }


    }

    //WA
    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] grid = {{2, 5, 4}, {1, 5, 1}};
//            Assert.assertEquals(4, handler.gridGame(grid));
            grid = new int[][]{{1, 3, 1, 15}, {1, 3, 3, 1}};
//            Assert.assertEquals(7, handler.gridGame(grid));
            grid = new int[][]{{20, 3, 20, 17, 2, 12, 15, 17, 4, 15}, {20, 10, 13, 14, 15, 5, 2, 3, 14, 3}};
            Assert.assertEquals(63, handler.gridGame(grid));


        }

        int m, n;

        public long gridGame(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int[][] dp = maxPath(grid);
            dfs(dp, grid, m - 1, n - 1);
            dp = maxPath(grid);
            return dp[m - 1][n - 1];
        }


        //制作dp
        private int[][] maxPath(int[][] grid) {
            int[][] dp = new int[m][n];
            dp[0][0] = grid[0][0];
            for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
            for (int j = 1; j < n; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            return dp;
        }

        //设置0
        public void dfs(int[][] dp, int[][] grid, int i, int j) {
            if (i == 0 && j == 0) {
                grid[0][0] = 0;
                return;
            }
            if (i >= 1 && grid[i][j] + dp[i - 1][j] == dp[i][j]) {
                grid[i][j] = 0;
                dfs(dp, grid, i - 1, j);
            }
            if (j >= 1 && grid[i][j] + dp[i][j - 1] == dp[i][j]) {
                grid[i][j] = 0;
                dfs(dp, grid, i, j - 1);
            }
        }
    }

    static class _2nd_1{
        public static void main(String[] args) {

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
