package com.frankcooper.bank._201_300;

import java.io.PipedReader;
import java.util.*;

import org.junit.Assert;

public class _239 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
            int k = 3;
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
