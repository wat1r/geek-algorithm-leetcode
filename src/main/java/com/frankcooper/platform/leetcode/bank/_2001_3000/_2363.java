package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;
public class _2363 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int[] v : items1) {
                map.put(v[0], map.getOrDefault(v[0], 0) + v[1]);
            }
            for (int[] v : items2) {
                map.put(v[0], map.getOrDefault(v[0], 0) + v[1]);
            }

            List<List<Integer>> res = new ArrayList<List<Integer>>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int k = entry.getKey(), v = entry.getValue();
                List<Integer> pair = new ArrayList<Integer>();
                pair.add(k);
                pair.add(v);
                res.add(pair);
            }
            Collections.sort(res, (a, b) -> a.get(0) - b.get(0));
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
