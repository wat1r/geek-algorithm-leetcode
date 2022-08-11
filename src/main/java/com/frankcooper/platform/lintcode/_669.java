package com.frankcooper.platform.lintcode;

import java.util.*;

import org.junit.Assert;

public class _669 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] coins = new int[]{1, 2, 5};
            int amount = 11;
            Assert.assertEquals(3, handler.coinChange(coins, amount));

        }

        public int coinChange(int[] coins, int amount) {
            int n = coins.length;
            //f[i]表示凑成钱数为i时，需要的最少的硬币数量
            int[] f = new int[amount + 1];
            f[0] = 0;//凑出钱数为0的硬币的数量，需要0个硬币，即不需要任何硬币
            int INF = Integer.MAX_VALUE;
            for (int i = 1; i <= amount; i++) {
                f[i] = INF;//初始化
                for (int j = 0; j < n; j++) {
                    //数组下标不能为负数
                    //i-coins[j]可以被当前的coins凑成
                    if (i - coins[j] >= 0 && f[i - coins[j]] != INF) {
                        //去min，用掉了当前的硬币，数量+1
                        f[i] = Math.min(f[i], f[i - coins[j]] + 1);
                    }
                }
            }
            //如果是初始化的INF，说明amount不能由当前的硬币凑成
            return f[amount] == INF ? -1 : f[amount];
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
