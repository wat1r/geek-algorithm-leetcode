package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1405 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public String longestDiverseString(int a, int b, int c) {
            Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
            if (a > 0) queue.offer(new int[]{0, a});
            if (b > 0) queue.offer(new int[]{1, b});
            if (c > 0) queue.offer(new int[]{2, c});
            StringBuilder res = new StringBuilder();
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int n = res.length();
                if (n >= 2 && res.charAt(n - 1) - 'a' == cur[0] && res.charAt(n - 2) - 'a' == cur[0]) {
                    if (queue.isEmpty()) break;
                    int[] next = queue.poll();
                    res.append((char) (next[0] + 'a'));
                    if (--next[1] != 0) {
                        queue.offer(next);
                    }
                    queue.offer(cur);
                } else {
                    res.append((char) (cur[0] + 'a'));
                    if (--cur[1] != 0) {
                        queue.offer(cur);
                    }
                }
            }
            return res.toString();
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public String longestDiverseString(int a, int b, int c) {
            Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
            if (a > 0) queue.offer(new int[]{'a', a});
            if (b > 0) queue.offer(new int[]{'b', b});
            if (c > 0) queue.offer(new int[]{'c', c});
            StringBuilder res = new StringBuilder();
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int n = res.length();
                if (n >= 2 && res.charAt(n - 1) == cur[0] && res.charAt(n - 2) == cur[0]) {
                    if (queue.isEmpty()) break;
                    int[] next = queue.poll();
                    res.append((char) (next[0]));
                    if (--next[1] != 0) {
                        queue.offer(next);
                    }
                    queue.offer(cur);
                } else {
                    res.append((char) (cur[0]));
                    if (--cur[1] != 0) {
                        queue.offer(cur);
                    }
                }
            }
            return res.toString();
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
