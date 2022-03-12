package com.frankcooper.bank._301_400;

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
}
