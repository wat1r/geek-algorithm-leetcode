package com.frankcooper.platform.leetcode.interview;

public class _08_01 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.waysToStep(61);
        }


        public int waysToStep(int n) {
            int MOD = (int) 1e9 + 7;
            long[] f = new long[n + 1];
            f[0] = 0;
            f[1] = 1;
            if (n == 1) return (int) f[1];
            f[2] = 2;
            if (n == 2) return (int) f[2];
            f[3] = 4;
            if (n == 3) return (int) f[3];
            for (int i = 4; i <= n; i++) {
                f[i] = (f[i - 1] + f[i - 2] + f[i - 3]) % MOD;
            }
            return (int) f[n] % MOD;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int waysToStep(int n) {
            if (n <= 2) return n;
            int MOD = (int) 1e9 + 7;
            int[] f = new int[n + 1];
            f[1] = 1;
            f[2] = 2;
            f[3] = 4;
            for (int i = 4; i <= n; i++) {
                f[i] = (f[i - 1] + (f[i - 2] + f[i - 3]) % MOD) % MOD;
            }
            return f[n];
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.waysToStep(5);
        }

        public int waysToStep(int n) {
            if (n <= 2) return n;
            int MOD = (int) 1e9 + 7;
            int a = 4, b = 2, c = 1;
            for (int i = 4; i <= n; i++) {
                int ta = a, tb = b;
                a = (ta + (b + c) % MOD) % MOD;
                b = ta;
                c = tb;
            }
            return a;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
