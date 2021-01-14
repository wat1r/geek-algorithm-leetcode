package com.frankcooper.bank;

import java.util.ArrayList;
import java.util.List;

public class _1018 {

    static _1018 handler = new _1018();

    public static void main(String[] args) {
        handler.prefixesDivBy5(new int[]{0, 1, 1});
    }


    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> res = new ArrayList<>();
        int prev = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) prev += A[i] << 1;
            int curr = prev;
            res.add((curr % 5) == 0);
        }
        return res;
    }

}
