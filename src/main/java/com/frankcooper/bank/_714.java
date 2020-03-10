package com.frankcooper.bank;

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
}
