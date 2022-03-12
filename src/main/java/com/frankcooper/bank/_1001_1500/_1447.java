package com.frankcooper.bank._1001_1500;

import java.util.ArrayList;
import java.util.List;

public class _1447 {

    public List<String> simplifiedFractions(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(i, j) == 1) {
                    res.add(j + "/" + i);
                }
            }
        }
        return res;

    }


    //    public int gcd(int a, int b) {
//        int big = Math.max(a, b), smaller = Math.min(a, b);
//        return big % smaller == 0 ? smaller : gcd(big % smaller, smaller);
//    }
    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    static class _2nd{


    }
}
