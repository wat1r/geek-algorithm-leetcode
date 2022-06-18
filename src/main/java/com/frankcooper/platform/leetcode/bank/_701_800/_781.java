package com.frankcooper.platform.leetcode.bank._701_800;

import java.util.*;

public class _781 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int numRabbits(int[] answers) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int a : answers) map.put(a, map.getOrDefault(a, 0) + 1);
            int res = 0;
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                int a = e.getKey(), b = e.getValue();
                res += (b + a) / (a + 1) * (a + 1);
            }
            return res;

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
