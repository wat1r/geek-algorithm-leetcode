package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _910 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int smallestRangeII(int[] nums, int k) {
            int n = nums.length;
            Arrays.sort(nums);
            int res = nums[n - 1] - nums[0];
            for (int i = 0; i < n - 1; i++) {
                int maxx = Math.max(nums[i] + k, nums[n - 1] - k);
                int minn = Math.min(nums[i + 1] - k, nums[0] + k);
                int diff = maxx - minn;
                res = Math.min(res, diff);
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
