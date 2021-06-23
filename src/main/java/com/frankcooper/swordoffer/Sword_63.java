package com.frankcooper.swordoffer;

import java.util.*;

import org.junit.Assert;

public class Sword_63 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            int[][] f = new int[n][2];//f[i][0]第i天手里没股票，f[i][1]第i天手里有股票
            f[0][0] = 0;
            f[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
                f[i][1] = Math.max(f[i - 1][1], -prices[i]);
            }
            return f[n - 1][0];

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            int[] f = new int[2];//f[i][0]第i天手里没股票，f[i][1]第i天手里有股票
            f[0] = 0;
            f[1] = -prices[0];
            for (int i = 1; i < n; i++) {
                f[0] = Math.max(f[0], f[1] + prices[i]);
                f[1] = Math.max(f[1], -prices[i]);
            }
            return f[0];

        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            int a = 0, b = -prices[0];
            for (int i = 1; i < n; i++) {
                a = Math.max(a, b + prices[i]);
                b = Math.max(b, -prices[i]);
            }
            return a;

        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
