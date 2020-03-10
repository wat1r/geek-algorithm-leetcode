package com.frankcooper.bank;

public class _122 {
    public static void main(String[] args) {

    }


    /*

        ##### 方法2:空间压缩DP
        - 和`121`题基本一致，因为交易次数`k`无次数限制,`infinity`

             ```java
          int tmp = dp_i_0;
          dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
          dp_i_1 = Math.max(dp_i_1, tmp - prices[i]);
             ```
          - `dp_i_0` 是昨天无股票`dp_i_0` 或者是昨天持有股票，今天卖出了`sell`,卖出相当于盈利为`dp_i_1+prices(i)`
            - 这时为 `dp_i_0 = max(dp_i_0, dp_i_1 + prices[i])`
          - `dp_i_1`是昨天持有股票`dp_i_1`,或者是昨天无股票状态，今天买入了股票`buy`，买入相当于负债，但是需要提前记录下`tmp =dp_i_0`,因为上面的转移方程已经改变了`dp_i_0`的值
            - 这时为`dp_i_1 = max(dp_i_1, tmp - prices[i])`
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i]);
        }
        return dp_i_0;
    }
}
