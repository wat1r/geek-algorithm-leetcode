package com.frankcooper.bank._201_300;

import java.io.PipedReader;
import java.util.*;

import org.junit.Assert;

public class _239 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] res = new int[n - k + 1];
            int idx = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
            for (int i = 0; i < n; i++) {
                while (pq.size() < k) {
                    pq.offer(new int[]{i, nums[i]});
                }

            }

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
