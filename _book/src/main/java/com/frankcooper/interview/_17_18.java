package com.frankcooper.platform.leetcode.interview;

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
            int[] big = new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
            int[] small = {1, 5, 9};
            int[] expected = new int[]{7, 10};
//            Assert.assertArrayEquals(expected, handler.shortestSeq(big, small));

            big = new int[]{878982, 143504, 268583, 394343, 849567, 257687, 352256, 35131, 663529, 543027};
            small = new int[]{143504};
            expected = new int[]{1, 1};
            Assert.assertArrayEquals(expected, handler.shortestSeq(big, small));
            /**
             * [842, 336, 777, 112, 789, 801, 922, 874, 634, 121, 390, 614, 179, 565, 740, 672, 624, 130, 555, 714, 9, 950, 887, 375, 312, 862, 304, 121, 360, 579, 937, 148, 614, 885, 836, 842, 505, 187, 210, 536, 763, 880, 652, 64, 272, 675, 33, 341, 101, 673, 995, 485, 16, 434, 540, 284, 567, 821, 994, 174, 634, 597, 919, 547, 340, 2, 512, 433, 323, 895, 965, 225, 702, 387, 632, 898, 297, 351, 936, 431, 468, 694, 287, 671, 190, 496, 80, 110, 491, 365, 504, 681, 672, 825, 277, 138, 778, 851, 732, 176]
             * [950, 885, 702, 101, 312, 652, 555, 936, 842, 33, 634, 851, 174, 210, 287, 672, 994, 614, 732, 919, 504, 778, 340, 694, 880, 110, 777, 836, 365, 375, 536, 547, 887, 272, 995, 121, 225, 112, 740, 567, 898, 390, 579, 505, 351, 937, 825, 323, 874, 138, 512, 671, 297, 179, 277, 304]
             *  if (map.get(big[l]) > 0) diff++;
             *  [2,98]
             */
        }

        public int[] shortestSeq(int[] big, int[] small) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : small) map.put(x, map.getOrDefault(x, 0) + 1);
            int l = 0, r = 0, diff = small.length;
            int len = Integer.MAX_VALUE;
            int[] res = {};
            while (r < big.length) {
                if (map.containsKey(big[r])) {
                    if (map.get(big[r]) > 0) diff--;
                    map.put(big[r], map.get(big[r]) - 1);

                }
                while (diff == 0) {
                    if (len > r - l) {
                        len = r - l;
                        res = new int[]{l, r};
                    }
                    if (map.containsKey(big[l])) {
                        map.put(big[l], map.get(big[l]) + 1);
                        if (map.get(big[l]) > 0) diff++;
                    }
                    l++;
                }
                r++;
            }
            return res;
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
