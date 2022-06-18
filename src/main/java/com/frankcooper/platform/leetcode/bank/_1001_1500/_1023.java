package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

public class _1023 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public List<Boolean> camelMatch(String[] queries, String pattern) {
            List<Boolean> res = new ArrayList<>();
            for (String s : queries) {
                res.add(check(s, pattern));
            }
            return res;
        }

        private boolean check(String s, String p) {
            int m = s.length(), n = p.length(), i = 0, j = 0;
            while (i < m || j < n) {
                // System.out.printf("%d %d-> ",i , j );
                if (i < m && j < n && s.charAt(i) == p.charAt(j)) {
                    i++;
                    j++;
                } else if (i < m && s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                    i++;
                } else {
                    return false;
                }
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
