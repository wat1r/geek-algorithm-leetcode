package com.frankcooper.bank._501_700;

import java.util.Arrays;
import java.util.Stack;

public class _581 {

    static class _1st {
        public int findUnsortedSubarray(int[] nums) {
            int[] help = new int[nums.length];
            int i = 0;
            while (i < nums.length) help[i] = nums[i++];
            Arrays.sort(help);
            int start = -1, end = -1;
            for (i = 0; i < nums.length; i++) {
                if (nums[i] != help[i]) {
                    if (start == -1) start = i;
                    end = i;
                }
            }
            return start == end ? 0 : end - start + 1;
        }
    }

    static class _2nd {
        public int findUnsortedSubarray(int[] nums) {
            Stack<Integer> stk = new Stack<>();
            int l = nums.length, r = 0;
            for (int i = 0; i < nums.length; i++) {
                while (!stk.isEmpty() && nums[stk.peek()] > nums[i]) {
                    l = Math.min(l, stk.pop());
                }
                stk.push(i);
            }
            stk.clear();
            for (int i = nums.length - 1; i >= 0; i--) {
                while (!stk.isEmpty() && nums[stk.peek()] < nums[i]) {
                    r = Math.max(r, stk.pop());
                }
                stk.push(i);
            }

            return r - l > 0 ? r - l + 1 : 0;
        }
    }
}
