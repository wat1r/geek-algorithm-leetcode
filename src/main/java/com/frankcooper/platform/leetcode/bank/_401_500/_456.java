package com.frankcooper.platform.leetcode.bank._401_500;


import org.junit.Assert;

import java.util.Stack;

public class _456 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{-1, 3, 2, 0};
            Assert.assertTrue(handler.find132pattern(nums));
        }


        public boolean find132pattern(int[] nums) {
            int N = nums.length;
            Stack<Integer> stk = new Stack<>();
            int mid = Integer.MIN_VALUE;
            stk.push(nums[N - 1]);
            for (int i = N - 2; i >= 0; --i) {
                if (nums[i] < mid) return true;
                while (!stk.isEmpty() && nums[i] > stk.peek()) {
                    mid = Math.max(mid, stk.pop());
                }
                stk.push(nums[i]);
            }
            return false;
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
