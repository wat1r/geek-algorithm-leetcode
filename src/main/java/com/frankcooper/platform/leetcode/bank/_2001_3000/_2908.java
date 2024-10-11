package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2908 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int minimumSum(int[] nums) {
            int n = nums.length;
            int[] suf = new int[n];
            suf[n - 1] = nums[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                suf[i] = Math.min(suf[i + 1], nums[i]);
            }
            int[] pre = new int[n];
            pre[0] = nums[0];
            for (int i = 1; i < n; i++) {
                pre[i] = Math.min(pre[i - 1], nums[i]);
            }
            int ans = Integer.MAX_VALUE;
            for (int i = 1; i < n - 1; i++) {
                if (pre[i - 1] < nums[i] && nums[i] > suf[i + 1]) {
                    ans = Math.min(ans, pre[i - 1] + nums[i] + suf[i + 1]);
                }
            }
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int minimumSum(int[] nums) {
            int n = nums.length;
            int[] suf = new int[n];
            suf[n - 1] = nums[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                suf[i] = Math.min(suf[i + 1], nums[i]);
            }
            int ans = Integer.MAX_VALUE;
            //记录前缀最小值，不用数组
            int pre = nums[0];
            for (int i = 1; i < n - 1; i++) {
                if (pre < nums[i] && nums[i] > suf[i + 1]) {
                    ans = Math.min(ans, pre + nums[i] + suf[i + 1]);
                }
                pre = Math.min(pre, nums[i]);
            }
            return ans == Integer.MAX_VALUE ? -1 : ans;
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
