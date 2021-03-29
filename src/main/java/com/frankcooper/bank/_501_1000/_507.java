package com.frankcooper.bank._501_1000;

import java.util.*;

import org.junit.Assert;

public class _507 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public boolean checkPerfectNumber(int num) {
            int res = 0, sum = 0;
            for (int i = 1; i * i < num; i++) {
                if (num % i == 0) {
                    sum += i;
                    if (i * i != num) sum += num / i;
                }
                if (sum > num) break;
            }
            return sum == num;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            Assert.assertFalse(handler.checkPerfectNumber(2096128));
        }


        private int pn(int p) {
            int res = (1 << (p - 1)) * ((1 << p) - 1);
            System.out.printf("%d--->%d\n", p, res);
            return res;
        }


        public boolean checkPerfectNumber(int num) {
//            int[] primes = new int[]{2,3, 5, 7, 13, 17, 19, 31, 61, 89, 107};//梅森数
            int[] primes = new int[]{2, 3, 5, 7, 13, 17, 19, 31};//梅森数
            for (int p : primes) {
                if (pn(p) == num) {
                    return true;
                }
            }
            return false;
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
