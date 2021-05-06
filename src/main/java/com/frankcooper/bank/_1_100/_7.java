package com.frankcooper.bank._1_100;

import java.util.*;

import org.junit.Assert;

public class _7 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.reverse(123);
            handler.reverse(1534236469);
        }

        /**
         * 1534236469
         *
         * @param x
         * @return
         */
        public int reverse(int x) {
            int sign = 1;
            if (x < 0) {
                sign = -1;
                x = -x;
            }
            long res = 0;
            int bit = 0;
            while (x > 0) {
                res = res * 10 + x % 10;
                x /= 10;
                if (res >= Integer.MAX_VALUE) return 0;
            }
            return (int) res * sign;
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
