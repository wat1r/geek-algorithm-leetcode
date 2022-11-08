package com.frankcooper.platform.acwing;

import java.util.*;

import org.junit.Assert;

public class _4268 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        private static boolean isPrime(int x) {
            if (x < 2) return false;
            for (int i = 2; i <= x / i; i++) {
                if (x % i == 0) return false;
            }
            return true;
        }

        private static void process(int N) {
            for (int i = N - 6; i <= N + 6; i += 12) {
                if (isPrime(i) && isPrime(N)) {
                    System.out.println("Yes\n" + i);
                    return;
                }
            }
            for (int i = N + 1; ; i++) {
                if (isPrime(i) && (isPrime(i - 6) || isPrime(i + 6))) {
                    System.out.println("No\n" + i);
                    return;
                }
            }
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
