package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1403 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {4, 3, 10, 9, 8};
            handler.minSubsequence(nums);

        }


        public List<Integer> minSubsequence(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            int[] sum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
            int cur = 0;
            List<Integer> res = new ArrayList<>();
            for (int i = n - 1; i >= 0; i--) {
                cur += nums[i];
                res.add(nums[i]);
                int remain = sum[i] - sum[0];
                if (cur > remain) {
                    break;
                }
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
