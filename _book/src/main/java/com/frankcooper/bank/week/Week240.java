package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week240 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int maximumPopulation(int[][] logs) {
            int[] cnt = new int[2051];
            for (int[] log : logs) {
                for (int y = log[0]; y < log[1]; y++) cnt[y]++;
            }
            int res = 0, ans = 0;
            for (int y = 0; y < cnt.length; y++) {
                if (cnt[y] > res) {
                    ans = y;
                    res = cnt[y];
                }
            }
            return ans;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums1 = {55, 30, 5, 4, 2};
            int[] nums2 = {100, 20, 10, 10, 5};
            Assert.assertEquals(2, handler.maxDistance(nums1, nums2));
        }

        public int maxDistance(int[] nums1, int[] nums2) {
            int i = 0, j = 0, m = nums1.length, n = nums2.length;
            int dis = 0;
            while (i < m) {
                while (j < n && nums1[i] <= nums2[j]) {
                    dis = Math.max(dis, j - i);
                    j++;
                }
                i++;
                dis = Math.max(dis, j - i);
            }
            return dis;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {2, 3, 3, 1, 2};
            handler.maxSumMinProduct(nums);
        }


        public int maxSumMinProduct(int[] nums) {
            int MOD = (int) 1e9 + 7;
            long ans = 0;
//            nums = new int[]
            int[] arr = new int[nums.length + 2];
            for (int i = 1; i < arr.length - 1; i++) {
                arr[i] = nums[i - 1];
            }
            long[] sum = new long[arr.length];
            for (int i = 1; i < arr.length; i++) sum[i] = sum[i - 1] + arr[i];
            Stack<Integer> stk = new Stack<>();//存数组的下标索引
            for (int i = 0; i < arr.length; i++) {
                while (!stk.isEmpty() && arr[stk.peek()] > arr[i]) {
                    int cur = arr[stk.pop()];
                    int left = stk.peek() + 1;
                    int right = i - 1;
                    ans = Math.max(ans, cur * (sum[right] - sum[left - 1]));
                }
                stk.push(i);
            }
            return (int) (ans % MOD);
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
