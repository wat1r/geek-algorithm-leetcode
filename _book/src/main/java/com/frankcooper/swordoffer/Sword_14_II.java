package com.frankcooper.swordoffer;

import java.util.*;

import org.junit.Assert;

public class Sword_14_II {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.cuttingRope(120);
            handler.cuttingRope(10);

        }

        public int cuttingRope(int n) {
            if (n <= 3) return n - 1;
            int b = n % 3, p = (int) 1e9 + 7;
            long rem = 1, x = 3;
            for (int a = n / 3 - 1; a > 0; a /= 2) {
                if (a % 2 == 1) rem = (rem * x) % p;
                x = (x * x) % p;
            }
            if (b == 0) return (int) (rem * 3 % p);
            if (b == 1) return (int) (rem * 4 % p);
            return (int) (rem * 6 % p);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int cuttingRope(int n) {
            if (n <= 3) return n - 1;
            long b = n % 3, p = (int) 1e9 + 7;
            long rem = 1, x = 3, a = n / 3 - 1;
            while (a > 0) {
                if ((a & 1) == 1) rem = (rem * x) % p;
                x = x * x % p;
                a /= 2;
            }
            if (b == 0) return (int) (rem * 3 % p);
            if (b == 1) return (int) (rem * 4 % p);
            return (int) (rem * 6 % p);
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            Assert.assertEquals(965709895, handler.cuttingRope(999));
        }


        public int cuttingRope(int n) {
            int MOD = (int) 1e9 + 7;
            if (n <= 3) return n - 1;
            int b = n % 3;
            long remain = 1, x = 3;
            for (int a = n / 3 - 1; a > 0; a /= 2) {
                if (a % 2 == 1) remain = (remain * x) % MOD;
                x = (x * x) % MOD;
            }
            if (b == 0) return (int) (remain * 3 % MOD);
            if (b == 1) return (int) (remain * 4 % MOD);
            return (int) (remain * 6 % MOD);
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
