package com.frankcooper.bank._101_200;

public class _153 {

    static class _1st {


        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{3, 4, 5, 1, 2};
            handler.findMin(nums);
        }

        public int findMin(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (nums[m] == nums[r]) return nums[m];
                else if (nums[m] > nums[r]) {
                    l = m + 1;
                } else if (nums[m] < nums[r]) {
                    r = m;
                }
            }
            return nums[l];
        }

    }
}
