package com.frankcooper.platform.leetcode.interview;

public class _05_07 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int exchangeBits(int num) {
            int odd = num & 0x55555555;
            odd <<= 1;
            int even = num & 0xaaaaaaaa;
            even >>= 1;
            return odd | even;
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
