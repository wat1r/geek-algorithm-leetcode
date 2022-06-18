package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _313 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        //丑数II 改一下
        public int nthSuperUglyNumber(int n, int[] primes) {
            PriorityQueue<Long> pq = new PriorityQueue<>();
            Set<Long> vis = new HashSet<>();
            pq.offer(1L);
            long res = 0;
            // int[] factors = {2, 3, 5};
            for (int i = 0; i < n; i++) {
                long cur = pq.poll();
                res = cur;
                for (int f : primes) {
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
            int n = 12;
            int[] primes = {2, 7, 13, 19};
            handler.nthSuperUglyNumber(n, primes);
        }

        public int nthSuperUglyNumber(int n, int[] primes) {
            int[] dp = new int[n];
            int[] idx = new int[primes.length];
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < primes.length; j++) {
                    if (min > dp[idx[j]] * primes[j]) {
                        min = dp[idx[j]] * primes[j];
                    }
                }
                dp[i] = min;
                for (int j = 0; j < primes.length; j++) {
                    if (min == dp[idx[j]] * primes[j]) {
                        idx[j]++;
//                        break;
                    }
                }

            }
            return dp[n - 1];
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
