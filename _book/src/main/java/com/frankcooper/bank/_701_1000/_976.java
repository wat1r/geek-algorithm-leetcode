package com.frankcooper.bank._901_1000;

import java.util.Arrays;

public class _976 {

    public static void main(String[] args) {

    }


    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int n = A.length;
        for (int i = n - 1; i >= 2; i--) {
            if (A[i - 2] + A[i - 1] > A[i]) return A[i - 2] + A[i - 1] + A[i];
        }
        return 0;
    }
}
