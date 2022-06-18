package com.frankcooper.platform.leetcode.bank._1501_2000;

import com.frankcooper.utils.PrintUtils;

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


    public int stoneGameVII2nd(int[] stones) {
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


    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        Integer[][] f = new Integer[n][n];
        int sum = 0;
        for (int i = 0; i < n; i++) sum += stones[i];
        return dfs(stones, f, 0, n - 1, sum);
    }

    private int dfs(int[] stones, Integer[][] f, int i, int j, int sum) {
        //出口，当只有一个石子后，选完就没了
        if (i == j) return 0;
        //如果已经搜过了，不需要再次搜索
        if (f[i][j] != null) return f[i][j];
        //左右两侧，取左边，取右边，拿最大值
        int left = sum - stones[i] - dfs(stones, f, i + 1, j, sum - stones[i]);
        int right = sum - stones[j] - dfs(stones, f, i, j - 1, sum - stones[j]);
        return f[i][j] = Math.max(left, right);
    }

}
