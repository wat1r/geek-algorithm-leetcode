package com.frankcooper.platform.leetcode.bank._901_1000;

public class _921 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int minAddToMakeValid(String s) {
            int l = 0, r = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') l++;
                else if (c == ')') {
                    if (l > 0) l--;
                    else r++;
                }
            }
            return Math.abs(l + r);
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
