package com.frankcooper.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2068 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public boolean checkAlmostEquivalent(String word1, String word2) {
            int[] cnt1 = new int[26], cnt2 = new int[26];
            for (int i = 0, j = 0; i < word1.length() || j < word2.length(); i++, j++) {
                if (i < word1.length()) cnt1[word1.charAt(i) - 'a']++;
                if (j < word2.length()) cnt2[word2.charAt(j) - 'a']++;
            }
            for (int i = 0; i < cnt1.length; i++) {
                if (Math.abs(cnt1[i] - cnt2[i]) > 3) return false;
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
