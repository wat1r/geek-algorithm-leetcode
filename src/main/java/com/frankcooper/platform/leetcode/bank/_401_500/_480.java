package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.*;

public class _480 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) return null;
        double[] result = new double[nums.length - k + 1];
        PriorityQueue<Long> maxheap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Long> minheap = new PriorityQueue<>();
        int index = 0;
        boolean isEven = ((k & 1) == 0);
        for (int i = 0; i < nums.length; i++) {
            maxheap.offer((long) nums[i]);
            int total = minheap.size() + maxheap.size();
            if (total > k) {
                if (maxheap.contains((long) nums[i - k])) {
                    maxheap.remove((long) nums[i - k]);
                } else {
                    minheap.remove((long) nums[i - k]);
                }
            }
            if (maxheap.size() > 0 && minheap.size() > 0 && maxheap.peek() > minheap.peek()) {
                minheap.offer(maxheap.poll());
            }
            if (minheap.size() > maxheap.size()) {
                maxheap.offer(minheap.poll());
            }
            if (maxheap.size() > (minheap.size() + 1)) {
                minheap.offer(maxheap.poll());
            }
            total = minheap.size() + maxheap.size();
            if (total == k) {
                if (isEven) {
                    result[index++] = ((double) (maxheap.peek() + minheap.peek())) / 2;
                } else {
                    result[index++] = (double) (maxheap.peek());
                }
            }
        }
        return result;
    }
}
