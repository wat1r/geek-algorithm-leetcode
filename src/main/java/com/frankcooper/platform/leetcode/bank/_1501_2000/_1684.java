package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1684 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int countConsistentStrings(String allowed, String[] words) {
            int[] source = new int[26];
            for (char c : allowed.toCharArray()) {
                source[c - 'a']++;
            }
            int res = 0;
            for (String word : words) {
                int[] target = Arrays.copyOf(source, source.length);
                boolean f = true;
                Set<Character> set = new HashSet<>();
                for (char c : word.toCharArray()) {
                    if (set.contains(c)) {
                        continue;
                    }
                    set.add(c);
                    target[c - 'a']--;
                    if (target[c - 'a'] < 0) {
                        f = false;
                        break;
                    }
                }
                if (f) {
                    res++;
                }
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int countConsistentStrings(String allowed, String[] words) {
            boolean[] arr = new boolean[26];
            for (char c : allowed.toCharArray()) {
                arr[c - 'a'] = true;
            }
            int res = 0;
            label:
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    if (!arr[c - 'a']) {
                        continue label;
                    }
                }
                res++;
            }
            return res;
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
