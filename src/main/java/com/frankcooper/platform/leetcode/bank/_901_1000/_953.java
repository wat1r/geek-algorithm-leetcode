package com.frankcooper.platform.leetcode.bank._901_1000;

import java.util.*;

public class _953 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public boolean isAlienSorted(String[] words, String order) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < order.length(); i++) {
                map.put(order.charAt(i), i);
            }
            String[] targets = new String[words.length];
            int idx = 0;
            for (String word : words) {
                String t = "";
                for (int i = 0; i < word.length(); i++) {
                    t += (char) (map.get(word.charAt(i)) + 'a');
                }
                targets[idx++] = t;
            }
            for (int i = 0; i < targets.length - 1; i++) {
                if (targets[i].compareTo(targets[i + 1]) > 0) {
                    return false;
                }
            }
            return true;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public boolean isAlienSorted(String[] words, String order) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < order.length(); i++) {
                map.put(order.charAt(i), i);
            }
            for (int i = 0; i < words.length - 1; i++) {
                String prev = words[i], cur = words[i + 1];
                boolean nonEqual = false;
                int len = Math.min(prev.length(), cur.length());
                for (int j = 0; j < len; j++) {
                    if (map.get(prev.charAt(j)) > map.get(cur.charAt(j))) {
                        return false;
                    } else if (map.get(prev.charAt(j)) < map.get(cur.charAt(j))) {
                        nonEqual = true;
                        break;
                    }
                }
                if (!nonEqual && prev.length() > cur.length()) {
                    return false;
                }
            }
            return true;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public boolean isAlienSorted(String[] words, String order) {
            int[] dict = new int[26];
            for (int i = 0; i < order.length(); i++) {
                dict[order.charAt(i) - 'a'] = i;
            }
            String[] mirror = words.clone();
            Arrays.sort(mirror, (a, b) -> {
                int m = a.length(), n = b.length();
                int i = 0, j = 0;
                while (i < m && j < n) {
                    char ac = a.charAt(i), bc = b.charAt(j);
                    if (ac != bc) return dict[ac - 'a'] - dict[bc - 'a'];
                    i++;
                    j++;
                }
                if (i < m) return 1;
                if (j < n) return -1;
                return 0;
            });
            for (int i = 0; i < words.length; i++) {
                if (!mirror[i].equals(words[i])) return false;
            }
            return true;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        int[] dict = new int[26];

        int check(String a, String b) {
            int m = a.length(), n = b.length();
            int i = 0, j = 0;
            while (i < m && j < n) {
                int ac = a.charAt(i) - 'a', bc = b.charAt(j) - 'a';
                if (ac != bc) return dict[ac] - dict[bc];
                i++;
                j++;
            }
            if (i < m) return 1;
            if (j < n) return -1;
            return 0;
        }

        public boolean isAlienSorted(String[] words, String order) {
            for (int i = 0; i < 26; i++) dict[order.charAt(i) - 'a'] = i;
            for (int i = 1; i < words.length; i++) {
                if (check(words[i - 1], words[i]) > 0) return false;
            }
            return true;
        }

    }
}
