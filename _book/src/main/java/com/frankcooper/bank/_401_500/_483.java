package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _483 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        /**
         * class Solution {
         *     public String smallestGoodBase(String n) {
         *         long m = Long.parseLong(n);
         *         int max = (int)(Math.log(m) / Math.log(2) + 1);
         *         for (int len = max; len >= 3; len--) {
         *             long k = (long)Math.pow(m, 1.0 / (len - 1));
         *             long res = 0;
         *             for (int i = 0; i < len; i++) res = res * k + 1;
         *             if (res == m) return String.valueOf(k);
         *         }
         *         return String.valueOf(m - 1);
         *     }
         * }
         *
         * @param n
         * @return
         */


        public String smallestGoodBase(String n) {
            long m = Long.parseLong(n);
            int max = (int) (Math.log(m) / Math.log(2) + 1);
            for (int i = max; i >= 3; --i) {
                long k = (long) Math.pow(m, 1.0 / (i - 1));
                long res = 0;
                for (int j = 0; j < i; j++) res = res * k + 1;
                if (res == m) return String.valueOf(k);
            }
            return String.valueOf(m - 1);
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
