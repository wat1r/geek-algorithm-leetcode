package com.frankcooper.platform.leetcode.bank._801_900;

public class _867 {

    static class _1st {
        public int[][] transpose(int[][] matrix) {
            int R = matrix.length, C = matrix[0].length;
            int[][] res = new int[C][R];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    res[j][i] = matrix[i][j];
                }
            }
            return res;
        }
    }
}
