package com.frankcooper.bank._601_700;

public class _605 {


    public boolean canPlaceFlowers(int[] A, int n) {

        int N = A.length;
        int prev, next, ans = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] == 1) continue;
            prev = i == 0 ? 0 : A[i - 1];
            next = i == N - 1 ? 0 : A[i + 1];
            if (prev == 0 && next == 0) {
                A[i] = 1;
                ans++;
            }
        }
        return ans >= n;
    }


}
