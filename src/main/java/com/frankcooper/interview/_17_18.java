package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_18 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] big = new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
            int[] small = {1, 5, 9};
            handler.shortestSeq(big, small);

        }


        public int[] shortestSeq(int[] big, int[] small) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : small) map.put(x, map.getOrDefault(x, 0) + 1);
            int l = 0, r = 0, n = big.length;
            int start = 0, end = 0;
            while (r < n) {
//                if(map.containsKey(big[l]))
                while (check(map)) {
                    start = l;
                    end = r;
                    map.put(big[l], map.get(big[l] + 1));
                    l++;
                }
                if (map.containsKey(big[r])) {
                    map.put(big[r], map.get(big[r] - 1));
                }
                r++;
            }
            return null;
        }

        private boolean check(Map<Integer, Integer> map) {
            int cnt = 0;
            for (Integer x : map.keySet()) {
                cnt += map.get(x);
            }
            return cnt == 0;
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
