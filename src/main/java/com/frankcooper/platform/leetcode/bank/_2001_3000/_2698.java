package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2698 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals(182, handler.punishmentNumber(10));

        }

        static final int N = 1000;


        public int punishmentNumber(int n) {
            int[] f = new int[N + 1];
            if (n == 1) {
                return 1;
            }
            f[1] = 1;
            f[n] = punishmentNumber(n - 1) + (valid(n * n, n) ? n * n : 0);
            return f[n];
        }

        /**
         * @param x i*i
         * @param y i
         * @return
         */
        private boolean valid(int x, int y) {
            if (x < y) {
                return false;
            }
            if (x == y) {
                return true;
            }
            for (int b = 10; b <= x; b *= 10) {
                if (valid(x / b, y - x % b)) {
                    return true;
                }
            }
            return false;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            Assert.assertEquals(182, handler.punishmentNumber(10));
        }

        private static final int[] PRE_SUM = new int[1001];

        static {
            for (int i = 1; i <= 1000; i++) {
                char[] s = Integer.toString(i * i).toCharArray();
                PRE_SUM[i] = PRE_SUM[i - 1] + (dfs(s, i, 0, 0) ? i * i : 0);
            }
        }

        /**
         * @param s   i*i的char数组
         * @param i   当前要计算的i
         * @param p   当前计算到的char数组的位置
         * @param sum 当前的和
         * @return
         */
        private static boolean dfs(char[] s, int i, int p, int sum) {
            if (p == s.length) { // 递归终点
                return sum == i; // i 符合要求
            }
            int x = 0;
            for (int j = p; j < s.length; j++) { // 枚举分割出从 s[p] 到 s[j] 的子串
                x = x * 10 + s[j] - '0'; // 子串对应的整数值
                if (dfs(s, i, j + 1, sum + x)) {
                    return true;
                }
            }
            return false;
        }

        public int punishmentNumber(int n) {
            return PRE_SUM[n];
        }

//        作者：灵茶山艾府
//        链接：https://leetcode.cn/problems/find-the-punishment-number-of-an-integer/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
