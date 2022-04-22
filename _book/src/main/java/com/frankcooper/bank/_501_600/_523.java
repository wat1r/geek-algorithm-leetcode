package com.frankcooper.bank._501_600;

import java.util.*;

import org.junit.Assert;

public class _523 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {23, 2, 4, 6, 7};
            int k = 6;
            Assert.assertTrue(handler.checkSubarraySum(nums, k));
        }

        /**
         * TLE
         *
         * @param nums
         * @param k
         * @return
         */
        public boolean checkSubarraySum(int[] nums, int k) {
            int n = nums.length;
            long[] preSum = new long[n + 1];
            for (int i = 1; i <= n; i++) preSum[i] = preSum[i - 1] + nums[i - 1];
            for (int i = 1; i < n; i++) {
                for (int j = i - 1; j >= 0; --j) {
                    long t = preSum[i + 1] - preSum[j];
//                    System.out.printf("%d,%d,%d\n", j, i, t);
                    if (t % k == 0) return true;
                }
            }
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {2, 4, 3};
            int k = 6;
//            Assert.assertTrue(handler.checkSubarraySum(nums, k));
            nums = new int[]{2, 2, 2, 2};
            k = 4;
            Assert.assertTrue(handler.checkSubarraySum(nums, k));
        }

        /**
         * 前缀和+Hash
         *
         * @param nums
         * @param k
         * @return
         */
        public boolean checkSubarraySum(int[] nums, int k) {
            int n = nums.length;
            int[] preSum = new int[n + 1];
            //k存储的是preSum[i]%k的值，v是下标索引
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
                map.put(preSum[i] % k, i - 1);
            }
            for (int i = 0; i <= n; i++) {
                int t = preSum[i] % k;
                //存的是下标 [0,1]距离为2 可能下标的结果小
                if (map.containsKey(t) && Math.abs(map.get(t) - i + 1) >= 2) return true;
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
