package com.frankcooper.platform.leetcode.bank._101_200;

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
                int m = l + (r - l) / 2;//和最右端点r比较
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

    static class _2nd {
        public int findMin(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] > nums[r]) l = mid + 1;
                else r = mid;
            }
            return nums[r];
        }
    }

    static class _3rd {
        public int findMin(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] == nums[r]) return mid;
                else if (nums[mid] < nums[r]) {
                    r = mid;
                } else if (nums[mid] > nums[r]) {
                    l = mid + 1;
                }
            }
            return nums[l];
        }
    }

    static class _4th {
        public int findMin(int[] nums) {
            int n = nums.length;
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] > nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return nums[l];
        }
    }
}
