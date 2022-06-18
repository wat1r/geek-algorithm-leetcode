package com.frankcooper.platform.leetcode.bank._1501_2000;

public class _1572 {
    public int diagonalSum(int[][] mat) {
        int N = mat.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) sum += mat[i][i];
                else if (i + j == N - 1) sum += mat[i][j];
            }
        }
        return sum;
    }
}
