package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _438 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> resList = new ArrayList<>();
            if (s == null || p == null || s.length() < p.length()) return resList;
            int[] source = new int[256];
            int[] target = new int[256];
            for (char c : p.toCharArray()) target[c]++;
            int left = 0, right = 0;
            while (right < s.length()) {
                if (!valid1(source, target)) {
                    source[s.charAt(right)]++;
                    right++;
                }
                while (valid1(source, target) || (right - left) > p.length()) {
                    if (valid1(source, target)) {
                        resList.add(left);
                    }
                    source[s.charAt(left)]--;
                    left++;
                }

            }
            return resList;
        }

        private boolean valid1(int[] source, int[] target) {
            for (int i = 0; i < source.length; i++) {
                if (source[i] != target[i]) return false;
            }
            return true;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> ans = new ArrayList<>();
            int n = s.length(), m = p.length();
            int[] c1 = new int[26], c2 = new int[26];
            for (int i = 0; i < m; i++) c2[p.charAt(i) - 'a']++;
            for (int l = 0, r = 0; r < n; r++) {
                c1[s.charAt(r) - 'a']++;
                if (r - l + 1 > m) c1[s.charAt(l++) - 'a']--;
                if (check(c1, c2)) ans.add(l);
            }
            return ans;
        }

        boolean check(int[] c1, int[] c2) {
            for (int i = 0; i < 26; i++) {
                if (c1[i] != c2[i]) return false;
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
