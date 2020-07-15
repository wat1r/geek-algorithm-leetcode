package com.frankcooper.bank;

import sun.java2d.opengl.OGLRenderQueue;

/**
 * @Date 2020/7/15
 * @Author Frank Cooper
 * @Description
 */
public class _64 {


    static _64 handler = new _64();

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        handler.minPathSum2nd(grid);
    }


    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;//行 * 列
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    public int minPathSum2nd(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length, col = grid[0].length;
        int[] dp = new int[col];
        dp[0] = grid[0][0];
        for (int j = 1; j < col; j++) dp[j] = dp[j - 1] + grid[0][j];
        for (int i = 1; i < row; i++) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i ][j];
            }

        }
        return dp[col-1];
    }


}
