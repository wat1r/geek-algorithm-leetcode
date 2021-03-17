package com.frankcooper.bank._501_1000;

public class _509 {


    public int fib(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;
        if (n == 0) return f[n];
        f[1] = 1;
        if (n == 1) return f[n];
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

}
