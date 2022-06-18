package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.HashMap;
import java.util.Map;

public class _1791 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int findCenter(int[][] edges) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int[] e : edges) {
                int u = e[0], v = e[1];
                map.put(u, map.getOrDefault(u, 0) + 1);
                map.put(v, map.getOrDefault(v, 0) + 1);
            }

            int n = map.size();
            for (int k : map.keySet()) {
                if (n - 1 == map.get(k)) {
                    return k;
                }
            }
            return -1;
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
