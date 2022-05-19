package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1288 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int removeCoveredIntervals(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> {
                if (a[0] == b[0]) return b[1] - a[1];
                return a[0] - b[0];
            });
            int prevEnd = 0, curEnd = 0;
            int res = 0;
            for (int[] t : intervals) {
                curEnd = t[1];
                if (curEnd > prevEnd) {
                    res++;
                    prevEnd = curEnd;
                }
            }
            return res;
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
