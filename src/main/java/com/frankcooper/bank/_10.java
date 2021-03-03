package com.frankcooper.bank;

public class _10 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "aab", p = "c*a*b";
            handler.isMatch(s, p);
        }

        public boolean isMatch(String s, String p) {

            if (p.isEmpty()) return s.isEmpty();
            boolean f = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
            boolean res = true;
            if (p.length() >= 2 && p.charAt(1) == '*') {
                res = isMatch(s, p.substring(2)) || (f && isMatch(s.substring(1), p));
                return res;
            }
            return f && isMatch(s.substring(1), p.substring(1));
        }
    }


    static class _2nd {
        /**
         * https://leetcode-cn.com/problems/regular-expression-matching/solution/javadi-gui-yi-bu-yi-bu-de-you-hua-dao-ji-bai-100yi/
         */

        int[][] cache;


        public boolean isMatch(String s, String p) {
            cache = new int[s.length() + 1][p.length() + 1];
            char[] ss = s.toCharArray(), pp = p.toCharArray();
            return isMatch(ss, 0, pp, 0);

        }

        private boolean isMatch(char[] ss, int s1, char[] pp, int p1) {
            if (p1 >= pp.length) return s1 >= ss.length;
            if (cache[s1][p1] != 0) return cache[s1][p1] > 0;
            boolean f = s1 < ss.length && (ss[s1] == pp[p1] || pp[p1] == '.');
            boolean res = true;
            if (pp.length - p1 >= 2 && pp[p1 + 1] == '*') {
                res = isMatch(ss, s1, pp, p1 + 2) || (f && isMatch(ss, s1 + 1, pp, p1));
                if (res) cache[s1][p1] = 1;
                else cache[s1][p1] = -1;
                return res;
            }
            res = f && isMatch(ss, s1 + 1, pp, p1 + 1);
            if (res) cache[s1][p1] = 1;
            else cache[s1][p1] = -1;
            return res;
        }


    }
}
