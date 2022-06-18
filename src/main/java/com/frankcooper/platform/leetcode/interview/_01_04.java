package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _01_04 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean canPermutePalindrome(String s) {
            char[] chas = s.toCharArray();
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < chas.length; i++) {
                map.put(chas[i], map.getOrDefault(chas[i], 0) + 1);
            }
            int cnt = 0;
            for (Character k : map.keySet()) {
                if (map.get(k) % 2 == 1) cnt++;
                if (cnt > 1) return false;
            }
            return true;
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
