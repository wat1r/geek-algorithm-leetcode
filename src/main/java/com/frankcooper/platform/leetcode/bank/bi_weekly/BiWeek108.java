package com.frankcooper.platform.leetcode.bank.bi_weekly;

//import java.util.*;
//
//import com.frankcooper.utils.PrintUtils;
//import org.junit.Assert;

import sun.nio.cs.ext.MacArabic;

import java.util.*;

public class BiWeek108 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums) {
                map.put(x, 1);
            }
            int n = moveFrom.length;
            for (int i = 0; i < n; i++) {
                map.remove(moveFrom[i]);
                map.put(moveTo[i], 1);
            }
            ArrayList<Integer> res = new ArrayList<>(map.keySet());
            res.sort(Comparator.comparingInt(a -> a));
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.minimumBeautifulSubstrings("1011");
        }

        String s;
        Set<String> set;
        Integer[] cache;


        public int minimumBeautifulSubstrings(String s) {
            int n = s.length();
            this.s = s;
            this.cache = new Integer[n + 1];
            Set<String> set = new HashSet<>();
            for (int k = 0; k < 10; k++) {
                String t = Integer.toBinaryString((int) Math.pow(5, k));
                if (t.length() > 15) {
                    break;
                }
                set.add(t);
            }
            this.set = set;
            int res = dfs(n);
            return res != Integer.MAX_VALUE / 2 ? res : -1;

        }


        public int dfs(int i) {
            if (i == 0) {
                return 0;
            }
            if (cache[i] != null) {
                return cache[i];
            }
            int res = Integer.MAX_VALUE / 2;
            for (int j = i - 1; j >= 0; j--) {
                if (set.contains(s.substring(j, i))) {
                    System.out.printf("[%d:%d],%s\n", j, i, s.substring(j, i));
                    res = Math.min(res, dfs(j) + 1);
                }
            }
            System.out.println(res);
            return cache[i] = res;
        }
    }


    static class _3rd_1 {
        public static void main(String[] args) {
            _3rd_1 handler = new _3rd_1();
            handler.minimumBeautifulSubstrings("1011");

        }

        String s;
        Set<String> set;
        Integer[] cache;
        int n;

        public int minimumBeautifulSubstrings(String s) {
            this.n = s.length();
            this.s = s;
            this.cache = new Integer[n + 1];
            Set<String> set = new HashSet<>();
            for (int k = 0; k < 10; k++) {
                String t = Integer.toBinaryString((int) Math.pow(5, k));
                if (t.length() > 15) {
                    break;
                }
                set.add(t);
            }
            this.set = set;
            int res = dfs(0);
            return res != Integer.MAX_VALUE / 2 ? res : -1;

        }


        public int dfs(int i) {
            if (i == n) {
                return 0;
            }
            if (cache[i] != null) {
                return cache[i];
            }
            int res = Integer.MAX_VALUE / 2;
            for (int j = i + 1; j < n + 1; j++) {
                if (set.contains(s.substring(i, j))) {
                    System.out.printf("[%d:%d],%d\n", i, j, dfs(j) + 1);
                    res = Math.min(res, dfs(j) + 1);
                }
            }
            System.out.println(res);
            return cache[i] = res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }

}
