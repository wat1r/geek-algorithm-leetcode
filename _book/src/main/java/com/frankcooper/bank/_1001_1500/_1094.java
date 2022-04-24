package com.frankcooper.bank._1001_1500;

import org.junit.Assert;

public class _1094 {


    static class _1st {
        static _1st handler = new _1st();

        public static void main(String[] args) {
            int[][] trips = {{2, 1, 5}, {3, 3, 7}};
            int capacity = 4;
//            handler.carPooling(trips, capacity);
            trips = new int[][]{{9, 0, 1}, {3, 3, 7}};
            capacity = 4;
            Assert.assertFalse(handler.carPooling(trips, capacity));
        }


        public boolean carPooling(int[][] trips, int capacity) {
            int[] pre = new int[1005];
            for (int[] t : trips) {
                pre[t[1]] += t[0];
                pre[t[2]] -= t[0];
            }
            for (int i = 0; i < 1004; i++) {
                if (pre[i] > capacity) return false;
                pre[i + 1] += pre[i];
            }
            return true;
        }
    }

    static class _2nd {
        public boolean carPooling(int[][] trips, int capacity) {
            int n = 1010;
            int[] d = new int[n];
            for (int[] t : trips) {
                d[t[1]] += t[0];
                d[t[2]] -= t[0];
            }
            for (int i = 0; i < n - 1; i++) {
                if (d[i] > capacity) return false;
                d[i + 1] += d[i];
            }
            return true;
        }
    }
}
