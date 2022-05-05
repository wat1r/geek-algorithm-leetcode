package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _933 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        class RecentCounter {

            PriorityQueue<Integer> pq;
            int N = 3000;

            public RecentCounter() {
                pq = new PriorityQueue<>();
            }

            public int ping(int t) {

                while (!pq.isEmpty() && pq.peek() < t - N) {
                    pq.poll();
                }
                pq.offer(t);
                return pq.size();
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class RecentCounter {
            Deque<Integer> q;

            public RecentCounter() {
                q = new LinkedList<>();
            }

            public int ping(int t) {
                q.offerLast(t);
                while (q.peek() < t - 3000) q.pollFirst();
                return q.size();
            }
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
