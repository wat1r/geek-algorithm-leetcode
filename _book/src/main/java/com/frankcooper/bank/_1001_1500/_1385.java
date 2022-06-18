package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1385 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
            int res = 0;
            for (int t : arr1) {
                if (check(t, d, arr2)) res++;
            }
            return res;
        }


        private boolean check(int t, int d, int[] arr2) {
            for (int x : arr2) {
                if (Math.abs(x - t) <= d) return false;
            }
            return true;
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
