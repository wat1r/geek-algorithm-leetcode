package com.frankcooper.bank._601_700;

import java.util.*;

import org.junit.Assert;

public class _645 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int[] findErrorNums(int[] nums) {
            int n = nums.length;
            int i = 0;
            while (i < n) {
                while (nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i] - 1);
                }
                i++;
            }
            i = 0;
            int[] res = new int[2];
            for (int j = 0; j < n; j++) {
                if (nums[j] != j + 1) {
                    res[i++] = nums[j];
                    res[i++] = j + 1;
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
