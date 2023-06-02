package com.frankcooper.platform.leetcode.bank._2001_3000;

import org.junit.Assert;

import java.util.Arrays;

public class _2064 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int n = 6;
            int[] qs = {11, 6};
//            Assert.assertEquals(3, handler.minimizedMaximum(n, qs));
            n = 7;
            qs = new int[]{15, 10, 10};
            Assert.assertEquals(5, handler.minimizedMaximum(n, qs));


        }

        public int minimizedMaximum(int n, int[] qs) {
            Arrays.sort(qs);
            int l = 1, r = qs[qs.length - 1];
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (check(n, qs, mid)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }


        public boolean check(int n, int[] qs, int t) {
            int cnt = 0;
            for (int x : qs) {
                cnt += x / t + (x % t == 0 ? 0 : 1);
            }
            return n >= cnt;//以t来分x，能分成多少份，返回是否够n个人分
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int n = 6;
            int[] qs = {11, 6};
//            Assert.assertEquals(3, handler.minimizedMaximum(n, qs));
            n = 7;
            qs = new int[]{15, 10, 10};
            Assert.assertEquals(5, handler.minimizedMaximum(n, qs));
        }

        public int minimizedMaximum(int n, int[] qs) {
            Arrays.sort(qs);
            int l = 1, r = qs[qs.length - 1];
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (check(n, qs, mid)) {//true时 mid的数量 够n分
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            return l;
        }


        public boolean check(int n, int[] qs, int t) {
            int cnt = 0;
            for (int x : qs) {
                cnt += x / t + (x % t == 0 ? 0 : 1);
            }
            return cnt > n;//以t来分x，能分成多少份，返回是否够n个人分
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
