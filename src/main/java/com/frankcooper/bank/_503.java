package com.frankcooper.bank;

import java.util.Arrays;
import java.util.Stack;

public class _503 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 2, 1};
            handler.nextGreaterElements(nums);
        }

        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            Stack<Integer> stk = new Stack<>();
            for (int i = 0; i < 2 * n; i++) {
                int curr = nums[i % n];
                while (!stk.isEmpty() && nums[stk.peek()] < curr) {
                    ans[stk.pop()] = curr;
                }
                if (i < n) stk.push(i);
            }
            return ans;
        }
    }
}
