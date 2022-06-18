package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1175 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.numPrimeArrangements(100);
            handler.numPrimeArrangements(2);
            Assert.assertEquals(handler.numPrimeArrangements(100), 682289015);

        }


        int MOD = (int) 1e9 + 7;

        public int numPrimeArrangements(int n) {
            int prime = countPrimesLine(n);
            return (int) (factorial(prime) % MOD * factorial(n - prime) % MOD);
        }


        private long factorial(long n) {
            if (n == 0) return 1;
            return n * factorial(n - 1) % MOD;
        }


        public int countPrimesLine(int n) {//注意小于等于n
            List<Integer> primes = new ArrayList<Integer>();
            int[] isPrime = new int[n + 1];
            Arrays.fill(isPrime, 1);
            for (int i = 2; i <= n; ++i) {
//            System.out.printf("i:%d\n", i);
                if (isPrime[i] == 1) {
                    primes.add(i);
                }
                for (int j = 0; j < primes.size() && i * primes.get(j) <= n; ++j) {
//                System.out.printf("   j:%d--->%d\n", j, i * primes.get(j));
                    isPrime[i * primes.get(j)] = 0;
                    if (i % primes.get(j) == 0) {
                        break;
                    }
                }
//            primes.forEach(f -> System.out.printf("%d ", f));
//            System.out.printf("\n");
            }
            return primes.size();
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
