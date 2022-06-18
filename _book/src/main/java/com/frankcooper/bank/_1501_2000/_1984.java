package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;
import org.junit.Assert;
public class _1984 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        public int minimumDifference(int[] nums, int k) {
            Arrays.sort(nums);
            int i = 0, j = i+k-1;
            int res = 100005;
            while(j<nums.length)res = Math.min(res,nums[j++]-nums[i++]);
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
