package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1856 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 2, 3, 2};
            handler.maxSumMinProduct(nums);

        }


        //AC
        public int maxSumMinProduct(int[] nums) {
            int MOD = (int) 1e9 + 7;
            int n = nums.length;
            long[] s = new long[n + 1];
            for (int i = 0; i < n; i++) {
                s[i + 1] = s[i] + nums[i];
            }
            int[] left = new int[n], right = new int[n];
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!stk.isEmpty() && nums[stk.peek()] > nums[i]) {
                    stk.pop();
                }
                left[i] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(i);
            }
            stk.clear();
            for (int i = n - 1; i >= 0; --i) {
                while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                    stk.pop();
                }
                right[i] = stk.isEmpty() ? n : stk.peek();
                stk.push(i);
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                int l = left[i], r = right[i];
                res = Math.max(res, 1L * (s[r] - s[l + 1]) * nums[i]);
            }
            return (int) (res % MOD);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{1, 2, 3, 2};
            handler.maxSumMinProduct(nums);
        }

        public int maxSumMinProduct(int[] nums) {
            int n = nums.length;

            // 数组前缀和
            long[] pre = new long[n + 1];  // 存储下标“之前”的元素和
            pre[0] = nums[0];
            for (int i = 1; i <= n; i++) {
                pre[i] = pre[i - 1] + nums[i - 1];
            }

            // 单递增调栈
            Stack<Integer> stack = new Stack<>();
            // 求元素右边第一个比其小的
            int[] rightLower = new int[n];
            Arrays.fill(rightLower, n);  // 默认为n，即没发现
            for (int i = 0; i < n; i++) {
                // 单调递增栈
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    int t = stack.pop();
                    rightLower[t] = i;
                }
                stack.push(i);
            }
            // 求元素左边第一个比其小的
            int[] leftLower = new int[n];
            Arrays.fill(leftLower, -1);  // 默认为-1，即没发现
            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    int t = stack.pop();
                    leftLower[t] = i;
                }
                stack.push(i);
            }

            // 在前缀和及单调栈基础上，求最终解
            long ans = 0;
            for (int i = 0; i < n; i++) {
                int r = rightLower[i];
                int l = leftLower[i] + 1;
                long t = pre[r] - pre[l];
                ans = Math.max(ans, t * nums[i]);
            }
            long mod = (long) 1e9 + 7;
            return (int) (ans % mod);
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = new int[]{1, 2, 3, 2};
            Assert.assertEquals(14, handler.maxSumMinProduct(nums));
        }

        public int maxSumMinProduct(int[] nums) {
            int MOD = (int) 1e9 + 7;
            int n = nums.length;
            // left 是严格定义的，left[i] 是左侧最近的严格小于 nums[i] 的元素下标
            // right 是非严格定义的，right[i] 是右侧最近的小于等于 nums[i] 的元素下标
            int[] left = new int[n], right = new int[n];
            Arrays.fill(right, n - 1);
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                    right[stk.pop()] = i - 1;
                }
                if (!stk.isEmpty()) {
                    left[i] = stk.peek() + 1;
                }
                stk.push(i);
            }
            long[] pre = new long[n + 1];
            for (int i = 0; i < n; i++) {
                pre[i + 1] = pre[i] + nums[i];
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                res = Math.max(res, 1L * (pre[right[i] + 1] - pre[left[i]]) * nums[i]);
            }
            return (int) (res % MOD);
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[] nums = new int[]{1, 2, 3, 2};
            Assert.assertEquals(14, handler.maxSumMinProduct(nums));
        }

        public int maxSumMinProduct(int[] src) {
            int MOD = (int) 1e9 + 7;
            //设置前后两个哨兵 0 0
            int[] nums = new int[src.length + 2];
            for (int i = 0; i < src.length; i++) {
                nums[i + 1] = src[i];
            }
            int n = nums.length;
            long[] pre = new long[n + 1];
            for (int i = 0; i < n; i++) {
                pre[i + 1] = pre[i] + nums[i];
            }
            Deque<Integer> stk = new ArrayDeque<>();
            //右边第一个比它小的元素的下标
            int[] right = new int[n];
            for (int i = 0; i < n; i++) {
                while (!stk.isEmpty() && nums[i] < nums[stk.peek()]) {
                    right[stk.pop()] = i;
                }
                stk.push(i);
            }
            stk.clear();
            //左边第一个比它小的元素的下标
            int[] left = new int[n];
            for (int i = n - 1; i >= 0; --i) {
                while (!stk.isEmpty() && nums[i] < nums[stk.peek()]) {
                    left[stk.pop()] = i;
                }
                stk.push(i);
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                int l = left[i], r = right[i];
                res = Math.max(res, nums[i] * (pre[r] - pre[l + 1]));
            }
            return (int) (res % MOD);
        }

    }
}
