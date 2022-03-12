package com.frankcooper.bank._1001_1500;

import java.util.HashMap;
import java.util.Map;

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


    static class _1st {


        public static void main(String[] args) {

        }

//        Integer[][] cache = new Integer[35][3005];

        HashMap<Map<Integer, Integer>, Integer> cache = new HashMap<>();

        public int lastStoneWeightII(int[] stones) {
            dfs(stones, 0, 0);
            Map<Integer, Integer> key = new HashMap<Integer, Integer>() {{
                put(0, 0);
            }};
            return cache.get(key);

        }


        private int dfs(int[] stones, int idx, int sum) {
            //当idx到达数组末尾
            Map<Integer, Integer> key = new HashMap<Integer, Integer>() {{
                put(idx, sum);
            }};
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            if (idx == stones.length) {
                return sum >= 0 ? sum : Integer.MAX_VALUE;
            }
            int positive = dfs(stones, idx + 1, sum + stones[idx]);
            int negative = dfs(stones, idx + 1, sum - stones[idx]);
            int value = Math.min(positive, negative);
            cache.put(key, value);
            return value;
        }

    }


}
