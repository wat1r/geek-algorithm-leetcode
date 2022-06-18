package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

public class _239 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
            int k = 3;
            //下面是测试 (i - k) == deque.peekFirst()
//            nums = new int[]{1, -1};
//            k = 1;
            handler.maxSlidingWindow(nums, k);
        }

        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) return new int[]{};
            int n = nums.length;
            Deque<Integer> deque = new LinkedList<>();
            int[] ans = new int[n - k + 1];
            int index = 0;
            for (int i = 0; i < n; i++) {
                while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                    deque.pollLast();
                }
                deque.addLast(i);
                if ((i - k) == deque.peekFirst()) {
                    deque.pollFirst();
                }
                if (i >= k - 1) {
                    ans[index++] = nums[deque.peekFirst()];
                }
            }
            return ans;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
            int k = 3;
            handler.maxSlidingWindow(nums, k);
        }

        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) return new int[]{};
            int n = nums.length;
            int[] res = new int[n - k + 1];
            int idx = 0;
            Deque<Integer> q = new LinkedList<>();//存的是元素的下标索引
            for (int i = 0; i < n; i++) {
                //队列中有元素，但是元素的下标已经过期，即不在k的滑窗范围内，开始从进队的First位置移除过期的索引
                while (!q.isEmpty() && q.peek() < i - k + 1) {
                    q.poll();//pollFirst()
                }
                //队列中有元素，但是队列的last位置的索引的值，小于当前的nums[i]的值，last位置的索引是无意义的，可以提前弹出
                while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                    q.pollLast();
                }
                //加入当前索引
                q.offer(i);
                //一定要满足滑窗的条件的时候，开始收集
                if (i >= k - 1) {
                    res[idx++] = nums[q.peek()];//peekFirst()
                }
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {2, 1, 3, 4, 6, 3, 8, 9, 10, 12, 56};
            int k = 4;
            handler.maxSlidingWindow(nums, k);
        }

        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) return new int[]{};
            int n = nums.length;
            int[] left_max = new int[n];
            int[] right_max = new int[n];
            left_max[0] = nums[0];
            right_max[n - 1] = nums[n - 1];
            for (int i = 1; i < n; i++) {
                left_max[i] = (i % k == 0) ? nums[i] : Math.max(nums[i], left_max[i - 1]);
                int j = n - i - 1;
                right_max[j] = (j % k == 0) ? nums[j] : Math.max(nums[j], right_max[j + 1]);
            }
            int[] res = new int[n - k + 1];
            for (int i = 0, j = 0; i + k <= n; i++) {
                res[j++] = Math.max(right_max[i], left_max[i + k - 1]);
            }
            return res;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
