package com.frankcooper.bank._501_600;

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

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{1, 2, 1};
            handler.nextGreaterElements(nums);
        }


        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Arrays.fill(res, -1);
            Stack<Integer> stk = new Stack<>();
            for (int i = 0; i < 2 * n; i++) {
                int ele = nums[i % n];
                System.out.printf("%d-->%d\n", i, ele);
                while (!stk.isEmpty() && nums[stk.peek()] < ele) {
                    res[stk.pop()] = ele;
                }
                stk.push(i % n);
            }
            return res;
        }


    }
}
