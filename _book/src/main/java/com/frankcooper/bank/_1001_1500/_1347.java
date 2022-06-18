package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1347 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "leetcode", t = "practice";
            handler.minSteps(s, t);
        }


        public int minSteps(String s, String t) {
            int n = s.length();
            int[] chas = new int[26];
            int[] chat = new int[26];
            for (int i = 0; i < n; i++) {
                chas[s.charAt(i) - 'a']++;
                chat[t.charAt(i) - 'a']++;
            }
            int[] res = new int[26];
            int ans = 0;
            for (int i = 0; i < 26; i++) {
                res[i] = Math.abs(chas[i] - chat[i]);
                if (res[i] != 0) ans += res[i];
            }
            return ans >> 1;
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
