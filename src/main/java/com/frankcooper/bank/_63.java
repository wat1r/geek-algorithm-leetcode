package com.frankcooper.bank;

/**
 * @Date 2020/7/6
 * @Author Frank Cooper
 * @Description
 */
public class _63 {
    static _63 handler = new _63();

    public static void main(String[] args) {
        handler.uniquePathsWithObstacles(new int[][]{{0, 0}});
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {//第一列
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int j = 0; j < m; j++) {//第一行
            if (obstacleGrid[0][j] == 1) break;
            dp[0][j] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }


    public int uniquePathsWithObstacles2nd(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        //列*行
        int m = obstacleGrid[0].length, n = obstacleGrid.length;
        int[] dp = new int[m];
        for (int j = 0; j < m; j++) {
            if (obstacleGrid[0][j] == 1) break;
            dp[j] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                dp[j] = (j == 0) ? dp[j] : dp[j] + dp[j - 1];
            }
        }
        return dp[m-1];
    }





}
