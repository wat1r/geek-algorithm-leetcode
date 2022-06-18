package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.*;

import org.junit.Assert;

public class _88 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p = m-- + n-- - 1;
            while (m >= 0 && n >= 0) {
                nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
            }
            while (n >= 0) {
                nums1[p--] = nums2[n--];
            }


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
