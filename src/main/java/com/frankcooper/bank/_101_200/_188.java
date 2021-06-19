package com.frankcooper.bank._101_200;

import java.util.*;

import org.junit.Assert;

public class _188 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int maxProfit(int k, int[] prices) {
            int n = prices.length;
            int[][][] f = new int[n + 1][k + 1][2];
            for (int j = 0; j <= k; j++) f[0][j][1] = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    f[i][j][0] = Math.max(f[i - 1][j][0], f[i - 1][j][1] + prices[i - 1]);
                    f[i][j][1] = Math.max(f[i - 1][j][1], f[i - 1][j - 1][0] - prices[i - 1]);
                }
            }
            return f[n][k][0];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int maxProfit(int k, int[] prices) {
            int n = prices.length;
            int[][] f = new int[k + 1][2];
            for (int j = 0; j <= k; j++) f[j][1] = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    f[j][0] = Math.max(f[j][0], f[j][1] + prices[i - 1]);
                    f[j][1] = Math.max(f[j][1], f[j - 1][0] - prices[i - 1]);
                }
            }
            return f[k][0];
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
