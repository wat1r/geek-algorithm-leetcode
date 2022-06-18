package com.frankcooper.platform.leetcode.bank._101_200;

public class _154 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{2, 2, 2, 0, 1};
            nums = new int[]{1, 0, 1, 1, 1};
            handler.findMin(nums);
        }


        public int findMin(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (nums[m] < nums[r]) r = m;
                else if (nums[m] > nums[r]) l = m + 1;
                else if (nums[m] == nums[r]) r = r - 1;
            }
            return nums[l];
        }
    }


    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{2, 2, 2, 0, 1};
            nums = new int[]{3, 3, 1, 3};
            handler.findMin(nums);

        }

        public int findMin(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] > nums[r]) l = mid + 1;
                else if (nums[mid] < nums[r]) r = mid;
                else r = r - 1;
            }
            return nums[r];
        }
    }
}
