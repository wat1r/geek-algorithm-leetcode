package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import org.junit.Assert;

public class _16_18 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String pattern = "abba";
            String value = "dogcatcatdog";
            Assert.assertTrue(handler.patternMatching(pattern, value));
        }


        /**
         * https://leetcode-cn.com/problems/pattern-matching-lcci/solution/shuang-bai-suan-fa-guan-jian-zhao-dao-fen-lei-de-j/
         * @param pattern
         * @param value
         * @return
         */
        public boolean patternMatching(String pattern, String value) {
            //1.pattern 为空
            if (pattern.isEmpty()) return value.isEmpty();
            //2.pattern不为空
            //2.1value 为空，pattern是否只是有一个字母组成
            if (value.isEmpty()) {
                int i = 0;
                while (i < pattern.length() && pattern.charAt(i) == pattern.charAt(0)) i++;
                return i == pattern.length();
            }
            //2.2 pattern 和value 都不为空
            int n = pattern.length(), m = value.length();
            int[] cnt = new int[2];
            for (char x : pattern.toCharArray()) cnt[x - 'a']++;
            if (cnt[0] == 0) return helper(value, cnt[1]);
            else if (cnt[1] == 0) return helper(value, cnt[0]);
            //3.1假设使得a，b其中之一为空，次数为0
            if (helper(value, cnt[0])) return true;
            if (helper(value, cnt[1])) return true;
            //3.2 a b 都不为空，枚举a，b匹配的长度
            for (int sa = 1; sa * cnt[0] <= m - cnt[1]; sa++) {
                if ((m - sa * cnt[0]) % cnt[1] != 0) continue;
                int sb = (m - sa * cnt[0]) / cnt[1];
                if (check(pattern, value, sa, sb)) return true;
            }
            return false;
        }


        /**
         * 是否可以k次切分value
         *
         * @param value
         * @param k
         * @return
         */
        private boolean helper(String value, int k) {
            int m = value.length();
            if (m % k != 0) return false;
            int step = m / k;
            for (int i = step; i < m; i += step) {
                if (!value.substring(0, step).equals(value.substring(i, i + step))) return false;
            }
            return true;
        }


        private boolean check(String pattern, String value, int sa, int sb) {
            String[] ps = new String[]{"", ""};
            for (int i = 0, j = 0; i < pattern.length(); i++) {
                if (pattern.charAt(i) == 'a') {
                    String t = value.substring(j, j + sa);
                    if (ps[0].equals("")) ps[0] = t;
                    else if (!t.equals(ps[0])) return false;
                    j += sa;
                } else if (pattern.charAt(i) == 'b') {
                    String t = value.substring(j, j + sb);
                    if (ps[1].equals("")) ps[1] = t;
                    else if (!t.equals(ps[1])) return false;
                    j += sb;
                }
            }
            return !ps[0].equals(ps[1]);
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
