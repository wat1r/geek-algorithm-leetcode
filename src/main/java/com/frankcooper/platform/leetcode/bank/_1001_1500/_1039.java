package com.frankcooper.platform.leetcode.bank._1001_1500;

import com.frankcooper.utils.PrintUtils;

public class _1039 {


    static _1039 handler = new _1039();

    public static void main(String[] args) {
//        handler.minScoreTriangulation(new int[]{1, 2, 3});
        handler.minScoreTriangulation(new int[]{1, 3, 1, 4, 1, 5});
    }


    public int minScoreTriangulation1st(int[] A) {
        int INF = Integer.MAX_VALUE >> 1;
        if (A == null || A.length == 0) return 0;
        int n = A.length; //
        int[][] dp = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n; i++) {
                int j = i + len; //当i=0,len=2时，即[0,2] 三角形必须要三个点才能形成
                if (j >= n) break; //
                dp[i][j] = INF; //要找最小值，默认是0，设置为INF
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[k] * A[j]);
                }
            }
//            PrintUtils.printMatrix(dp);
        }
        return dp[0][n - 1]; //返回的是A[0...n-1]这个区域的剖分后的最小值
    }


    public int minScoreTriangulation(int[] A) {
        int INF = Integer.MAX_VALUE >> 1;
        if (A == null || A.length == 0) return 0;
        int n = A.length; //
        int[][] dp = new int[n][n];
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n; i++) {
                int j = len + i - 1;
                if (j >= n) break;
                dp[i][j] = INF;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[k] * A[j]);
                }
            }
            PrintUtils.printMatrix(dp);
        }
        return dp[0][n - 1];
    }


}
