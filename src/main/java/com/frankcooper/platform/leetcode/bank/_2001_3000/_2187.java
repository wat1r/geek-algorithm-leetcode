package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2187 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] time = {1, 2, 3};
            int totalTrops = 5;
            time = new int[]{2};
            totalTrops = 1;
//            handler.minimumTime(time, totalTrops);
            time = new int[]{5, 10, 10};
            totalTrops = 9;
//            Assert.assertEquals(25, handler.minimumTime(time, totalTrops));
            time = new int[]{9, 2};
            totalTrops = 8;
            Assert.assertEquals(14, handler.minimumTime(time, totalTrops));
        }

        public long minimumTime(int[] time, int totalTrips) {
            Arrays.sort(time);
//            long l = 0, r = (int) 10e7 + 5;
//            long l = 0, r = Long.MAX_VALUE;
            //long类型强转
            long l = 0, r = (long) totalTrips * time[time.length - 1];
            for (int x : time) r += x;
            r = Math.max(r, totalTrips);
            while (l < r) {
                long mid = l + (r - l) / 2;//下取整
                long cnt = check(time, mid);
                if (cnt < totalTrips) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return r;
        }



        //返回t这个时刻，能完成的趟数
        public long check(int[] time, long t) {
            long cnt = 0;
            for (int x : time) {
                if (t < x) break;
                cnt += t / x;
            }
            return cnt;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
