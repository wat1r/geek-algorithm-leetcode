package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/7/29
 * @Author Frank Cooper
 * @Description
 */
public class _96 {

    public static void main(String[] args) {

    }

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }


    static class _1st {

        public int numTrees(int n) {
            return dfs(n);
        }
        private int dfs(int n) {
            if (n == 0 || n == 1) return 1;
            int cnt = 0;
            for (int i = 0; i <= n - 1; i++) {
                cnt += dfs(i) * dfs(n - 1 - i);
            }
            return cnt;
        }

    }


    static class _2nd {

        Map<Integer, Integer> cache = new HashMap<>();

        public int numTrees(int n) {
            return dfs(n);
        }


        private int dfs(int n) {
            if (cache.containsKey(n)) return cache.get(n);
            if (n == 0 || n == 1) return 1;
            int cnt = 0;
            for (int i = 0; i <= n - 1; i++) {
                cnt += dfs(i) * dfs(n - 1 - i);
            }
            cache.put(n,cnt);
            return cnt;
        }
    }
}
