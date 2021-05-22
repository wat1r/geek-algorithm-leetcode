package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_04 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {3, 0, 1};
            Arrays.sort(nums);
            handler.missingNumber(nums);
        }


        public int missingNumber(int[] nums) {
            int n = nums.length, res = n;
            for (int i = 0; i < n; i++) {
                res ^= i;
                res ^= nums[i];
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int missingNumber(int[] nums) {
            int n = nums.length;
            int sum1 = 0;//实际的和
            int sum2 = n;//不缺元素的和
            for (int i = 0; i < n; i++) {
                sum1 += nums[i];
                sum2 += i;
            }
            return sum2 - sum1;
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
