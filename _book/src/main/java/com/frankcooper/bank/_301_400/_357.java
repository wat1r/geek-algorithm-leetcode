package com.frankcooper.bank._301_400;

import org.junit.Assert;

/**
 * @Date 2020/9/12
 * @Author Frank Cooper
 * @Description
 */
public class _357 {


    static class _1st {


        public static void main(String[] args) {
            _1st handler = new _1st();
            int n = 2;
            Assert.assertEquals(91, handler.countNumbersWithUniqueDigits(n));
        }


        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) return 1;
            int first = 10, second = 9 * 9;
            int size = Math.min(n, 10);
            for (int i = 2; i <= size; i++) {
                first += second;
                second *= 10 - i;
            }
            return first;
        }


    }

    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int n = 3;
            Assert.assertEquals(739, handler.countNumbersWithUniqueDigits(n));
        }


        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) return 1;
            int ans = 10;
            for (int i = 2, last = 9; i <= n; i++) {
                int cur = last * (10 - i + 1);
                ans += cur;
                last = cur;
            }
            return ans;
        }

    }

    static class _3rd {
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) return 1;
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 10;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + (dp[i - 1] - dp[i - 2]) * (10 - (i - 1));
            }
            return dp[n];
        }
    }

    static class _4th {
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) return 1;
            if (n == 1) return 10;
            int prev = 10;
            for (int i = 9, curr = 9; --n > 0; prev += curr *= i--) ;
            return prev;
        }
    }
}
