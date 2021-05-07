package com.frankcooper.bank._1501_2000;

public class _1734 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int[] decode(int[] encoded) {
            int n = encoded.length + 1;
            int[] perm = new int[n];
            int a = 0;
            for (int i = 1; i <= n; i++) a ^= i;
            int b = 0;
            for (int i = 1; i < encoded.length; i += 2) b ^= encoded[i];
            perm[0] = a ^ b;
            for (int i = 1; i < perm.length; i++) {
                perm[i] = encoded[i - 1] ^ perm[i - 1];
            }
            return perm;
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
