package com.frankcooper.platform.lintcode;

import java.util.*;

import org.junit.Assert;

public class _1476 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int peakIndexInMountainArray(int[] A) {
            int n = A.length - 1, lo = 0, hi = n - 1;
            while (lo < hi) {
                int mid = lo + hi >> 1;
                if (A[mid] < A[mid + 1]) lo = mid + 1;
                else hi = mid;
            }
            return lo;
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
