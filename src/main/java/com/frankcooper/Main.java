package com.frankcooper;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int low = sc.nextInt();
        int hign = sc.nextInt();
        List<Integer> primes = findPrimes(low, hign);
        //x：个位数，y:十位数
        int x = 0, y = 0;
        for (Integer p : primes) {
            x += p % 10;
            p /= 10;
            y += p % 10;
        }
        System.out.println(Math.min(x, y));

    }

    public static List<Integer> findPrimes(int n, int m) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[m + 1];

        // 初始化所有的数为质数
        for (int i = 2; i <= m; i++) {
            isPrime[i] = true;
        }

        // 埃拉托斯特尼筛法
        for (int i = 2; i * i <= m; i++) {
            if (isPrime[i]) {
                // 如果i是质数，那么它的所有倍数都不是质数
                for (int j = i * i; j <= m; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // 收集范围内的质数
        for (int i = n; i <= m; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }


//    public static List<Integer> findPrimes(int n, int m) {
//        List<Integer> primes = new ArrayList<>();
//        if (n <= 2) {
//            n = 2;
//        }
//        for (int i = n; i <= m; i++) {
//            if (isPrime(i)) {
//                primes.add(i);
//            }
//        }
//        return primes;
//    }
//
//    private static boolean isPrime(int number) {
//        if (number <= 1) {
//            return false;
//        }
//        for (int i = 2; i <= Math.sqrt(number); i++) {
//            if (number % i == 0) {
//                return false;
//            }
//        }
//        return true;
//    }

    public void T1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int x = 0, y = 0;
        while (n > 0) {
            x += n % 10;
            n /= 10;
        }
        while (m > 0) {
            y += m % 10;
            m /= 10;
        }
        System.out.println(x % y);
    }

    public static class T2_2 {

        public static List<Integer> findPrimes(int n, int m) {
            List<Integer> primes = new ArrayList<>();
            boolean[] isPrime = new boolean[m + 1];

            // 初始化所有的数为质数
            for (int i = 2; i <= m; i++) {
                isPrime[i] = true;
            }

            // 埃拉托斯特尼筛法
            for (int i = 2; i * i <= m; i++) {
                if (isPrime[i]) {
                    // 如果i是质数，那么它的所有倍数都不是质数
                    for (int j = i * i; j <= m; j += i) {
                        isPrime[j] = false;
                    }
                }
            }

            // 收集范围内的质数
            for (int i = n; i <= m; i++) {
                if (isPrime[i]) {
                    primes.add(i);
                }
            }

            return primes;
        }


    }


    public static class T2_3 {
        public static void main(String[] args) {
            int m = 20;
            T2_3 handler = new T2_3();
            boolean[] isPrime = handler.isPrime(m);
            for (int i = 2; i <= m; i++) {
                if (isPrime[i]) {
                    System.out.printf("%d ", i);
                }
            }
        }

        public boolean[] isPrime(int m) {
            boolean[] isPrime = new boolean[m + 1];
            for (int i = 2; i <= m; i++) {
                isPrime[i] = true;
            }
            for (int i = 2; i * i <= m; i++) {
                for (int j = i * i; j <= m; j += i) {
                    isPrime[j] = false;
                }
            }
            return isPrime;
        }

    }
}
