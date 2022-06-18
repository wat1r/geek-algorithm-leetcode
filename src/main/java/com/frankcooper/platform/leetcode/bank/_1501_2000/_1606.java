package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

public class _1606 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < k; i++) set.add(i);
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            int[] sums = new int[k];
            int time = 0;
            for (int i = 0; i < arrival.length; i++) {
                time = arrival[i];
                while (!pq.isEmpty() && pq.peek()[0] <= time) {
                    set.add(pq.poll()[1]);
                }
                if (set.isEmpty()) continue;
                Integer j = set.ceiling(i % k);
                if (j == null) j = set.first();
                pq.add(new int[]{time + load[i], j});
                set.remove(j);
                sums[j]++;
            }
            List<Integer> list = new ArrayList<>();
            int max = 0;
            for (int i = 0; i < k; i++) {
                if (sums[i] == max) list.add(i);
                if (sums[i] > max) {
                    max = sums[i];
                    list.clear();
                    list.add(i);
                }
            }
            return list;
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
