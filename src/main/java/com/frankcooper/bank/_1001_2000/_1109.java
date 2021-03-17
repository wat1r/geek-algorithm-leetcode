package com.frankcooper.bank._1001_2000;

import com.frankcooper.swordoffer.utils.PrintUtils;

public class _1109 {
    static _1109 handler = new _1109();

    public static void main(String[] args) {
        int[][] bookings = PrintUtils.processSymbol("[[1,2,10],[2,3,20],[2,5,25]]");
        int n = 5;
        handler.corpFlightBookings(bookings, n);
    }


    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] pre = new int[n];
        for (int[] b : bookings) {
            pre[b[0] - 1] += b[2];
            if (b[1] < n) {
                pre[b[1]] -= b[2];
            }
        }
        for (int i = 1; i < n; i++) {
            pre[i] += pre[i - 1];
        }
        return pre;

    }

}
