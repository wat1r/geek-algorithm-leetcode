package com.frankcooper.platform.leetcode.bank._1001_1500;

public class _1317 {

    public int[] getNoZeroIntegers(int n) {
        int[] res = new int[2];
        for (int i = 1; i < n; i++) {
            int remian = n - i;
            if (!String.valueOf(i).contains("0") && !String.valueOf(remian).contains("0")) {
                res[0] = i;
                res[1] = remian;
                return res;
            }
        }
        return res;
    }
}
