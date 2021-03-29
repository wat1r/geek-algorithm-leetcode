package com.frankcooper.bank.week;

import org.junit.Assert;

public class CommonFunction {


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


    /*
    计算 a^b
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

    public static void main(String[] args) {
        CommonFunction handler = new CommonFunction();
        Assert.assertEquals(handler.pow(9, 8), (int) Math.pow(9, 8));
    }


}
