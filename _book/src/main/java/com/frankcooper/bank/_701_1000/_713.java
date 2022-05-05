package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _713 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals(0, handler.numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0));
            Assert.assertEquals(0, handler.numSubarrayProductLessThanK(new int[]{1, 1, 1}, 1));
        }

        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (k <= 1) return 0;
            int n = nums.length, res = 0;
            //统计以nums[r]为右边界的子数组的个数
            for (int t = 1, l = 0, r = 0; r < n; r++) {
                t *= nums[r];
                while (t >= k) t /= nums[l++];
                res += r - l + 1;
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int numSubarrayProductLessThanK(int[] nums, int k) {
            int n = nums.length, res = 0;
            //统计以nums[r]为右边界的子数组的个数
            for (int t = 1, l = 0, r = 0; r < n; r++) {
                t *= nums[r];
                while (l < r && t >= k) t /= nums[l++];
                res += r - l + 1;
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int numSubarrayProductLessThanK(int[] nums, int k) {
            int n = nums.length, res = 0;
            //统计以nums[r]为右边界的子数组的个数
            for (int t = 1, l = 0, r = 0; r < n; ) {
                t *= nums[r++];
                while (l < r && t >= k) t /= nums[l++];
                res += r - l;
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
