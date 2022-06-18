package com.frankcooper.platform.leetcode.bank._401_500;

import org.junit.Assert;

public class _479 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals(987, handler.largestPalindrome(2));

        }


        public int largestPalindrome(int n) {
            if (n == 1) return 9;
            int MOD = 1337;
            int maxx = (int) (Math.pow(10, n) - 1);
            for (int i = maxx; i >= 0; i--) {
                long num = i, t = i;
                while (t > 0) {
                    num = num * 10 + (t % 10);
                    t /= 10;
                }
                for (long j = maxx; j * j >= num; --j) {
                    if (num % j == 0) {
                        return (int) (num % MOD);
                    }
                }
            }
            return -1;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int largestPalindrome(int n) {
            if (n == 1) {
                return 9;
            }
            int upper = (int) Math.pow(10, n) - 1;
            int ans = 0;
            for (int left = upper; ans == 0; --left) { // 枚举回文数的左半部分
                long p = left;
                for (int x = left; x > 0; x /= 10) {
                    p = p * 10 + x % 10; // 翻转左半部分到其自身末尾，构造回文数 p
                }
                for (long x = upper; x * x >= p; --x) {
                    if (p % x == 0) { // x 是 p 的因子
                        ans = (int) (p % 1337);
                        break;
                    }
                }
            }
            return ans;
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
