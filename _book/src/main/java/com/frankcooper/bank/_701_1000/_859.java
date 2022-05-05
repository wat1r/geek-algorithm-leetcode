package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _859 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertTrue(handler.buddyStrings("ab", "ba"));
            Assert.assertFalse(handler.buddyStrings("ab", "ab"));
            Assert.assertTrue(handler.buddyStrings("aa", "aa"));
            //保持除了换了字符位置的其他字符的相对有序
            Assert.assertFalse(handler.buddyStrings("abcd", "bdac"));
            //"abcaa"
            //"abcbb"
            //false

            Assert.assertFalse(handler.buddyStrings("abcaa", "abcbb"));
        }


        public boolean buddyStrings(String s, String goal) {
            if (s.length() != goal.length()) return false;
            int swap = 0;//字符交换的次数
            int[] cnt = new int[26];
            int[] a = new int[26];//"abcd" "abca" 这个是false
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != goal.charAt(i)) {
                    a[s.charAt(i) - 'a']++;
                    a[goal.charAt(i) - 'a']--;
                    swap++;
                }
                if (swap > 2) return false;
                if (cnt[s.charAt(i) - 'a'] == 0) cnt[s.charAt(i) - 'a']++;
            }
            for (int x : a) {
                if (x != 0) return false;
            }
            int len = 0;
            for (int x : cnt) len += x;
            //"ab" "ab"  true
            if (swap == 0 && len == s.length()) return false;
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
