package com.frankcooper.bank._501_1000;

import com.frankcooper.utils.PrintUtils;

public class _879 {

    static _879 handler = new _879();

    static int m = (int) (1e9 + 7);

    public static void main(String[] args) {
        int G = 5;
        int P = 3;
        int[] group = new int[]{2, 2};
        int[] profit = new int[]{2, 3};

        handler.profitableSchemes(G, P, group, profit);
        handler.profitableSchemes1st(G, P, group, profit);


        System.out.println(m);


    }


    int MOD = 1_000_000_007;


    /**
     * @param G      n n名员工
     * @param P      minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int N = profit.length;
        int[][][] dp = new int[N + 1][G + 1][P + 1];
        //初始化
        dp[0][0][0] = 1;
        //三层dp循环
        for (int i = 1; i <= N; ++i) {
            for (int j = 0; j <= G; ++j) {
                for (int k = 0; k <= P; ++k) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= group[i - 1]) {
                        //nk 表示new k 小于0 没有意义
                        int nk = Math.max(k - profit[i - 1], 0);
                        dp[i][j][k] += dp[i - 1][j - group[i - 1]][nk];
                    }
                    dp[i][j][k] %= MOD;
                }
            }
        }
        PrintUtils.printMatrix(dp);
        //最终返回
        int sum = 0;
        for (int j = 0; j <= G; j++) {
            System.out.printf("i:%d,j:%d,k:%d\n", N, j, P);
            sum = (sum + dp[N][j][P]) % MOD;
        }
        return sum;
    }


    public int profitableSchemes3rd(int G, int P, int[] group, int[] profit) {

        int N = profit.length;
        int[][] dp = new int[G + 1][P + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= N; ++i) {
            int g = group[i - 1];
            int p = profit[i - 1];
            for (int j = G; j >= g; j--) {
                for (int k = P; k >= 0; k--) {
                    int nk = Math.max(k - p, 0);
                    dp[j][k] = (dp[j][k] + dp[j - g][nk]) % MOD;
//                    PrintUtils.printMatrix(dp);
                }
            }
        }
//        PrintUtils.printMatrix(dp);
        int sum = 0;
        for (int i = 0; i <= G; i++) {
            sum = (sum + dp[i][P]) % MOD;
        }
        return sum;
//        return dp[G][P];
    }

    /**
     * @param G      n n名员工
     * @param P      minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes1st(int G, int P, int[] group, int[] profit) {
        int N = profit.length;
        int[][][] dp = new int[N + 1][G + 1][P + 1];
        //初始化
        for (int i = 0; i <= N; ++i) {
            for (int j = 0; j <= G; ++j) {
                dp[i][j][0] = 1;
            }
        }
        //三层dp循环
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= G; ++j) {
                for (int k = 0; k <= P; ++k) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= group[i - 1]) {
                        //nk 表示new k 小于0 没有意义
                        int nk = Math.max(k - profit[i - 1], 0);
                        dp[i][j][k] += dp[i - 1][j - group[i - 1]][nk];
                    }
                    dp[i][j][k] %= MOD;
                }
            }
        }
        PrintUtils.printMatrix(dp);
        //最终返回
        return dp[N][G][P];
    }


}
