package com.frankcooper.bank._301_400;

import com.frankcooper.utils.PrintUtils;

public class _304 {

    static class _1st {
        class NumMatrix {

            int[][] s;

            public NumMatrix(int[][] matrix) {
                if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
                int m = matrix.length, n = matrix[0].length;
                s = new int[m][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        s[i][j + 1] = s[i][j] + matrix[i][j];
                    }
                }
            }

            public int sumRegion(int row1, int col1, int row2, int col2) {
                int ans = 0;
                for (int i = row1; i <= row2; i++) {
                    ans += s[i][col2 + 1] - s[i][col1];
                }
                return ans;
            }
        }
    }


    static class _2nd {
        static class NumMatrix {

            public static void main(String[] args) {
                int[][] matrix = PrintUtils.processSymbol("[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]");
                NumMatrix handler = new NumMatrix(matrix);

            }

            int[][] s;

            public NumMatrix(int[][] matrix) {
                if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
                int m = matrix.length, n = matrix[0].length;
                s = new int[m + 1][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + matrix[i][j];
                    }
                }
                PrintUtils.printMatrix(s);
            }

            public int sumRegion(int r1, int c1, int r2, int c2) {
                return s[r2 + 1][c2 + 1] - s[r1 + 1][c2] - s[r2][c1 + 1] + s[r1][c1];
            }
        }
    }
}
