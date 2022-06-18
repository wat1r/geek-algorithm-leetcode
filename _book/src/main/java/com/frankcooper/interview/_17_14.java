package com.frankcooper.platform.leetcode.interview;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _17_14 {

    public static void main(String[] args) {
        _1st handler = new _1st();
        int[] arr = new int[]{1, 2, 3};
        int k = 0;
        arr = new int[]{1, 3, 5, 7, 2, 4, 6, 8};
        k = 4;
        arr = new int[]{62577, -220, -8737, -22, -6, 59956, 5363, -16699, 0, -10603, 64, -24528, -4818, 96, 5747, 2638, -223, 37663, -390, 35778, -4977, -3834, -56074, 7, -76, 601, -1712, -48874, 31, 3, -9417, -33152, 775, 9396, 60947, -1919, 683, -37092, -524, -8, 1458, 80, -8, 1, 7, -355, 9, 397, -30, -21019, -565, 8762, -4, 531, -211, -23702, 3, 3399, -67, 64542, 39546, 52500, -6263, 4, -16, -1, 861, 5134, 8, 63701, 40202, 43349, -4283, -3, -22721, -6, 42754, -726, 118, 51, 539, 790, -9972, 41752, 0, 31, -23957, -714, -446, 4, -61087, 84, -140, 6, 53, -48496, 9, -15357, 402, 5541, 4, 53936, 6, 3, 37591, 7, 30, -7197, -26607, 202, 140, -4, -7410, 2031, -715, 4, -60981, 365, -23620, -41, 4, -2482, -59, 5, -911, 52, 50068, 38, 61, 664, 0, -868, 8681, -8, 8, 29, 412};
        k = 131;
        arr = new int[]{1, 3, 5, 7, 2, 4, 6, 8};
        k = 4;
        handler.smallestK(arr, k);
    }


    static class _1st {


        public int[] smallestK(int[] arr, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for (int a : arr) {
                if (k > 0) pq.offer(a);
                if (!pq.isEmpty()) {
                    if (pq.size() > k && pq.peek() > a) {
                        pq.poll();
                    }
                }
            }
            while (!pq.isEmpty() && pq.size() > k) {
                pq.poll();
            }
            int[] res = new int[k];
            int idx = k;
            while (!pq.isEmpty()) {
                res[--idx] = pq.poll();
            }
            return res;
        }
    }


}
