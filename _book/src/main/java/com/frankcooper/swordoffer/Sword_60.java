package com.frankcooper.swordoffer;

import java.util.*;

import org.junit.Assert;

public class Sword_60 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.dicesProbability(2);

        }


        public double[] dicesProbability(int n) {

            int[][] f = new int[n + 1][6 * n + 1];
            for (int j = 1; j <= 6; j++) {
                f[1][j] = 1;
            }
            for (int i = 2; i <= n; i++) {
                for (int j = i; j <= 6 * i; j++) {
                    for (int k = 1; k <= 6; k++) {
                        if (j - k <= 0) break;
                        f[i][j] += f[i - 1][j - k];
                    }

                }
            }
            int total = (int) Math.pow(6, n);
            double[] res = new double[5 * n + 1];
            int idx = 0;
            for (int j = n; j <= 6 * n; j++) {
                res[idx++] = f[n][j] * 1.0 / total;
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public double[] dicesProbability(int n) {

            int[] f = new int[6 * n + 1];
            for (int j = 1; j <= 6; j++) {
                f[j] = 1;
            }
            for (int i = 2; i <= n; i++) {
                for (int j = 6 * i; j >= i; j--) {
                    f[j] = 0 ;
                    for (int k = 1; k <= 6; k++) {
                        if (j - k < i - 1) break;
                        f[j] += f[j - k];
                    }

                }
            }
            int total = (int) Math.pow(6, n);
            double[] res = new double[5 * n + 1];
            int idx = 0;
            for (int j = n; j <= 6 * n; j++) {
                res[idx++] = f[j] * 1.0 / total;
            }
            return res;
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
