package com.frankcooper.bank;

import java.util.List;

/**
 * @Date 2020/7/14
 * @Author Frank Cooper
 * @Description
 */
public class _120 {

    public static void main(String[] args) {

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
