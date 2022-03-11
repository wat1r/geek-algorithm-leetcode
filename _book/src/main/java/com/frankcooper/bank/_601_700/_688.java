package com.frankcooper.bank._601_700;

import java.util.*;

import org.junit.Assert;

public class _688 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         * 定义 f[i][j][p] 为从位置 (i, j) 出发，使用步数不超过 p 步，最后仍在棋盘内的概率
         *
         * @param n
         * @param k
         * @param row
         * @param column
         * @return
         */
        public double knightProbability(int n, int k, int row, int column) {
            int[][] dirs = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
            double[][][] f = new double[n][n][k + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    f[i][j][0] = 1;
                }
            }
            for (int p = 1; p <= k; p++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        for (int[] d : dirs) {
                            int ni = i + d[0], nj = j + d[1];
                            if (ni < 0 || ni >= n || nj < 0 || nj >= n) continue;
                            f[i][j][p] += f[ni][nj][p - 1] / 8;
                        }
                    }
                }
            }
            return f[row][column][k];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public double knightProbability(int n, int k, int row, int column) {
            int[][] dirs = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
            double[][][] f = new double[k + 1][n][n];
            for (int step = 0; step <= k; step++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (step == 0) {
                            f[0][i][j] = 1;
                        } else {
                            for (int[] d : dirs) {
                                int ni = i + d[0], nj = j + d[1];
                                if (ni < 0 || ni >= n || nj < 0 || nj >= n) continue;
                                f[step][i][j] += f[step - 1][ni][nj] / 8;
                            }
                        }
                    }
                }
            }
            return f[k][row][column];
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
