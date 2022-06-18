package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1685 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {1, 4, 6, 8, 10};
            handler.getSumAbsoluteDifferences(nums);

        }

        public int[] getSumAbsoluteDifferences(int[] nums) {
            int n = nums.length;
            int[] pre = new int[n];
            int t = 0;
            for (int i = 0; i < n; i++) {
                t += nums[i];
                pre[i] = t;
            }
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                int left = (i + 1) * nums[i] - pre[i];
                int right = pre[n - 1] - pre[i] - (n - i - 1) * nums[i];
                res[i] = left + right;
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
