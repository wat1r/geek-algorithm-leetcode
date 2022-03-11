package com.frankcooper.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _220 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 2, 3, 1};
            int k = 3, t = 0;
            /**
             [2147483640,2147483641]
             1
             100
             */
            nums = new int[]{2147483640, 2147483641};
            k = 1;
            t = 100;
            Assert.assertTrue(handler.containsNearbyAlmostDuplicate(nums, k, t));

        }

        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            TreeSet<Long> ts = new TreeSet<>();//注意long型
            for (int i = 0; i < nums.length; i++) {
                if (i > k) ts.remove((long) nums[i - k - 1]);//k的滑窗
                Long ceil = ts.ceiling(((long) nums[i] - t));//返回一个最小且大于等于这个元素的元素
                if (ceil != null && ceil <= (long) nums[i] + t) {
                    return true;
                }
                ts.add((long) nums[i]);
            }
            return false;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            TreeSet<Long> treeSet = new TreeSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (i > k) treeSet.remove((long) nums[i - k - 1]);
                Long ceil = treeSet.ceiling((long) nums[i] - t);
                if (ceil != null && ceil <= nums[i] + t) return true;
                treeSet.add((long) nums[i]);
            }
            return false;
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
