package com.frankcooper.bank._701_800;

import java.util.*;

import com.frankcooper.utils.PrintUtils;

public class _762 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int countPrimeSetBits(int left, int right) {
            int res = 0;
            for (int a = left; a <= right; a++) {
                if (isPrime(count(a))) res++;
            }
            return res;
        }


        public int count(int a) {
            int cnt = 0;
            while (a > 0) {
                cnt += a & 1;
                a >>= 1;
            }
            return cnt;
        }


        /**
         * 判断一个数是否是素数
         */
        public boolean isPrime(int a) {
            if (a < 2) return false;
            for (int i = 2; i * i <= a; i++) {
                if (a % i == 0) return false;
            }
            return true;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            PrintUtils.toBinaryString(10_00_000, 20);
            //11110100001001000000
        }


        public int countPrimeSetBits(int left, int right) {
            List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19);
            int cnt = 0;
            for (int i = left; i <= right; i++) {
                int bits = 0;
                for (int x = i; x > 0; x >>= 1) {
                    bits += x & 1;
                }
                if (primes.contains(bits)) cnt++;
            }
            return cnt;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int countPrimeSetBits(int left, int right) {
            List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19);
            int[] f = count(right);
            int cnt = 0;
            for (int i = left; i <= right; i++) {
                if (primes.contains(f[i])) cnt++;
            }
            return cnt;
        }


        public int[] count(int x) {
            if (x == 0) return new int[]{};
            int[] f = new int[x + 1];
            f[0] = 0;
            f[1] = 1;
            for (int i = 2; i <= x; i++) {
                f[i] = f[i >> 1] + f[i & 1];
            }
            return f;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
