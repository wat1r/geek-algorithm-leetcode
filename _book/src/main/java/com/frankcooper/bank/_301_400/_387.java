package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _387 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int firstUniqChar(String s) {
            int[] t = new int[26];
            for (char c : s.toCharArray()) {
                t[c - 'a']++;
            }
            for (int i = 0; i < s.length(); i++) {
                if (t[s.charAt(i) - 'a'] == 1) return i;
            }
            return -1;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int firstUniqChar(String s) {
            char[] chs = s.toCharArray();
            int[] t = new int[26];
            for (char c : chs) {
                t[c - 'a']++;
            }
            for (int i = 0; i < s.length(); i++) {
                if (t[s.charAt(i) - 'a'] == 1) return i;
            }
            return -1;
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
