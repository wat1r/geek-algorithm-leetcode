package com.frankcooper.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.util.*;

import org.junit.Assert;

public class _17_10 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int majorityElement(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
            for (int x : map.keySet()) if (map.get(x) > nums.length / 2) return x;
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
