package com.frankcooper.bank._901_1000;

import java.util.*;

public class _954 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] arr = {4, -2, 2, -4};
//            handler.canReorderDoubled(arr);

        }

        static int N = 100010;

        public boolean canReorderDoubled(int[] arr) {
            int[] counter = new int[N * 4];
            //按绝对值从小到大开始排序，做小根堆
            PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> Math.abs(o1) - Math.abs(o2)));
            for (int x : arr) pq.add(x);
            while (!pq.isEmpty()) {
                int x = pq.poll(), t = x * 2;
                if (counter[x + 2 * N] != 0 && --counter[x + N * 2] >= 0) continue;
                counter[t + N * 2]++;
            }
            for (int i = 0; i < counter.length; i++) {
                if (counter[i] != 0) return false;
            }
            return true;
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
