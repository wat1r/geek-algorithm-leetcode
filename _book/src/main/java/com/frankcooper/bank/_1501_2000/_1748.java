package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1748 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int sumOfUnique(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums) {
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
            int res = 0;
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                if (e.getValue() == 1) {
                    res += e.getKey();
                }
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
