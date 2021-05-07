package com.frankcooper.bank._1001_1500;

import java.util.ArrayList;
import java.util.List;

public class _1018 {

    static _1018 handler = new _1018();

    public static void main(String[] args) {
//        handler.prefixesDivBy5(new int[]{0, 1, 1});
//        handler.prefixesDivBy5(new int[]{0, 1, 1, 1, 1, 1});
//        handler.prefixesDivBy5(new int[]{1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1});
        handler.prefixesDivBy5(new int[]{1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0});
        ;
    }


    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> res = new ArrayList<>();
        int prev = 0;
        for (int i = 0; i < A.length; i++) {
            int curr = (A[i] + (prev << 1)) % 5;
            System.out.printf("%d\n", curr);
            res.add((curr % 5) == 0);
            prev = curr;
        }
        return res;
    }

}
