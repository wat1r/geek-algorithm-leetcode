package com.frankcooper.swordoffer;

/**
 * @Date 2020/7/30
 * @Author Frank Cooper
 * @Description
 */
public class Sword_14_I {

    static class _1st {
        public static void main(String[] args) {

        }

//    int MOD = 1000000007;

        public int cuttingRope(int n) {
            int[] dp = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    dp[i] = Math.max(dp[i], j * Math.max(i - j, dp[i - j]));
                }
            }
            return dp[n];
        }

    }


    static class _2nd {
        public int cuttingRope(int n) {

            int[] f = new int[n + 1];
//            f[0] = f[1] = 0;
            for (int i = 2; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    f[i] = Math.max(f[i], j * Math.max(i - j, f[i - j]));
                }
            }
            return f[n];
        }
    }


}
