package com.frankcooper.bank._1_100;

import com.frankcooper.utils.PrintUtils;

import java.util.Arrays;

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
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }

        }
        return dp[col - 1];
    }


    /**
     * follow up 1 : 打印一条最短路径
     */
    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] grid = PrintUtils.processSymbol("[[1,3,1],[1,5,1],[2,2,1]]");
            handler.printShortestPath(grid);
        }

        int R, C;

        public void printShortestPath(int[][] grid) {
            if (grid == null || grid.length == 0) return;
            R = grid.length;
            C = grid[0].length;//行 * 列
            int[][] dp = new int[R][C];
            dp[0][0] = grid[0][0];
            for (int i = 1; i < R; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
            for (int j = 1; j < C; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
            for (int i = 1; i < R; i++) {
                for (int j = 1; j < C; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            dfs(dp, grid, R - 1, C - 1);
        }

        public void dfs(int[][] dp, int[][] grid, int i, int j) {
            if (i == 0 && j == 0) {
                System.out.printf("<-(%d,%d)", i, j);
                System.out.println();
            }
            boolean f = (i == R - 1 && j == C - 1);
            if (i >= 1 && grid[i][j] + dp[i - 1][j] == dp[i][j]) {
                System.out.printf((f ? "" : "<-") + "(%d,%d)", i, j);
                dfs(dp, grid, i - 1, j);
            }
            if (j >= 1 && grid[i][j] + dp[i][j - 1] == dp[i][j]) {
                System.out.printf((f ? "" : "<-") + "(%d,%d)", i, j);
                dfs(dp, grid, i, j - 1);
            }
        }

    }


    static class _2nd {


        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] grid = PrintUtils.processSymbol("[[1,3,1],[1,5,1],[4,2,1]]");
            handler.minPathSum(grid);
        }

        int[][] cache;
        int R, C;

        int[][] dirs = {{1, 0}, {0, 1}};

        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0) return 0;
            R = grid.length;
            C = grid[0].length;//行 * 列
            cache = new int[R][C];
            for (int i = 0; i < R; i++) Arrays.fill(cache[i], -1);//初始化
            return dfs(grid, 0, 0);
        }


        private int dfs(int[][] grid, int r, int c) {
            if (r == R - 1 && c == C - 1) return grid[r][c];
            if (cache[r][c] != -1) return cache[r][c];
            int ans = Integer.MAX_VALUE / 2;// 设置一个最大值
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (!inArea(nr, nc)) continue;
                ans = Math.min(ans, dfs(grid, nr, nc));
            }
            return cache[r][c] = ans + grid[r][c];
        }


        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }


    }

}
