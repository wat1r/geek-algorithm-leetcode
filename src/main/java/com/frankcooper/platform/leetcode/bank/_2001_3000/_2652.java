package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2652 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals(81, handler.sumOfMultiples(15));

        }

        //容斥原理
        public int sumOfMultiples(int n) {
            return s(n, 3) + s(n, 5) + s(n, 7) - s(n, 15) - s(n, 21) - s(n, 35) + s(n, 105);
        }

        private int s(int n, int m) {
            int k = n / m;
            return k * (k + 1) / 2 * m;
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
