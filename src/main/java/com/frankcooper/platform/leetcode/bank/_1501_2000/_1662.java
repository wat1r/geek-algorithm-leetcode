package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1662 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int firstCompleteIndex(int[] arr, int[][] mat) {
            int m = mat.length, n = mat[0].length;
            Map<Integer, int[]> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    map.put(mat[i][j], new int[]{i, j});
                }
            }
            int[] row = new int[m];
            int[] col = new int[n];
            for (int i = 0; i < m * n; i++) {
                int[] x = map.get(arr[i]);
                int r = x[0], c = x[1];
                if (++row[r] == n || ++col[c] == m) {
                    return i;
                }
            }
            return -1;
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
