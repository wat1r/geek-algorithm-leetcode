package com.frankcooper.platform.leetcode.swordoffer;

import com.frankcooper.utils.PrintUtils;

public class Sword_47 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] grid = PrintUtils.processSymbol("[[1,3,1],[1,5,1],[4,2,1]]");
            handler.maxValue(grid);
        }

        public int maxValue(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[][] f = new int[m][n];
            f[0][0] = grid[0][0];
            for (int i = 1; i < m; i++) f[i][0] = f[i - 1][0] + grid[i][0];
            for (int j = 1; j < n; j++) f[0][j] = f[0][j - 1] + grid[0][j];
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]) + grid[i][j];
                }
            }
            return f[m - 1][n - 1];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] grid = PrintUtils.processSymbol("[[1,3,1],[1,5,1],[4,2,1]]");
            handler.maxValue(grid);
        }

        public int maxValue(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[][] f = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 0) {
                        f[0][0] = grid[0][0];
                        continue;
                    }
                    if (i == 0) f[0][j] = grid[0][j] + f[0][j - 1];
                    else if (j == 0) f[i][0] = grid[i][0] + f[i - 1][0];
                    else f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]) + grid[i][j];
                }
            }

            return f[m - 1][n - 1];
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int maxValue(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 0) continue;
                    if (i == 0) grid[i][j] += grid[i][j - 1];
                    else if (j == 0) grid[i][j] += grid[i - 1][j];
                    else grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
                }
            }
            return grid[m - 1][n - 1];
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
