package com.frankcooper.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2121 {

    //TLE 56 / 61 ->56 / 61
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        public long[] getDistances(int[] arr) {
            // k: arr[i] v: i
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
                list.add(i);
                map.put(arr[i], list);
            }
            long[] intervals = new long[arr.length];
            for (int i = 0; i < arr.length; i++) {
                if (intervals[i] != 0) continue;
                List<Integer> list = map.get(arr[i]);
                long t = 0;
                for (int x : list) {
                    t += Math.abs(i - x);
                }
                intervals[i] = t;
            }
            return intervals;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public long[] getDistances(int[] arr) {
            // k: arr[i] v: i
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
                list.add(i);
                map.put(arr[i], list);
            }
            long[] intervals = new long[arr.length];
            for (List<Integer> ls : map.values()) {
                long sum = 0;
                for (int index : ls) sum += index - ls.get(0);
                intervals[ls.get(0)] = sum;
                for (int i = 1; i < ls.size(); i++) {
                    sum += (2 * i - ls.size()) * (ls.get(i) - ls.get(i - 1));
                    intervals[ls.get(i)] = sum;
                }
            }
            return intervals;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public long[] getDistances(int[] arr) {
            // k: arr[i] v: i
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
                list.add(i);
                map.put(arr[i], list);
            }
            long[] intervals = new long[arr.length];
            for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
                List<Integer> ls = e.getValue();
                int m = ls.size();
                long[] left = new long[m];
                long[] right = new long[m];
                for (int i = 1, j = m - 2; i < m; i++, j--) {
                    left[i] = left[i - 1] + i * (ls.get(i) - ls.get(i - 1));
                    right[j] = right[j + 1] + (m - j - 1) * (ls.get(j + 1) - ls.get(j));
                }
                for (int i = 0; i < m; i++) {
                    intervals[ls.get(i)] = left[i] + right[i];
                }
            }
            return intervals;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
