package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2226 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] candies = new int[]{4, 7, 5};
            int k = 4;
//            Assert.assertEquals(3, handler.maximumCandies(candies, k));
            candies = new int[]{1, 2, 3, 4, 10};
            k = 5;
            Assert.assertEquals(3, handler.maximumCandies(candies, k));

        }

        public int maximumCandies(int[] candies, long k) {
            long sum = 0;
            for (int x : candies) sum += x;
            if (sum < k) {
                return 0;
            }
            Arrays.sort(candies);
            int l = 0, r = (int) 1e9 + 5;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (check(candies, mid) < k) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            return l;
        }

        //能分到t个糖果的小孩的最大数量
        public long check(int[] candies, int t) {
            long cnt = 0;
            for (int x : candies) {
                if (x >= t) cnt += x / t;
            }
//            System.out.println(String.format("mid:%d->cnt:%d", t, cnt));
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
