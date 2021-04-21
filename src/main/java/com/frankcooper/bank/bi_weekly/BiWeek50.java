package com.frankcooper.bank.bi_weekly;

import java.util.*;

import org.junit.Assert;

public class BiWeek50 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 5, 2, 4, 1};
            handler.minOperations(nums);

        }

        public int minOperations(int[] nums) {
            int res = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] <= nums[i - 1]) {
                    int delta = nums[i - 1] - nums[i] + 1;
                    res += delta;
                    nums[i] = nums[i] + delta;
                }
            }
            return res;
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
