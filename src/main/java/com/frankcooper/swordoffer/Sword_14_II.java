package com.frankcooper.swordoffer;

import java.util.*;

import org.junit.Assert;

public class Sword_14_II {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.cuttingRope(120);

        }

        int MOD = (int) 1e9 + 7;

        public int cuttingRope(int n) {
            long[] f = new long[n + 1];
//            f[0] = f[1] = 0;
            for (int i = 2; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    f[i] = Math.max(f[i], (1L * j * Math.max(i - j, f[i - j])) % MOD);
                }
            }
            return (int) (f[n] % MOD);
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
