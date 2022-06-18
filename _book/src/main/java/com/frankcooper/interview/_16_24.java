package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import org.junit.Assert;

public class _16_24 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public List<List<Integer>> pairSums(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
            for (int x : nums) {
                int y = target - x;
                if (map.containsKey(y) && map.get(y) > 0 && map.containsKey(x) && map.get(x) > 0) {
                    if (x == y && map.get(y) <= 1) continue;//如果x 和 y 相等，则元素的个数至少为2个
                    map.put(x, map.get(x) - 1);
                    map.put(y, map.get(y) - 1);
                    List<Integer> sub = new ArrayList<>();
                    res.add(Arrays.asList(x, y));
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
