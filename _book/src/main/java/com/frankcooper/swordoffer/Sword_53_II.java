package com.frankcooper.swordoffer;

public class Sword_53_II {


    static class _1st {
        public int missingNumber(int[] nums) {
            for (int i = 0; i < nums.length; ++i) {
                if (i != nums[i]) return i;
            }
            return nums.length;
        }
    }


    static class _2nd {
        public int missingNumber(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] == mid) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return nums[l] == l ? nums[l] + 1 : l;
        }
    }
}
