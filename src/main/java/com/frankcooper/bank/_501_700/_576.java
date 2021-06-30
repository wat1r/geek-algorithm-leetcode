package com.frankcooper.bank._501_700;

import java.util.Arrays;

/**
 * Created by FrankCooper
 * Date 2020/9/19 9:41
 * Description
 */
public class _576 {


    static _576 handler = new _576();

    static long s, e;

    public static void main(String[] args) {
       /* int m = 2;
        int n = 2;
        int N = 2;
        int i = 0;
        int j = 0;*/
 /*       int m = 8;
        int n = 7;
        int N = 16;
        int i = 1;
        int j = 5;*/
        int m = 30;
        int n = 24;
        int N = 23;
        int i = 26;
        int j = 12;
        s = System.currentTimeMillis();
        handler.findPaths(m, n, N, i, j);
        e = System.currentTimeMillis();
        System.out.println(e - s);
    }


    int m, n;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int N;
    int res = 0;
    int[][][] memo;

    int MOD = 1_000_000_007;


    public int findPaths(int m, int n, int N, int i, int j) {
        this.m = m;
        this.n = n;
        this.N = N;
        memo = new int[m][n][N + 1];
        for (int o = 0; o < m; ++o) {
            for (int p = 0; p < n; ++p) {
                Arrays.fill(memo[o][p], -1);
            }
        }
        res += dfs(i, j, 0);
        return res;
    }

    private int dfs(int i, int j, int steps) {
        if (steps > N) {
            return 0;
        }
        if (!inArea(i, j)) {
            return 1;
        }
        if (memo[i][j][steps] != -1) {
            return memo[i][j][steps];
        }
        int tmp = 0;
        for (int[] dir : directions) {
            int nextI = i + dir[0], nextJ = j + dir[1];
            System.out.printf("nextI:%d,nextJ:%d\n", nextI, nextJ);
            tmp += dfs(nextI, nextJ, steps + 1);
            tmp %= MOD;

        }
        memo[i][j][steps] = tmp;
        return memo[i][j][steps];
    }


    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }


}
