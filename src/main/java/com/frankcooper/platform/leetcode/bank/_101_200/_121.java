package com.frankcooper.platform.leetcode.bank._101_200;
//121. 买卖股票的最佳时机 121. Best Time to Buy and Sell Stock Easy

import org.junit.Assert;

public class _121 {
    public static void main(String[] args) {

    }


    class _1st {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            int[][] dp = new int[n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            }
            return dp[n - 1][0];
        }

    }



    /*
       ##### 方法1：基础版DP

        - `dp[i][0|1]`
          - `i`表示第`i`天
          - `0`表示第`i`天没有股票的状态[无股票]
          - `1`表示第`i`天持有股票的状态[有股票]
        - 规定卖出的时候(`sell`)获利，`+prices[i]`,买入的时候，暂时算负债(`buy`)，`-prices[i]`
        - `dp[i][0]`表示第`i`天无股票，可能是昨天也没有股票，状态持续到今天，为`dp[i-1][0]`，也可能是昨天持有股票，但是卖出了(`sell`)，为`dp[i-1][1]+prices[i]`
          - 这时为  `dp[i][0]` = max{`dp[i-1][0]`,`dp[i-1][1]+prices[i]`}
        - `dp[i][1]`表示第`i`天有股票，可能是昨天就有股票，状态持续到今天，为`dp[i-1][1]`,也可能是昨天没有持有股票，但是买入了(`buy`), 为`dp[i-1][0]-prices[i]` ,因为只有一次买入卖出的机会，
          `dp[i-1][0]`表示`i-1`天的时候，是无股票的，在`i`天的时候买入了股票，也就是说要必须在`i`天之后的某一天卖出股票，所以很显然`dp[i-1][0]=0`
          - 这时为`dp[i][1]` = max{`dp[i-1][1]`,`-prices[i]`}
     */

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[0];//第0天持有股票，证明是买入了，buy的情况下算负债
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];//最后一天，手里没有股票的状态，相当于在最后一天的前（包含最后一天），完成了一次买入和一次卖出
    }

    /*
        ##### 方法2:空间压缩DP
     */
    public int maxProfit2nd(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            //dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            //dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }


    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] prices = new int[]{7, 1, 5, 3, 6, 4};
            Assert.assertEquals(5, handler.maxProfit(prices));

        }


        /**
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int n = prices.length;
            //f[i][0]表示第i天手里没有股票获得的最大利润
            //f[i][0]表示第i天手里有股票获得的最大利润
            int[][] f = new int[n][2];
            f[0][0] = 0;
            f[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
                f[i][1] = Math.max(f[i - 1][1], -prices[i]);
            }
            return f[n - 1][0];
        }
    }

    static class _2nd_1 {
        public int maxProfit(int[] prices) {

            //f[i][0]表示第i天手里没有股票获得的最大利润
            //f[i][1]表示第i天手里有股票获得的最大利润

            int n = prices.length;
            int[][] f = new int[n][2];
            f[0][0] = 0;
            f[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
                f[i][1] = Math.max(f[i - 1][1], -prices[i]);
//                System.out.printf("i：%d, %d,%d\n", i, f[i][0], f[i][1]);
            }
            return f[n - 1][0];
        }
    }

    static class _2nd_2 {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] f = new int[n][2];
            f[0][0] = 0;
            f[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
                f[i][1] = Math.max(f[i - 1][1], -prices[i]);
            }
            return f[n - 1][0];

        }
    }


    static class _2nd_3 {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[] f = new int[2];
            f[0] = 0;//无股票状态
            f[1] = -prices[0];//有股票状态
            for (int i = 1; i < n; i++) {
                f[0] = Math.max(f[0], f[1] + prices[i]);
                f[1] = Math.max(f[1], -prices[i]);
            }
            return f[0];
        }
    }
}
