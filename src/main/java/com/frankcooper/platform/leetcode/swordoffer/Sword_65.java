package com.frankcooper.platform.leetcode.swordoffer;

public class Sword_65 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int a = 20, b = 17;
            handler.add(a, b);
        }


        public int add(int a, int b) {
//            PrintUtils.toBinaryString(a, 8);
//            PrintUtils.toBinaryString(b, 8);
            while (b != 0) {
                int c = (a & b) << 1;
//                PrintUtils.toBinaryString(c, 8);
                a ^= b;
//                PrintUtils.toBinaryString(a, 8);
                b = c;
//                PrintUtils.toBinaryString(b, 8);
            }
            return a;
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
