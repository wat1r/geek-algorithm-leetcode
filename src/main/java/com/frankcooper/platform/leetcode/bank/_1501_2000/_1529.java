package com.frankcooper.platform.leetcode.bank._1501_2000;

public class _1529 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int minFlips(String target) {
            int cnt = 0;
            char c = '0';
            for (int i = 0; i < target.length(); i++) {
                if (target.charAt(i) != c) {
                    cnt++;
                    c = c == '0' ? '1' : '0';
                }
            }
            return cnt;
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
