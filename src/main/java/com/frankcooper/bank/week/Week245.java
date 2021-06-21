package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week245 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String[] words = new String[]{"caaaaa", "aaaaaaaaa", "a", "bbb", "bbbbbbbbb", "bbb", "cc", "cccccccccccc", "ccccccc", "ccccccc", "cc", "cccc", "c", "cccccccc", "c"};
            Assert.assertTrue(handler.makeEqual(words));

        }


        public boolean makeEqual(String[] words) {
            int n = words.length;
            int[] s = new int[26];
            for (String w : words) {
                for (char c : w.toCharArray()) {
                    s[c - 'a']++;
                }
            }
            for (int c : s) {
                if (c != 0 && c % n != 0) return false;
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String s = "abcacb", p = "ab";
            int[] removable = {3, 1, 0};
            Assert.assertEquals(2, handler.maximumRemovals(s, p, removable));

            s = "abcbddddd";
            p = "abcd";
            removable = new int[]{3, 2, 1, 4, 5, 6};
            Assert.assertEquals(1, handler.maximumRemovals(s, p, removable));

            s = "abcab";
            p = "abc";
            removable = new int[]{0, 1, 2, 3, 4};

            Assert.assertEquals(0, handler.maximumRemovals(s, p, removable));


            s = "qlevcvgzfpryiqlwy";
            p = "qlecfqlw";
            removable = new int[]{12, 5};
            Assert.assertEquals(2, handler.maximumRemovals(s, p, removable));
        }


        public int maximumRemovals(String s, String p, int[] removable) {
            int n = removable.length;
            int l = 0, r = n;
            while (l < r) {
                int mid = l + r >> 1;
                String ns = remove(s, removable, 0, mid);//注意从0开始的
                if (check(ns, p)) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }


        private String remove(String s, int[] removable, int start, int end) {
            StringBuilder sb = new StringBuilder();
            char[] chas = s.toCharArray();
            for (int i = start; i <= end; i++) {
                chas[removable[i]] = ' ';
            }
            for (char c : chas) if (c != ' ') sb.append(c);
            return sb.toString();

        }


        /**
         * 判断t是不是s的一个子序列
         *
         * @param s
         * @param t
         * @return
         */
        private boolean check(String s, String t) {
            char[] chas = s.toCharArray();
            char[] chat = t.toCharArray();
            int i = 0, j = 0;
            while (i < chas.length) {
                if (chas[i] == chat[j]) {
                    i++;
                    j++;
                } else {
                    i++;
                }
                if (j == chat.length) return true;
            }
            return j == chat.length;
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
