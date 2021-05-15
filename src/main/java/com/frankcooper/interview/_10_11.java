package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _10_11 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {5, 3, 1, 2, 3};
            nums = new int[]{3, 5, 2, 1, 6, 4};
            //[3,5,1,6,2,4]
            handler.wiggleSort(nums);
        }

        public void wiggleSort(int[] nums) {
            Arrays.sort(nums);
            int i = 0, j = nums.length - 1;
            while (i < j) {
                swap(nums, i++, j--);
            }
            i = 1;
            j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i += 2;
                j -= 2;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {5, 3, 1, 2, 3};
            nums = new int[]{3, 5, 2, 1, 6, 4};
            //[3,5,1,6,2,4]
            handler.wiggleSort(nums);
        }


        public void wiggleSort(int[] nums) {
            for (int i = 1; i < nums.length; i++) {
                if (i % 2 == 0) {//峰
                    if (nums[i] < nums[i - 1]) swap(nums, i, i - 1);
                } else {//谷
                    if (nums[i] > nums[i - 1]) swap(nums, i, i - 1);
                }
            }
        }


        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {5, 3, 1, 2, 3};
            nums = new int[]{3, 5, 2, 1, 6, 4};
            //[3,5,1,6,2,4]
            handler.wiggleSort(nums);
        }


        public void wiggleSort(int[] nums) {
            int n = nums.length;
            int[] t = Arrays.copyOf(nums, n);
            Arrays.sort(t);
            int l = 0, r = n - 1;
            int i = 0;
            while (l < r) {
                nums[i++] = t[r--];
                nums[i++] = t[l++];
            }
            if (n % 2 != 0) {
                nums[i] = t[l];
            }


        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
