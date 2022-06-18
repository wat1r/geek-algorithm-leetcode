package com.frankcooper.platform.leetcode.swordoffer;

import java.util.HashSet;
import java.util.Set;

public class Sword_03 {


    static class _1st {
        public int findRepeatNumber(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int n : nums) {
                if (set.contains(n)) return n;
                set.add(n);
            }
            return 0;
        }
    }

    static class _2nd {
        public int findRepeatNumber(int[] nums) {
            if (nums == null || nums.length == 0) return -1;
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                while (nums[i] != i) {
                    if (nums[i] == nums[nums[i]]) {
                        return nums[i];
                    }
                    swap(nums, nums[i], i);
                }
            }
            return -1;
        }

        public void swap(int[] arr, int m, int n) {
            int temp = arr[m];
            arr[m] = arr[n];
            arr[n] = temp;
        }
    }

    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {2, 3, 1, 0, 2, 5, 3};
            handler.findRepeatNumber(nums);
        }

        public int findRepeatNumber(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] != i) {
                    if (nums[i] == nums[nums[i]]) return nums[i];
                    int t = nums[nums[i]];
                    nums[nums[i]] = nums[i];
                    nums[i] = t;
                }
            }
            return -1;
        }
    }

}
