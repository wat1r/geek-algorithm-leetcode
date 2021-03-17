package com.frankcooper.bank._301_400;

import java.util.HashMap;
import java.util.*;

//322. 零钱兑换 322. Coin Change
//参见 背包思想解决零钱兑换问题.md
public class _322 {

    static _322 handler = new _322();

    public static void main(String[] args) {
//        handler.coinChange1st(new int[]{1, 2, 5}, 11);
        handler.coinChange2nd(new int[]{1, 2, 5}, 11);
    }

    Map<Integer, Integer> memo = new HashMap<>();

    public int coinChange1st(int[] coins, int amount) {
        return helper(coins, amount);
    }

    public int helper(int[] coins, int j) {
        if (memo.containsKey(j)) return memo.get(j);
        if (j == 0) return 0;
        if (j < 0) return -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int tmp = helper(coins, j - coins[i]);
            if (tmp == -1) continue;
            res = Math.min(res, tmp + 1);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        memo.put(j, res);
        return res;
    }


    public int coinChange2nd(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = Integer.MAX_VALUE;
            if (j - coins[0] >= 0 && dp[0][j - coins[0]] != Integer.MAX_VALUE) {
                dp[0][j] = dp[0][j - coins[0]] + 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                int tmp = Integer.MAX_VALUE;
                if (j - coins[i] >= 0 && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
                    tmp = dp[i][j - coins[i]] + 1;
                }
                dp[i][j] = Math.min(tmp, dp[i - 1][j]);
            }
        }
        return dp[n - 1][amount] == Integer.MAX_VALUE ? -1 : dp[n - 1][amount];
    }


    /*
        - `dp[i]`表示凑够总金额为`i`需要的最少的硬币的个数，因为要求得是`dp[amount]`，即凑够总金额为`amount`时所需要的最少硬币的个数，初始化`dp`时要`n+1`;
        - `base case`：当总金额为`0`时，需要的最少的硬币数量，显然为`0` 即不需要硬币即可凑成

        - 在过程中会出现某个总金额`i` 不能被当前的硬币拼凑的情况，设置一个`MAX`值，在`for loop`结束后，如果`dp[i]`还是`MAX`的话，`dp[i]=-1`表示这个金额没有可选的
        - 动态转移方程：`01`背包问题，`for loop`当前的`coin` 选或者不选`coin`:
          - 不选`coin` 结果为`dp[i]`
          - 选`coin`，需要将当前的总金额扣除掉这个`coin` ，也就是`dp[i-coin]`,因为选了这个`coin`，硬币的个数需要`+1`
          - 求`min{dp[i],dp[i-coin]+1}`
          - 需要判断下条件，`i` `i-coin`小于`0`均无意义
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != -1) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
            if (dp[i] == Integer.MAX_VALUE) dp[i] = -1;
        }
        return dp[amount];
    }


    //https://leetcode-cn.com/problems/coin-change/solution/yong-bei-bao-wen-ti-si-xiang-lai-li-jie-ying-bi-zh/


}
