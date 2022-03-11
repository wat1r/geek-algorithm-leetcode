package com.frankcooper.models;

public class DPModel {

    /**
     * 区间DP模板
     */
    private void intervalDP() {
        int n = 101;
        int[][] dp = new int[n + 1][n + 1]; //初始化dp数组
        int[][] w = new int[n + 1][n + 1]; //权值
        for (int len = 2; len <= n; len++) {//区间长度
            for (int i = 1; i <= n; i++) { //枚举起点
                int j = i + len - 1; //区间终点
                if (j > n) break; //防止越界
                for (int k = i; k < j; k++) { //枚举分割点，开始转移
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j] + w[i][j]);
                }
            }
        }
    }





}
