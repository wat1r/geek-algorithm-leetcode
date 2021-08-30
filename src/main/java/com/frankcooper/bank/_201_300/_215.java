package com.frankcooper.bank._201_300;


import java.util.PriorityQueue;

//215. 数组中的第K个最大元素   Kth Largest Element in an Array
public class _215 {


    public static void main(String[] args) {
        _215 handler = new _215();
        handler.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);

//        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);


    }

    /*

     */
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k || nums[i] > pq.peek()) pq.offer(nums[i]);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }


    static class _2nd {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < nums.length; i++) {
                if (i < k || nums[i] > pq.peek()) pq.offer(nums[i]);
                if (pq.size() > k) pq.poll();
            }
            return pq.peek();
        }
    }

    static class _3rd {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < nums.length; i++) {
                if (i < k || nums[i] > pq.peek()) pq.offer(nums[i]);
                if (pq.size() > k) pq.poll();
            }
            return pq.peek();
        }
    }
}
