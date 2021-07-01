package com.frankcooper.bank._601_700;

import org.junit.Assert;

public class _633 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            Assert.assertTrue(handler.judgeSquareSum(5));
//            Assert.assertTrue(handler.judgeSquareSum(4));
//            Assert.assertTrue(handler.judgeSquareSum(2));
            Assert.assertTrue(handler.judgeSquareSum(100));

            //2147483645  101/124
        }


        public boolean judgeSquareSum(int c) {
            long lo = 0, hi = (long) Math.sqrt(c);
            while (lo <= hi) {
                long t = lo * lo + hi * hi;//可以用 c-lo*lo == hi*hi 防止溢出
                if (t == c) return true;
                else if (t > c) hi--;
                else if (t < c) lo++;
            }
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            Assert.assertTrue(handler.judgeSquareSum(100));
            Assert.assertTrue(handler.judgeSquareSum(10000));
        }

        public boolean judgeSquareSum(int c) {
            // n % 2^n = n & 2^n-1s
            if ((c & 3) == 3)
                return false;
            for (int i = 2; i * i <= c; i++) {
                System.out.printf("%d\n", i);
                // 求c的质因子
                if (c % i != 0)
                    continue;
                // 质因子的幂次
                int count = 0;
                while (c % i == 0) {
                    count++;
                    c /= i;
                }
                // 引理2
                if ((i & 3) == 3 && (count & 1) == 1)
                    return false;
            }
            // 此c非彼c
            return (c & 3) != 3;
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
