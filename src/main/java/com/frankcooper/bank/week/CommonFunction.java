package com.frankcooper.bank.week;

public class CommonFunction {


    /**
     * 最大公约数
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
    public long pow(long a, long b, long mod) {
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

}
