package com.frankcooper.platform.oj_set.poj;

import com.frankcooper.utils.PrintUtils;

import java.util.Arrays;
import java.util.Scanner;

/**
 * http://poj.org/problem?id=3311
 * https://zhuanlan.zhihu.com/p/67719012
 * https://www.cnblogs.com/real-l/p/8589562.html
 */
public class POJ3311 {

    static POJ3311 handler = new POJ3311();

    int n;

    public static void main(String[] args) {
        handler.process();
    }

    int[][] edges;
    int[][] dp;

    private void process() {
        autoProcess();

    }

    Integer INF = Integer.MAX_VALUE;

    private void autoProcess() {
        n = 3;
        n++;
        edges = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 10, 10},
                {0, 1, 0, 1, 2},
                {0, 10, 1, 0, 10},
                {0, 10, 2, 10, 0}};
//        dist = new int[n + 1][n + 1];
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= n; j++) {
//                dist[i][j] = edges[i][j];
//            }
//        }
        floyd();
        dp = new int[n + 1][1 << n];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        PrintUtils.printMatrix(dp, 14);
        dp[1][1] = 0;
        for (int state = 1; state < (1 << n); state++) {
            for (int i = 1; i <= n; i++) {
                if ((state & (1 << (i - 1))) != 0) {
                    for (int j = 1; j <= n; j++) {
                        if ((state & (1 << (j - 1))) == 0) {
                            dp[j][state | (1 << (j - 1))] = Math.min(dp[j][state | (1 << (j - 1))], dp[i][state] + edges[i][j]);
                        }
                    }
                }
            }
        }
        PrintUtils.printMatrix(dp, 14);
        int res = 0;
        for (int i = 2; i <= n; i++) {
            if (dp[i][(1 << n) - 1] >= 0 && dp[i][(1 << n) - 1] <= 10) {
                res = Math.min(res, dp[i][(1 << n) - 1] + edges[i][1]);
            }
        }
        System.out.println("res");

    }

    private void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    edges[i][j] = Math.min(edges[i][j], edges[i][k] + edges[k][j]);
                }
            }
        }
        PrintUtils.printMatrix(edges);
    }


    private void manualProcess() {
        while (true) {
            Scanner in = new Scanner(System.in);
            this.n = in.nextInt();
            n++;
            this.edges = new int[n][n];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    edges[i][j] = in.nextInt();
                }
            }
        }
    }


}
