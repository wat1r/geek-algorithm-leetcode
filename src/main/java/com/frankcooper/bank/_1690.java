package com.frankcooper.bank;

import com.frankcooper.swordoffer.utils.PrintUtils;

public class _1690 {


    static _1690 handler = new _1690();

    public static void main(String[] args) {
        handler.stoneGameVII(new int[]{5, 3, 1, 4, 2});
    }


    public int stoneGameVII1st(int[] stones) {
        int n = stones.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + stones[i];
        int[][] f = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                System.out.printf("i:%d,j:%d\n", i, j);
                f[i][j] = Math.max(
                        sum[j + 1] - sum[i + 1] - f[i + 1][j],
                        sum[j] - sum[i] - f[i][j - 1]
                );
            }
        }
        PrintUtils.printMatrix(f);
        return f[0][n - 1];
    }


    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[][] f = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                System.out.printf("i:%d,j:%d\n", i, j);
                if (i + 1 == j) {
                    f[i][j] = Math.max(stones[i], stones[j]);
                } else {
                    int left = Math.min(stones[i + 1] + f[i + 2][j], stones[j] + f[i + 1][j - 1]);
                    int right = Math.min(stones[i] + f[i + 1][j - 1], stones[j - 1] + f[i][j - 2]);
                    f[i][j] = Math.max(left, right);
                }
            }
        }
//        PrintUtils.printMatrix(f);
        return f[0][n - 1];
    }

}
