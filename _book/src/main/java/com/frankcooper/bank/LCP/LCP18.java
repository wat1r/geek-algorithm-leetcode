package com.frankcooper.platform.leetcode.bank.LCP;

import java.util.*;

import org.junit.Assert;

public class LCP18 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] s = new int[]{2, 1, 1};
            int[] d = new int[]{8, 9, 5, 1};
            int x = 9;
            handler.breakfastNumber(s, d, x);
        }

        int MOD = (int) 1e9 + 7;

        public int breakfastNumber(int[] staple, int[] drinks, int x) {
            Arrays.sort(staple);
            Arrays.sort(drinks);
            int i = 0, j = drinks.length - 1;
            long res = 0;
            while (i < staple.length && j >= 0) {
                while (i < staple.length && staple[i] + drinks[j] <= x) {
                    res += j + 1;
                    i++;
                }
                j--;
            }
            return (int) (res % MOD);
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
