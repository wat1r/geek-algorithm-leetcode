package com.frankcooper.bank._1_100;

public class _34 {

    static class _1st {


        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{5, 7, 7, 8, 8, 10};
            int target = 8;
//            handler.getFirst(nums, target);
            handler.getLast(nums, target);
        }


        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) return new int[]{-1, -1};
            int[] ans = new int[2];
            ans[0] = getFirst(nums, target);
            ans[1] = getLast(nums, target);
            return ans;
        }

        private int getFirst(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (nums[m] >= target) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            return nums[l] == target ? l : -1;
        }

        private int getLast(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l + 1) / 2;
                if (nums[m] <= target) {
                    l = m;
                } else {
                    r = m - 1;
                }
            }
            return nums[l] == target ? l : -1;
        }
    }
}
