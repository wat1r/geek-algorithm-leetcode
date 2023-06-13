package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _2475 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {4, 4, 2, 4, 3};
            Assert.assertEquals(3, handler.unequalTriplets(nums));

        }

        public int unequalTriplets(int[] nums) {
            int cnt = 0;
            Arrays.sort(nums);
            for (int l = 0, r = 1; r < nums.length; ) {
                if (nums[l] != nums[r]) {
                    cnt += (r - l) * l * (nums.length - r);
                    l = r;
                } else {
                    r++;
                }
            }
            return cnt;
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
