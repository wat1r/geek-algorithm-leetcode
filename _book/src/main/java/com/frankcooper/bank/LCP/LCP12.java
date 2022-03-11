package com.frankcooper.bank.LCP;

import java.util.*;

import org.junit.Assert;

public class LCP12 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] time = {1, 2, 2, 4, 3, 6, 1};
            int limit = 4;
//            handler.minTime(time, limit);
        }

      /*  boolean Check(int limit, int[] cost, int day) {
            // 每组划分 limit 的最大和，贪心划分看有多少组
            int useday, totaltime, maxtime;
            useday = 1;
            totaltime = maxtime = 0;
            for (int i = 0; i < cost.length; ++i) {
                int nexttime = Math.min(maxtime, cost[i]);
                if (nexttime + totaltime <= limit) {
                    totaltime += nexttime;
                    maxtime = Math.max(maxtime, cost[i]);
                } else {
                    ++useday;
                    totaltime = 0;
                    maxtime = cost[i];
                }
            }
            return (useday <= day);
        }

        int minTime(int[] time, int m) {
            int left, right, middle;
            left = right = 0;

            for (int i = 0; i < time.length; ++i) {
                right += time[i];
            }
            while (left <= right) {
                middle = (left + right) >> 1;
                if (Check(middle, time, m)) right = middle - 1;
                else left = middle + 1;
            }
            return left;
        }*/

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
