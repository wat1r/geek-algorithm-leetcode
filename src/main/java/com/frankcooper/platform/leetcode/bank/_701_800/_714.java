package com.frankcooper.platform.leetcode.bank._701_800;

//714. 买卖股票的最佳时机含手续费 714. Best Time to Buy and Sell Stock with Transaction Fee
//Medium
public class _714 {
    /*
        ##### 方法2:空间压缩版DP
        ```java
        dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i]-fee)
        dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
        或者
        dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
        dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i]-fee)
        ```
    - `fee`的话相当于在卖入`buy`的时候算负债，或者在卖出`sell`的时候扣除得到的利润
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i] - fee);
        }
        return dp_i_0;
    }


    static class _1st {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            //f[i][0]表示第i天手里没有股票，持有的总收益
            //f[i][1]表示第i天手里有股票，持有的总收益
            //规定买入（buy）的时候利润为负 即-prices[i]
            //规定卖出（sell）的时候利润为正，即+prices[i]
            //卖出（sell）的时候需要扣除手续费，为-fee
            int[][] f = new int[n][2];
            f[0][0] = 0;
            f[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i] - fee);
                f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i]);
            }
            return f[n - 1][0];
        }
    }

    static class _1st_1 {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            //f[0] 是当前无股票状态时 持有的总收益
            //f[1] 是当前有股票状态时 持有的总收益
            int[] f = new int[2];
            f[0] = 0;
            f[1] = -prices[0];
            for (int i = 1; i < n; i++) {
                f[0] = Math.max(f[0], f[1] + prices[i] - fee);
                f[1] = Math.max(f[1], f[0] - prices[i]);
            }
            return f[0];
        }
    }

    static class _2nd {
        //基于上一种方法，fee`的话相当于在卖入`buy`的时候算负债，或者在卖出`sell`的时候扣除得到的利润
        //当前方法是在买入（buy）的时候算负债-fee,注意在初始化f[0][1]时的处理，
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            //f[i][0]表示第i天手里没有股票，持有的总收益
            //f[i][1]表示第i天手里有股票，持有的总收益
            //规定买入（buy）的时候利润为负 即-prices[i]
            //规定卖出（sell）的时候利润为正，即+prices[i]
            //卖出（sell）的时候需要扣除手续费，为-fee
            int[][] f = new int[n][2];
            f[0][0] = 0;
            f[0][1] = -prices[0] - fee;//买入(buy),股票的价值为prices[i]，需要负债手续费-fee
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
                f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i] - fee);//买入的时候算负债和手续费
            }
            return f[n - 1][0];
        }
    }

    static class _1st_2 {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            //f[0] 是当前无股票状态时 持有的总收益
            //f[1] 是当前有股票状态时 持有的总收益
            int f_i_0 = 0, f_i_1 = -prices[0];
            //从 0 开始 1 开始都可以
            for (int i = 0; i < n; i++) {
                //暂存下变量值，在紧接着这个值会给覆盖掉
                int t = f_i_0;
                f_i_0 = Math.max(f_i_0, f_i_1 + prices[i] - fee);
                f_i_1 = Math.max(f_i_1, t - prices[i]);
            }
            return f_i_0;
        }
    }
}
