package com.frankcooper.bank;

public class _704 {
    static class _1st {
        public int search(int[] nums, int target) {
            int n = nums.length, l = 0, r = n - 1;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (nums[m] < target) l = m + 1;
                else r = m;
            }
            return nums[l] == target ? l : -1;
        }
    }
}
