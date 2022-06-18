package com.frankcooper.platform.leetcode.bank._1_100;

public class _76 {

    static _76 handler = new _76();

    public static void main(String[] args) {

    }


    static class _1st {
        public String minWindow(String s, String t) {
            if (s == null || t == null || s.length() < t.length()) return "";
            int left = 0, right = 0, len = Integer.MAX_VALUE;
            String res = "";
            int n = s.length();
            int[] source = new int[128];
            int[] target = new int[128];
            for (char c : t.toCharArray()) target[c]++;
            while (right < n) {
                if (!valid(source, target)) source[s.charAt(right++)]++;
                while (valid(source, target)) {
                    if (right - left < len) {
                        len = Math.min(len, right - left);
                        res = s.substring(left, right);
                    }
                    source[s.charAt(left++)]--;
                }
            }
            return res;
        }


        private boolean valid(int[] source, int[] target) {
            for (int i = 0; i < source.length; i++) {
                if (source[i] < target[i]) return false;
            }
            return true;
        }
    }


    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.minWindow("abcdefg", "ce");
        }


        public String minWindow(String s, String t) {
            if (s == null || t == null) return "";
            int[] q = new int[126];
            for (char c : t.toCharArray()) q[c]++;
            int[] p = new int[126];
            int minLen = Integer.MAX_VALUE / 2;
            int l = 0, r = 0;
            String ans = "";
            while (r < s.length()) {
                if (!check(p, q)) p[s.charAt(r++)]++;
                while (check(p, q)) {
                    if (r - l + 1 < minLen) {
                        minLen = r - l + 1;
                        ans = s.substring(l, r);
                    }
                    p[s.charAt(l++)]--;
                }
            }
            return ans;
        }


        /*check p contains all eles in q */
        private boolean check(int[] p, int[] q) {
            for (int i = 0; i < q.length; i++) if (p[i] < q[i]) return false;
            return true;
        }
    }

}
