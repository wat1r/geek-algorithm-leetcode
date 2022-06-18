package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1913 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int maxProductDifference(int[] nums) {
            if (nums.length < 4) return 0;
            Arrays.sort(nums);
            int n = nums.length;
            int w = nums[n - 1], x = nums[n - 2], y = nums[0], z = nums[1];
            return w * x - y * z;
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
