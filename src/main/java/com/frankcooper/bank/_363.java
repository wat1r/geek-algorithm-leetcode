package com.frankcooper.bank;

import com.frankcooper.swordoffer.utils.PrintUtils;

/**
 * @Date 2020/9/13
 * @Author Frank Cooper
 * @Description
 */
public class _363 {


    static _363 handler = new _363();

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{5, -4, -3, 4}, {-3, -4, 4, 5}, {5, 1, 5, -4}};
        int k = 10;
        handler.maxSumSubmatrix(matrix, k);
    }


    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int x1 = 1; x1 <= rows; x1++) {
            for (int y1 = 1; y1 <= cols; y1++) {
                int[][] dp = new int[rows + 1][cols + 1];
                dp[x1][y1] = matrix[x1 - 1][y1 - 1];
                PrintUtils.printMatrix(dp);
                for (int x2 = x1; x2 <= rows; x2++) {
                    for (int y2 = y1; y2 <= cols; y2++) {
                        dp[x2][y2] = dp[x2 - 1][y2] + dp[x2][y2 - 1]
                                - dp[x2 - 1][y2 - 1] + matrix[x2 - 1][y2 - 1];
                        if (dp[x2][y2] <= k) {
                            max = Math.max(max, dp[x2][y2]);
                        }
                        PrintUtils.printMatrix(dp);
                    }
                }
            }
        }
        return max == Integer.MIN_VALUE ? -1 : max;
    }


    public int maxSumSubmatrix1st(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int[][][][] dp = new int[rows + 1][cols + 1][rows + 1][cols + 1];
        for (int x1 = 1; x1 <= rows; x1++) {
            for (int y1 = 1; y1 <= cols; y1++) {
                dp[x1][y1][x1][y1] = matrix[x1 - 1][y1 - 1];
                for (int x2 = x1; x2 <= rows; x2++) {
                    for (int y2 = y1; y2 <= cols; y2++) {
                        dp[x1][y1][x2][y2] =
                                dp[x1][y1][x2 - 1][y2] + dp[x1][y1][x2][y2 - 1] - dp[x1][y1][x2 - 1][y2 - 1]
                                        + matrix[x2 - 1][y2 - 1];
                        if (dp[x1][y1][x2][y2] <= k) {
                            max = Math.max(max, dp[x1][y1][x2][y2]);
                        }
                    }
                }
            }
        }
        return max == Integer.MIN_VALUE ? -1 : max;
    }

}
