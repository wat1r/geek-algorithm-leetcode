package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _217 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean containsDuplicate(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums) {
                if (map.containsKey(x)) return true;
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
            return false;
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
