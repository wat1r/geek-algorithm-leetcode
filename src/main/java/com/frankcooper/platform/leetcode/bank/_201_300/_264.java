package com.frankcooper.platform.leetcode.bank._201_300;

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
            Assert.assertEquals(handler.nthUglyNumber(10), 12);
        }

        public int nthUglyNumber(int n) {
            int[] dp = new int[n];
            dp[0] = 1;
            int a = 0, b = 0, c = 0;
            int x = 2, y = 3, z = 5;
            for (int i = 1; i < n; i++) {
                int t1 = dp[a] * x, t2 = dp[b] * y, t3 = dp[c] * z;
                dp[i] = Math.min(t1, Math.min(t2, t3));
                if (dp[i] == t1) a++;
                if (dp[i] == t2) b++;
                if (dp[i] == t3) c++;
            }
            return dp[n - 1];
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int nthUglyNumber(int n) {

            PriorityQueue<Long> pq = new PriorityQueue<>();
            Set<Long> vis = new HashSet<>();
            pq.offer(1L);
            long res = 0;
            for (int i = 0; i < n; i++) {
                long cur = pq.poll();
                res = cur;
                int[] factors = {2, 3, 5};
                for (int f : factors) {
                    long t = f * cur;
                    if (!vis.contains(t)) {
                        pq.offer(t);
                        vis.add(t);
                    }
                }
            }
            return (int) res;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            try {
                int i = 1 / 0;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            } finally {
                System.out.println("finally");
            }
        }


    }
}
