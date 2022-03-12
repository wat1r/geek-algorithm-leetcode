package com.frankcooper.bank._101_200;

import java.util.List;

/**
 * @Date 2020/7/14
 * @Author Frank Cooper
 * @Description
 */
public class _120 {

    public static void main(String[] args) {

    }


    public int minimumTotal3rd(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int n = triangle.size();
        int[] dp = new int[n];
        for (int j = 0; j <=n - 1; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }


    public int minimumTotal2nd(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int j = 0; j <= n - 1; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }


    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

}
