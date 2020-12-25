package com.frankcooper.bank;

public class _1510 {

    public boolean winnerSquareGame(int n) {
        boolean[] f = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k * k <= i; k++) {
                if (!f[i - k * k]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}

