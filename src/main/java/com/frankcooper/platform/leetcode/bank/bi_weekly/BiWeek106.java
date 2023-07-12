package com.frankcooper.platform.leetcode.bank.bi_weekly;

//import java.util.*;
//
//import com.frankcooper.utils.PrintUtils;
//import org.junit.Assert;

import org.junit.Assert;

public class BiWeek106 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public boolean isFascinating(int n) {
            String s = n + String.valueOf(2 * n) + 3 * n;
            int[] cnt = new int[10];
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    return false;
                }
                cnt[c - '0']++;
                if (cnt[c - '0'] > 1) {
                    return false;
                }
            }
            return true;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            Assert.assertEquals(4, handler.longestSemiRepetitiveSubstring("52233"));
//            Assert.assertEquals(4, handler.longestSemiRepetitiveSubstring("5494"));
            Assert.assertEquals(3, handler.longestSemiRepetitiveSubstring("0001"));
        }

//        public int longestSemiRepetitiveSubstring(String s) {
//            int n = s.length();
//            int[] dp = new int[n];//dp[i]表示以字符s[i]结尾的字符串的最长半重复子字符串的长度
//
//        }

        public int longestSemiRepetitiveSubstring(String s) {
            int l = 0, n = s.length(), same = 0, res = 1;
            for (int r = 1; r < n; r++) {
                if (s.charAt(r) == s.charAt(r - 1)) {
                    same++;
                }
                if (same > 1) {
                    l++;
                    while (s.charAt(l) != s.charAt(l - 1)) {
                        l++;
                    }
                    same = 1;
                }
                res = Math.max(res, r - l + 1);
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
