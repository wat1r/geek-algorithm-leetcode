package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _296 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int minTotalDistance(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int xi = 0;
            int[] x = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) x[xi++] = i;
                }
            }
            int yi = 0;
            int[] y = new int[m * n];
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < m; i++) {
                    if (grid[i][j] == 1) y[yi++] = j;
                }
            }
            int lo = 0, hi = xi - 1;
            int res = 0;
            while (lo < hi) {
                res += x[hi--] - x[lo++];
            }
            lo = 0;
            hi = yi - 1;
            while (lo < hi) {
                res += y[hi--] - y[lo++];
            }
            return res;
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
