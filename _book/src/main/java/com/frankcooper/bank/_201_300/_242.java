package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _242 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean isAnagram(String s, String t) {
            if (s == null || t == null || s.length() != t.length()) {
                return false;
            }
            int[] arr = new int[26];
            for (int i = 0; i < s.length(); i++) {
                arr[s.charAt(i) - 'a']++;
                arr[t.charAt(i) - 'a']--;
            }
            for (int x : arr) {
                if (x != 0) return false;
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public boolean isAnagram(String s, String t) {
            char[] chs = s.toCharArray(), cht = t.toCharArray();
            if (chs.length != cht.length) return false;
            int[] arr = new int[26];
            for (char c : chs) arr[c - 'a']++;
            for (char c : cht) {
                if (--arr[c - 'a'] < 0) return false;
            }
            return true;
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
