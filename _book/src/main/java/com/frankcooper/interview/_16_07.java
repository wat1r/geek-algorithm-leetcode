package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _16_07 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int maximum(int a, int b) {
            long x = (long) a - (long) b;
            int sign = (int) (x >> 63);
            int res = (1 + sign) * a - sign * b;
            return res;
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
