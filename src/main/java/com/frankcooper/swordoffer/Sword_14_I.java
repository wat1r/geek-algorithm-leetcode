package com.frankcooper.swordoffer;

/**
 * @Date 2020/7/30
 * @Author Frank Cooper
 * @Description
 */
public class Sword_14_I {

    public static void main(String[] args) {

    }

//    int MOD = 1000000007;

    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], j * Math.max(i - j, dp[i - j])) ;
            }
        }
        return dp[n];
    }

}
