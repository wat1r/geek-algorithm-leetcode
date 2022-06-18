package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _396 {

    //TLE 45/58
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        public int maxRotateFunction(int[] nums) {
            int n = nums.length;
            int k = 0;
            int maxx = Integer.MIN_VALUE;
            while (k++ < n) {
                int f = 0;
                for (int i = 0; i < n; i++) {
                    f += nums[(i + k) % n] * i;
                }
                maxx = Math.max(maxx, f);
            }
            return maxx;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {4, 3, 2, 6};
            Assert.assertEquals(26, handler.maxRotateFunction(nums));
        }

        public int maxRotateFunction(int[] nums) {
            int n = nums.length, sum = 0;
            int t = 0;
            for (int i = 0; i < n; i++) {
                sum += nums[i];
                t += i * nums[i];
            }
            int res = t;
            for (int i = 1; i < n; i++) {
                int nxt = t + sum - n * nums[n - i];
                res = Math.max(res, nxt);
                t = nxt;
            }
            return res;
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
