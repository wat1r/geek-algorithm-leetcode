package com.frankcooper.bank._1_100;

import org.junit.Assert;

public class _33 {

    static class _1st {


        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {1, 3};
            int target = 3;
            nums = new int[]{4, 5, 6, 7, 8, 1, 2, 3};
            target = 8;
            nums = new int[]{4, 5, 6, 7, 0, 1, 2};
            target = 0;
            handler.search(nums, target);
        }


        public int search(int[] nums, int target) {
            int n = nums.length, l = 0, r = n - 1;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (nums[m] == target) return m;
                else if (nums[m] >= nums[l]) {
                    if (target >= nums[l] && target <= nums[m]) {
                        r = m;
                    } else {
                        l = m + 1;
                    }
                } else if (nums[m] < nums[l]) {
                    if (target > nums[m] && target <= nums[r]) {
                        l = m + 1;
                    } else {
                        r = m;
                    }
                }
            }
            return nums[l] == target ? l : -1;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {1, 3};
            int target = 3;
            Assert.assertEquals(handler.search(nums, target), 1);
            nums = new int[]{4, 5, 6, 7, 8, 1, 2, 3};
            target = 8;
            Assert.assertEquals(handler.search(nums, target), 4);
            nums = new int[]{4, 5, 6, 7, 0, 1, 2};
            target = 0;
            Assert.assertEquals(handler.search(nums, target), 4);
        }


        public int search(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (nums[m] == target) return m;
                else if (nums[m] >= nums[l]) {
                    if (target >= nums[l] && target <= nums[m]) r = m;
                    else l = m + 1;
                } else if (nums[m] < nums[l]) {
                    if (target >= nums[m] && target <= nums[r]) l = m + 1;
                    else r = m;
                }
            }
            return nums[l] == target ? l : -1;
        }
    }
}
