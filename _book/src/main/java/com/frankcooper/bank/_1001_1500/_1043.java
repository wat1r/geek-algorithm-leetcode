package com.frankcooper.bank._1001_1500;

/**
 * @Date 2020/7/28
 * @Author Frank Cooper
 * @Description
 */
public class _1043 {


    static _1043 handler = new _1043();

    public static void main(String[] args) {
        int[] A = {1, 15, 7, 9, 2, 5, 10};
        int K = 3;
        handler.maxSumAfterPartitioning(A,K);

    }


    public int maxSumAfterPartitioning(int[] A, int K) {
        int n = A.length;
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int j = i - 1;
            int max = dp[i];
            while ((i - j) <= K && j >= 0) {
                max = Math.max(max, A[j]);
                dp[i] = Math.max(dp[i], dp[j] + (i - j) * max);
                j--;
            }
        }
        return dp[n];
    }
}
