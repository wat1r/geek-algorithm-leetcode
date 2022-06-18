package com.frankcooper.platform.leetcode.bank._501_600;

import java.util.*;

public class _567 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public boolean checkInclusion(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) return false;
            int[] s = new int[256];//count s2
            int[] t = new int[256];//count s1
            for (char c : s1.toCharArray()) t[c]++;
            int l = 0, r = 0;
            while (r < s2.length()) {
                if (!check(s, t)) s[s2.charAt(r++)]++;
                while (check(s, t) || (r - l) > s1.length()) {
                    if (check(s, t)) return true;
                    s[s2.charAt(l++)]--;
                }

            }
            return false;

        }


        public boolean check(int[] s, int[] t) {
            for (int i = 0; i < s.length; i++) if (s[i] != t[i]) return false;
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public boolean checkInclusion(String s1, String s2) {
            //记录短字符s1中每个字符出现的次数，也可以使用array来计数
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s1.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
            int l = 0, r = 0;
            while (r < s2.length()) {
                //窗口的最右边的字符
                char rc = s2.charAt(r);
                r++;
                //对当前字符执行-1
                map.put(rc, map.getOrDefault(rc, 0) - 1);
                //l < r 窗口要维持正常
                //rc的字符如果数量小于0，说明s1中持有的rc比s2中持有的rc字符的数量多
                while (l < r && map.get(rc) < 0) {
                    //最左边的窗口开始缩,缩窗口时，需要将次数恢复
                    char lc = s2.charAt(l);
                    l++;
                    map.put(lc, map.get(lc) + 1);
                }
                //如果当前的窗口的大小长度和s1字符长度相等，则说明此窗口满足要求
                if (r - l == s1.length()) return true;
            }
            return false;
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
