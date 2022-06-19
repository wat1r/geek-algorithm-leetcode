package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/7/30
 * @Author Frank Cooper
 * @Description
 */
public class _343 {
    static _343 handler = new _343();

    public static void main(String[] args) {
//        handler.integerBreak(2);
        handler.integerBreak(10);
    }


    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            System.out.printf("%d\n", i);
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], j * Math.max(i - j, dp[i - j]));
                System.out.printf("  %d\n", j);
            }
        }
        return dp[n];
    }


    static class _1st {
        public int integerBreak(int n) {
            if (n <= 3) return n - 1;
            int a = n / 3, b = n % 3;
            if (b == 0) return (int) Math.pow(3, a);
            if (b == 1) return (int) (Math.pow(3, a - 1) * 2 * 2);
            return (int) (Math.pow(3, a) * 2);
        }
    }


    static class _2nd {
        public static void main(String[] args) {

        }

        public int integerBreak(int n) {
            if (n <= 3) return n - 1;
            int a = n / 3, b = n % 3;
            if (b == 0) return (int) Math.pow(3, a);
            if (b == 1) return (int) Math.pow(3, a - 1) * 4;
            return (int) Math.pow(3, a) * 2;
        }

    }

    static class _3rd {
        public int integerBreak(int n) {
            if (n <= 3) return n - 1;
            int a = n / 3, b = n % 3;
            if (b == 0) return (int) Math.pow(3, a);
            if (b == 1) return (int) Math.pow(3, a - 1) * 2 * 2;
            return (int) Math.pow(3, a) * 2;
        }
    }

    static class _4th {
        public int integerBreak(int n) {
            if (n < 2) return n;
            return dfs(n);
        }

        private int dfs(int n) {
            //n= 2 只能拆成 1+1
            if (n == 2) return 1;
            int res = 0;
            //当前摘取 i 剩下的是 n -i
            //case1：i 和 n-i 形成两段，算乘积， 不拆n-i
            //case2: i 和 n-i拆过的最大值 算乘积 拆 n-i
            for (int i = 1; i < n; i++) {
                res = Math.max(res, Math.max(i * (n - i), i * dfs(n - i)));
            }
            return res;
        }
    }

    static class _4th_1 {

        Map<Integer, Integer> memo = new HashMap<>();

        public int integerBreak(int n) {
            if (n < 2) return n;
            return dfs(n);
        }

        private int dfs(int n) {
            if (memo.containsKey(n)) return memo.get(n);
            //n= 2 只能拆成 1+1
            if (n == 2) return 1;
            int res = 0;
            //当前摘取 i 剩下的是 n -i
            //case1：i 和 n-i 形成两段，算乘积， 不拆n-i
            //case2: i 和 n-i拆过的最大值 算乘积 拆 n-i
            for (int i = 1; i < n; i++) {
                res = Math.max(res, Math.max(i * (n - i), i * dfs(n - i)));
            }
            memo.put(n, res);
            return res;
        }
    }

    static class _5th {
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                //从 1枚举到 j 至少留 i-j >=1
                for (int j = 1; j < i; j++) {
                    //选了j 可以对i-j这部分做两个选择：
                    //选择1： 不拆分 i-j 得到的结果是 j * (i - j)
                    //选择2： 拆分 i-j,要拿拆分的 [i-j]这部分的最大值 j * dp[i - j]
                    dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
                }
            }
            return dp[n];
        }
    }
}
