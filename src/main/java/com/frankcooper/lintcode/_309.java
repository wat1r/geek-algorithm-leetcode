package com.frankcooper.lintcode;

import java.util.*;

import org.junit.Assert;

public class _309 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int[] interleavedArray(int[] A, int[] B) {
            int m = A.length, n = B.length;
            int i = 0, j = 0;
            int[] res = new int[m + n];
            int idx = 0;
            while (i < m) {
                res[idx++] = A[i++];
                res[idx++] = B[j++];
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
