package com.frankcooper.platform.leetcode.bank._501_600;

import java.util.*;
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

    static class _1st_1 {
        public int[] nextGreaterElements(int[] nums) {
            Deque<Integer> stk = new ArrayDeque<>();
            int n = nums.length;
            int[] res = new int[n];
            Arrays.fill(res, -1);
            for (int i = 0; i < 2 * n; i++) {
                int t = nums[i % n];
                while (!stk.isEmpty() && nums[stk.peek()] < t) {
                    res[stk.pop()] = t;
                }
                if (i < n) {
                    stk.push(i);
                }
            }
            return res;
        }
    }

    static class _1st_2 {

        public static void main(String[] args) {
            _1st_2 handler = new _1st_2();
            int[] nums = new int[]{1, 2, 1};
            handler.nextGreaterElements(nums);
        }

        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            // 使用数组模拟栈，bottom 代表栈底，top 代表栈顶
            int[] d = new int[n * 2];
            int bottom = 0, top = -1;
            for (int i = 0; i < n * 2; i++) {
                while (bottom <= top && nums[i % n] > nums[d[top]]) {
                    int u = d[top--];
                    ans[u] = nums[i % n];
                }
                d[++top] = i % n;
            }
            return ans;
        }

    }
}
