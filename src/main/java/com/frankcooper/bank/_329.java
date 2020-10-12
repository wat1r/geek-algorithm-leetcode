package com.frankcooper.bank;


public class _329 {
    static _329 handler = new _329();

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };

        handler.longestIncreasingPath(matrix);
    }


    int[][] memo;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        memo = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, i, j));
                PrintUtils.printMatrix(memo);
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int i, int j) {
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j] += 1;
        for (int[] dir : directions) {
            int nextI = i + dir[0], nextJ = j + dir[1];
            if (inArea(nextI, nextJ) && matrix[i][j] < matrix[nextI][nextJ]) {
                memo[i][j] = Math.max(memo[i][j], dfs(matrix, nextI, nextJ) + 1);
            }
        }
        return memo[i][j];
    }


    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }


}
