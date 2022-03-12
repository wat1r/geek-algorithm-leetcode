package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _16_17 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int maxSubArray(int[] nums) {
            int l = 0, n = nums.length;
            int sum = nums[0], maxv = nums[0];
            for (int r = 1; r < n; r++) {
                sum += nums[r];
                //当加上的值比当前的[r]小，代表前面有负数，需要一直删除
                while (nums[r] > sum) {
                    sum -= nums[l++];
                }
                maxv = Math.max(maxv, sum);
            }
            return maxv;
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
