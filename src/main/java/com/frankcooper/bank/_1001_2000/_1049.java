package com.frankcooper.bank._1001_2000;

/**
 * @Date 2020/9/16
 * @Author Frank Cooper
 * @Description
 */
public class _1049 {

    static _1049 handler = new _1049();

    public static void main(String[] args) {

        int[] stones = {2, 7, 4, 1, 8, 1};
//        handler.lastStoneWeightII(stones);

    }


    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) sum += stone;
        int m = sum / 2;
        int[] dp = new int[m + 1];
        for (int i = 0; i < n; ++i) {
            int currStone = stones[i];
            for (int j = m; j >= currStone; j--) {
                dp[j] = Math.max(dp[j], dp[j - currStone] + currStone);
            }
        }
        int res = sum - 2 * dp[m];
        return res;
    }


    public int lastStoneWeightII1st(int[] stones) {

        int n = stones.length;
        int sum = 0;
        for (int stone : stones) sum += stone;
        int[][] dp = new int[n + 1][sum / 2 + 1];
        for (int i = 1; i <= n; ++i) {
            int currStone = stones[i - 1];
            for (int j = 1; j <= sum / 2; ++j) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                System.out.printf("i:%d,j:%d,dp:%d\n", i, j, dp[i][j]);

                if (j >= currStone) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - currStone] + currStone);
                    System.out.printf("i:%d,j:%d,dp:%d,currStone:%d\n", i, j, dp[i][j], currStone);
                }
            }
        }
        int res = sum - 2 * dp[n][sum / 2];
        return res;
    }


}
