package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.*;

public class _442 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
            handler.findDuplicates(nums);

        }


        public List<Integer> findDuplicates(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] != nums[nums[i] - 1]) {
                    int t = nums[i];
                    nums[i] = nums[nums[i] - 1];
                    nums[t - 1] = t;
                }
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i + 1) res.add(nums[i]);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
            handler.findDuplicates(nums);
        }

        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int x = Math.abs(nums[i]);
                if (nums[x - 1] > 0) {
                    nums[x - 1] = -nums[x - 1];
                } else {
                    res.add(x);
                }
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
