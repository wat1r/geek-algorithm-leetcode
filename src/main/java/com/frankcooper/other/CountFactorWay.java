package com.frankcooper.other;

public class CountFactorWay {

    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            System.out.println(handler.countWays(7));
        }

        public int countWays(int n) {
            if (n == 0) return 1;
            if (n == 1) return 1;
            if (n == 2) return 1;
            if (n == 3) return 2;
            int s1 = countWays(n - 1);
            int s3 = countWays(n - 3);
            int s4 = countWays(n - 4);
            return s1 + s3 + s4;
        }
    }


    static class _2nd {

        Integer[] memo;

        public int countWays(int n) {
            memo = new Integer[n + 1];
            return helper(n);

        }

        private int helper(int n) {
            if (memo[n] != null) return memo[n];
            if (n == 0) return 1;
            if (n == 1) return 1;
            if (n == 2) return 1;
            if (n == 3) return 2;
            int s1 = countWays(n - 1);
            int s3 = countWays(n - 3);
            int s4 = countWays(n - 4);
            return memo[n] = s1 + s3 + s4;
        }
    }

    static class _3rd {
        public int countWays(int n) {
            int[] f = new int[n + 1];
            f[0] = f[1] = f[2] = 1;
            f[3] = 2;
            for (int i = 4; i <= n; i++) {
                f[i] = f[i - 1] + f[i - 3] + f[i - 4];
            }
            return f[n];
        }
    }
}
