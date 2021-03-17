package com.frankcooper.bank._501_1000;

import java.util.HashSet;

public class _888 {


    public int[] fairCandySwap(int[] A, int[] B) {


        int sumA = 0, sumB = 0;
        for (int a : A) sumA += a;
        for (int b : B) sumB += b;
        int delta = (sumA - sumB) / 2;
        HashSet<Integer> set = new HashSet<>();
        for (int a : A) set.add(a);
        int[] res = new int[2];
        for (int b : B) {
            int a = b + delta;
            if (set.contains(a)) {
                res[0] = a;
                res[1] = b;
                return res;
            }
        }
        return res;

    }


}
