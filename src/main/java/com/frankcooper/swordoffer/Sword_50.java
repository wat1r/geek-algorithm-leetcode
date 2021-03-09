package com.frankcooper.swordoffer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Sword_50 {

    static class _1st {
        public char process(String s) {
            char[] chas = s.toCharArray();
            HashMap<Character, Boolean> map = new HashMap<>();
            for (char c : chas) {
                map.put(c, !map.containsKey(c));
            }
            for (char c : chas) if (map.get(c)) return c;
            return ' ';
        }
    }

    static class _2nd {
        public char process(String s) {
            char[] chas = s.toCharArray();
            Map<Character, Boolean> map = new LinkedHashMap<>();
            for (char c : chas) map.put(c, !map.containsKey(c));
            for (Map.Entry<Character, Boolean> e : map.entrySet()) {
                if (e.getValue()) return e.getKey();
            }
            return ' ';
        }
    }

    static class _3rd {
        public char process(String s) {
            char[] chas = s.toCharArray();
            int[] arr = new int[26];
            for (char c : chas) arr[c - 'a']++;
            for (char c : chas) if (arr[c - 'a'] == 1) return c;
            return ' ';
        }
    }
}


