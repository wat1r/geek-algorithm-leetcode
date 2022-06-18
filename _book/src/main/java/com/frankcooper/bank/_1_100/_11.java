package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.*;

import org.junit.Assert;

public class _11 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int maxArea(int[] h) {
            int n = h.length, l = 0, r = n - 1;
            int res = 0;
            while (l < r) {
                res = Math.max(res, Math.min(h[l], h[r]) * (r - l));
                if (h[l] < h[r]) l++;
                else r--;
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
