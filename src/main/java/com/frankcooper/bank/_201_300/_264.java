package com.frankcooper.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _264 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }


        public int nthUglyNumber(int n) {
            PriorityQueue<Long> pq = new PriorityQueue<>();
            Set<Long> vis = new HashSet<>();
            pq.offer(1L);
            long res = 0;
            int[] factors = {2, 3, 5};
            for (int i = 0; i < n; i++) {
                long cur = pq.poll();
                res = cur;
                for (int f : factors) {
                    long t = f * cur;
                    if (!vis.contains(t)) {
                        vis.add(t);
                        pq.offer(t);
                    }
                }
            }
            return (int) res;
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
