package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _01_01 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean isUnique(String astr) {
            char[] ch = new char[26];
            for (char c : astr.toCharArray()) {
                if (ch[c - 'a'] != 0) return false;
                ch[c - 'a']++;
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
