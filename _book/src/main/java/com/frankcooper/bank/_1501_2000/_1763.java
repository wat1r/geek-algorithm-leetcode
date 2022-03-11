package com.frankcooper.bank._1501_2000;

import java.util.HashSet;

import java.util.*;

import org.junit.Assert;

public class _1763 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public String longestNiceSubstring(String s) {
            String res = "";
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j <s.length(); j++) {
                    if (j - i + 1 > res.length() && check(s.substring(i, j + 1))) {
                        res = s.substring(i, j + 1);
                    }
                }
            }
            return res;
        }


        private boolean check(String sub) {
            Set<Character> set = new HashSet<>();
            for (char c : sub.toCharArray()) set.add(c);
            for (char c : sub.toCharArray()) {
                char l = Character.toLowerCase(c), u = Character.toUpperCase(c);
                if (!set.contains(l) || !set.contains(u)) {
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
