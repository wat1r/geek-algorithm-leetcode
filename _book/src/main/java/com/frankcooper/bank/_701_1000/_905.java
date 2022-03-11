package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _905 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{0, 1};
            handler.sortArrayByParity(nums);

        }


        public int[] sortArrayByParity(int[] nums) {
            int i = 0, j = 0;
            while (j < nums.length) {
                while (j < nums.length && nums[j] % 2 == 1) j++;
                if (j > nums.length - 1) break;
                swap(nums, i, j);
                i++;
                j++;
            }
            return nums;
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
