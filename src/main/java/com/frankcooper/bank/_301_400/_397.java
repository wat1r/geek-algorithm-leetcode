package com.frankcooper.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _397 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals(3, handler.integerReplacement(8));
            Assert.assertEquals(2, handler.integerReplacement(4));
            Assert.assertEquals(4, handler.integerReplacement(7));
        }

        public int integerReplacement(int n) {
            return (int) dfs(n);
        }

        public long dfs(long n) {
            if (n == 1) return 0;
            if (n % 2 == 0) {
                n /= 2;
                return dfs(n) + 1;
            } else {
                return Math.min(dfs(n + 1), dfs(n - 1)) + 1;
            }
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
