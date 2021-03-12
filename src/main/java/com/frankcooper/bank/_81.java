package com.frankcooper.bank;

public class _81 {
    static class _1st {
        public boolean search(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (nums[m] == target) return true;
                if (nums[m] > nums[l]) {
                    if (nums[m] >= target && target >= nums[l]) {
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                } else if (nums[m] < nums[l]) {
                    if (nums[r] >= target && target >= nums[m]) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                } else if (nums[m] == nums[l]) {
                    l++;
                }
            }

            return false;
        }
    }
}
