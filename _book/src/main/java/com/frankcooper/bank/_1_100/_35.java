package com.frankcooper.platform.leetcode.bank._1_100;

public class _35 {

    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {1, 3, 5, 6};
            int target = 5;
//            handler.searchInsert(nums, target);
            nums = new int[]{1, 3, 5, 6};
            target = 2;
            handler.searchInsert(nums, target);
            nums = new int[]{1, 3, 5, 6};
            target = 7;
            handler.searchInsert(nums, target);
            nums = new int[]{1, 3, 5, 6};
            target = 0;
            handler.searchInsert(nums, target);
        }


        public int searchInsert(int[] nums, int target) {
            System.out.printf("target:%d\n", target);
            int n = nums.length, l = 0, r = n - 1;
            if (nums[r] < target) return n;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (nums[m] < target) l = m + 1;
                else r = m;
            }
            return l;

//            int n = nums.length, l = 0, r = n - 1;
//            if (nums[r] < target) return n;
//            while (l < r) {
//                int m = l + (r - l) / 2;
//                if (nums[m] < target) l = m + 1;
//                else r = m;
//            }
//            return l;
        }
    }

    static class _2nd {
        public int searchInsert(int[] nums, int target) {
            int n = nums.length, l = 0, r = n - 1;
            if (nums[r] < target) return n;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] < target) l = mid + 1;7
                else r = mid;
            }
            return l;
        }
    }
}
