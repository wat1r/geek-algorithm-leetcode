package com.frankcooper.platform.lintcode;

import java.util.*;

import org.junit.Assert;

public class _521 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 3, 1, 4, 4, 2};
            handler.deduplication(nums);

        }


        public int deduplication(int[] nums) {
            Set<Integer> set = new HashSet<>();
            int i = 0, j = 0;
            while (j < nums.length) {
                while (!set.contains(nums[j])) {
                    swap(nums, i, j);
                    set.add(nums[i]);
                    i++;
                }
                j++;
            }
            return i ;

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
