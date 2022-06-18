package com.frankcooper.platform.leetcode.bank._701_800;

public class _766 {


    static class _1st {
        public boolean isToeplitzMatrix(int[][] matrix) {
            if (matrix == null || matrix[0].length == 0) return false;
            int R = matrix.length, C = matrix[0].length;
            for (int i = 0; i < R - 1; ++i) {
                for (int j = 0; j < C - 1; j++) {
                    if (matrix[i][j] != matrix[i + 1][j + 1]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }




}
