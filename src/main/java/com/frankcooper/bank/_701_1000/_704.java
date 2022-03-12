package com.frankcooper.bank._701_1000;

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

    static class _2nd {
        public int search(int[] nums, int target) {
            int n = nums.length, l = 0, r = n - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                int t = nums[mid];
                if (t == target) return mid;
                else if (t > target) r = mid - 1;
                else l = mid + 1;
            }
            return nums[l] == target ? l : -1;
        }
    }

    static class _3rd {
        public int search(int[] nums, int target) {
            int n = nums.length, l = 0, r = n - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                int t = nums[mid];
                if (t == target) return mid;
                else if (t > target) r = mid - 1;
                else l = mid + 1;
            }
            return -1;
        }
    }

}
