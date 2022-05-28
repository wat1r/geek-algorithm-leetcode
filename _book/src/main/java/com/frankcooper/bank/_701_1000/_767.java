package com.frankcooper.bank._901_1000;

import java.util.PriorityQueue;

/**
 * 类似 169  229
 */

public class _767 {

    static _767 handler = new _767();

    public static void main(String[] args) {
//        handler.reorganizeString("aaab");
//        handler.reorganizeString("aab");
//        handler.reorganizeString("vvvloo");
    }

    class _1st {
        public String reorganizeString(String S) {
            int n = S.length();
            int[] arr = new int[26];
            PriorityQueue<Pair> pq = new PriorityQueue<>(((o1, o2) -> o2.cnt - o1.cnt));
            for (char c : S.toCharArray()) {
                arr[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (arr[i] > (n + 1) / 2) return "";
                if (arr[i] > 0) {
                    pq.offer(new Pair(arr[i], (char) (i + 'a')));
                }
            }
            StringBuilder sb = new StringBuilder();
            Pair prev = new Pair(0, ' ');
            while (!pq.isEmpty()) {
                Pair curr = pq.poll();
                sb.append(curr.c);
                if (prev.cnt > 0) {
                    pq.offer(prev);
                }
                prev = new Pair(curr.cnt - 1, curr.c);
            }
            return sb.toString();
        }


        class Pair {
            private int cnt;
            private char c;

            public Pair(int cnt, char c) {
                this.cnt = cnt;
                this.c = c;
            }
        }

    }

    class _2nd {

        public String reorganizeString(String S) {
            int n = S.length();
            int[] arr = new int[26];
            for (char c : S.toCharArray()) arr[c - 'a']++;
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
            for (int i = 0; i < 26; i++) {
                if (arr[i] > (n + 1) / 2) return "";
                if (arr[i] > 0) pq.offer(new int[]{i + 'a', arr[i]});
            }
            int[] prev = {-1, 0};
            StringBuilder sb = new StringBuilder();
            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                sb.append((char) curr[0]);
                if (prev[1] > 0) pq.offer(prev);
                prev = new int[]{curr[0], curr[1] - 1};
            }
            return sb.toString();
        }

    }

}
