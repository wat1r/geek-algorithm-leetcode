package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _383 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public boolean canConstruct(String ransomNote, String magazine) {
            int[] t = new int[26];
            for (char c : magazine.toCharArray()) t[c - 'a']++;
            for (char c : ransomNote.toCharArray()) {
                if (t[c - 'a']-- == 0) return false;
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
