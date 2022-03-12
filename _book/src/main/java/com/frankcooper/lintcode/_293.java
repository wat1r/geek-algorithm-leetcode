package com.frankcooper.lintcode;

import java.util.*;

import org.junit.Assert;

public class _293 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int FindDepth(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int lo = 0, hi = m - 1;
            while (lo <= hi) {
                int mid = lo + hi >> 1;
                int count = 0;
                for (int i = 0; i < n; i++) {
                    if (matrix[mid][i] == 1) count++;
                }
                if (count >= 2) lo = mid + 1;
                else hi = mid - 1;
            }
            return lo - 1;

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
