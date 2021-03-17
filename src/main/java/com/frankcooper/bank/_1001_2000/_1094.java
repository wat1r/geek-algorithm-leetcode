package com.frankcooper.bank._1001_2000;

public class _1094 {


    static _1094 handler = new _1094();

    public static void main(String[] args) {

    }


    public boolean carPooling(int[][] trips, int capacity) {
        int[] pre = new int[1005];
        for (int[] t : trips) {
            pre[t[1]] += t[0];
            pre[t[2]] -= t[0];
        }
        for (int i = 1; i < 1005; i++) {
            pre[i] += pre[i - 1];
            if (pre[i] > capacity) return false;
        }
        return true;
    }
}
