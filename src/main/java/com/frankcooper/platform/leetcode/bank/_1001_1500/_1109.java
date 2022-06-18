package com.frankcooper.platform.leetcode.bank._1001_1500;

import com.frankcooper.utils.PrintUtils;

public class _1109 {
    static class _1st {
        static _1st handler = new _1st();

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

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
            int n = 5;
            handler.corpFlightBookings(bookings, n);
        }

        public int[] corpFlightBookings(int[][] bookings, int n) {
            //差分数组
            int[] d = new int[n + 1];
            for (int[] b : bookings) {
                int l = b[0] - 1, r = b[1] - 1, v = b[2];
                d[l] += v;
                d[r + 1] -= v;
            }
            int[] res = new int[n];
            res[0] = d[0];
            for (int i = 1; i < n; i++) res[i] = res[i - 1] + d[i];
            return res;
        }


    }

}
