package com.frankcooper.platform.leetcode.swordoffer;

import java.util.*;

import org.junit.Assert;

public class Sword_40 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] arr = {0, 1, 2, 1};
            int k = 1;
            arr = new int[]{3, 2, 1};
            k = 2;
            handler.getLeastNumbers(arr, k);
        }

        public int[] getLeastNumbers(int[] arr, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            for (int x : arr) {
                pq.offer(x);
                if (!pq.isEmpty() && (pq.size() > k)) {
                    pq.poll();
                }
            }
            int[] res = new int[k];
            for (int i = 0; i < k; i++) res[i] = pq.poll();
            return res;
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
