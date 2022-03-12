package com.frankcooper.lintcode;

import java.util.*;

public class _5 {

    static _5 handler = new _5();

    public static void main(String[] args) {
        int n = 1;
        int[] nums = new int[]{1, 3, 4, 2};
//        n = 3;
//        nums = new int[]{9, 3, 2, 4, 8};
        handler.kthLargestElement(n, nums);
    }


    public int kthLargestElement(int n, int[] nums) {
        // write your code here
        Queue<Integer> pq = new PriorityQueue<>();
        for (int a : nums) {
            while (!pq.isEmpty() && (pq.size() >= n && pq.peek() < a)) {
                pq.poll();
            }
            pq.offer(a);
        }
        if (!pq.isEmpty() && pq.size() > n) {
            pq.poll();
        }
        // System.out.printf("%d\n",pq.size());
        return pq.isEmpty() ? -1 : pq.peek();
    }

}
