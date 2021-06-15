package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_19 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{2, 3};
            handler.missingTwo(nums);

        }

        public int[] missingTwo(int[] nums) {
            int size = nums.length;
            int[] arr = new int[size + 2];
            arr[0] = arr[1] = -1;
            for (int i = 2; i < arr.length; i++) arr[i] = nums[i - 2];
            for (int i = 0; i < arr.length; i++) {
                while (i != arr[i] && arr[i] != -1) {
                    swap(arr, i, arr[i]);
                }
            }
            int[] res = new int[2];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == -1) {
                    if (res[0] == 0) res[0] = i;
                    else res[1] = i;
                }
            }
            return res;
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
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
