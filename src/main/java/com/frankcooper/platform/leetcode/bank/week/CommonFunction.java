package com.frankcooper.platform.leetcode.bank.week;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class
CommonFunction {

    public static void main(String[] args) {
        CommonFunction handler = new CommonFunction();
//        Assert.assertEquals(handler.pow(9, 8), (int) Math.pow(9, 8));
//        Assert.assertTrue(handler.isPrime(7));
        handler.Eratosthenes(10);
    }


    /**
     * Greatest Common Divisor(GCD)
     * 最大公约数
     * 最大公因数，也称最大公约数、最大公因子，指两个或多个整数共有约数中最大的一个。a，b的最大公约数记为（a，b）
     *
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * 更相减损法 求GCD
     *
     * @param a
     * @param b
     * @return
     */
    public int gcd1(int a, int b) {
        while (true) {
            if (a > b) a -= b;
            else if (a < b) b -= a;
            else return a;
        }
    }


    public long MOD(long a, long m) {
        a %= m;
        if (a < 0) a += m;
        return a;
    }


    public long inverse(long a, long m) {
        a = MOD(a, m);
        if (a <= 1) return a;
        return MOD((1 - inverse(m, a) * m) / a, m);
    }

    /**
     * 计算 a^b  模上MOD
     */
    public long pow1(long a, long b, long mod) {
        a %= mod;
        if (b < 0) {
            a = inverse(a, mod);
            b = -b;
        }
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) ans = ans * a % mod;
            a = a * a % mod;
            b /= 2;
        }
        return ans % mod;
    }


    /**
     * 计算 a^b 快速幂
     */
    public long pow(long a, long b) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) ans *= a;
            a *= a;
            b /= 2;
        }
        return ans;
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


    /**
     * 小于n的质数(素数) 的个数 -> 埃拉托斯特尼筛法
     */
    public int Eratosthenes(int n) {
        if (n <= 1) return 0;
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        isPrime[0] = isPrime[1] = 0;
        int p = 0;
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i] == 1) {
                isPrime[p++] = i;// prime[p]是i,后置自增运算代表当前素数数量
                if ((long) i * i < n) {
                    // 因为从 2 到 i - 1 的倍数我们之前筛过了，这里直接从 i
                    // 的倍数开始，提高了运行速度
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;// 是i的倍数的均不是素数
                    }
                }
            }
        }
        return p;
    }


    /**
     * 从 平方根开始筛 小于n的质数
     */
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int ans = 0;
        for (boolean b : isPrime) {
            if (b) ans++;
        }
        return ans;
    }


    /**
     * 线性筛
     *
     * @param n
     * @return
     */
    public int countPrimesLine(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
//            System.out.printf("i:%d\n", i);
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
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

    //埃拉托斯特尼筛法 查找 n和m之间的所有质数
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

    int MOD = (int) 1e9 + 7;

    private long factorial(long n) {
        if (n == 0) return 1;
        return n * factorial(n - 1) % MOD;
    }

    //求 (x^a) % p —— 快速幂求余
    public int reminder(int x, int a, int p) {
        int rem = 1;
        while (a > 0) {
            if ((a & 1) == 1) rem = (rem * x) % p;
            x = x * x % p;
            a /= 2;
        }
        return rem;
    }

    //    函数 upper_bound() 在 [begin, end) 进行二分查找，返回 大于 tar的第一个元素位置。如果所有元素都小于tar，则返回 end.
    public static int upper_bound(int[] arr, int begin, int end, int tar) {
        while (begin < end) {
            int mid = begin + (end - begin) / 2;
            if (arr[mid] <= tar)
                begin = mid + 1;
            else if (arr[mid] > tar)
                end = mid;
        }
        return begin;
    }


    //函数 lower_bound() 在 [begin, end) 进行二分查找，返回 大于或等于 tar的第一个元素位置。如果所有元素都小于tar，则返回 end.
    public static int lower_bound(int[] arr, int begin, int end, int tar) {
        while (begin < end) {
            int mid = begin + (end - begin) / 2;
            // 当 mid 的元素小于 tar 时
            if (arr[mid] < tar)
                // begin 为 mid + 1, arr[begin] 的值会小于或等于 tar
                begin = mid + 1;
                // 当 mid 的元素大于等于 tar 时
            else if (arr[mid] >= tar)
                end = mid;
        }
        return begin;
    }


}
