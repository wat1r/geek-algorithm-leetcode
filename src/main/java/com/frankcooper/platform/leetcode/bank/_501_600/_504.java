package com.frankcooper.platform.leetcode.bank._501_600;

public class _504 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int num = 100;
            handler.convertToBase7(num);

        }

        public String convertToBase7(int num) {
            if(num ==0 ) return "0";
            boolean f = num < 0;
            if (f) num = -num;
            StringBuilder res = new StringBuilder();
            while (num >0 ) {
                res.append(num % 7);
                num /= 7;
            }
            res.reverse();
            return f ? "-" + res.toString() : res.toString();
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
