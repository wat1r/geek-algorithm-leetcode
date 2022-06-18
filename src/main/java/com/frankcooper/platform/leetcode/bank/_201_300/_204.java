package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

public class _204 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.Eratosthenes(10);
            handler.Eratosthenes(2);
        }

        public int countPrimes(int n) {
            int ans = 0;
            for (int i = 1; i < n; i++) {
                if (isPrime(i)) ans++;
            }
            return ans;
        }

        public boolean isPrime(int a) {
            if (a < 2) return false;
            for (int i = 2; i * i <= a; i++) {
                if (a % i == 0) return false;
            }
            return true;
        }


        /**
         * 小于n的质数(素数) 的个数
         *
         * @param n
         * @return
         */
        public int Eratosthenes(int n) {
            if (n <= 1) return 0;
            int[] isPrime = new int[n];
            Arrays.fill(isPrime, 1);
            isPrime[0] = isPrime[1] = 0;//
            int p = 0;
            for (int i = 2; i < n; i++) {
                if (isPrime[i] == 1) {
                    isPrime[p++] = i;// prime[p]是i,后置自增运算代表当前素数数量
                    if ((long) i * i < n) {
                        // 因为从 2 到 i - 1 的倍数我们之前筛过了，这里直接从 i的倍数开始，提高了运行速度
                        for (int j = i * i; j < n; j += i) {
                            isPrime[j] = 0;// 是i的倍数的均不是素数
                        }
                    }
                }
            }
            return p;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.countPrimes(16);
        }


        /**
         * 线性筛
         *
         * @param n
         * @return
         */
        public int countPrimes(int n) {
            List<Integer> primes = new ArrayList<Integer>();
            int[] isPrime = new int[n];
            Arrays.fill(isPrime, 1);
            for (int i = 2; i < n; ++i) {
                System.out.printf("i:%d\n", i);
                if (isPrime[i] == 1) {
                    primes.add(i);
                }
                for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                    System.out.printf("   j:%d--->%d\n", j, i * primes.get(j));
                    isPrime[i * primes.get(j)] = 0;
                    if (i % primes.get(j) == 0) {
                        break;
                    }
                }
                primes.forEach(f -> System.out.printf("%d ", f));
                System.out.printf("\n");
            }
            return primes.size();
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
//            handler.countPrimes(10);
            handler.countPrimes(499979);
        }

        public int countPrimes(int n) {
            int[] f = new int[n];
            Arrays.fill(f, 1);//1表示是质数，0表示不是质数
            f[0] = f[1] = 0;
            int res = 0;
            for (int i = 2; i < n; i++) {
                if (f[i] == 1) {
                    res++;
                    for (int j = i * i; j < n; j += i) {
                        System.out.printf("%d\n", j);
                        f[j] = 0;
                    }
                }
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int countPrimes(int n) {
            int res = 0;
            for (int i = 2; i < n; i++) {
                if (isPrime(i)) res++;
            }
            return res;
        }

        private boolean isPrime(int num) {
            int N = (int) Math.sqrt(num);
            for (int i = 2; i <= N; i++) {
                if (num % i == 0) return false;
            }
            return true;
        }
    }

    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            handler.countPrimes(16);
        }

        public int countPrimes(int n) {
            if (n <= 1) return 0;
            int[] prime = new int[n];
            Arrays.fill(prime, 1);
            prime[0] = prime[1] = 0;
            int p = 0;
            for (int i = 2; i < n; i++) {
                if (prime[i] == 1) {
                    prime[p++] = i;
                    if ((long) i * i < n) {
                        // 因为从 2 到 i - 1 的倍数我们之前筛过了，这里直接从 i的倍数开始，提高了运行速度
                        for (int j = i * i; j < n; j += i) {
                            prime[j] = 0;// 是i的倍数的均不是素数
                        }
                    }
                }
            }
            return p;
        }

    }
}
