package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _16_06 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[] a = {1, 3, 15, 11, 2};
            int[] b = {23, 127, 235, 19, 8};
//            Assert.assertEquals(3, handler.smallestDifference(a, b));
            a = new int[]{-2147483648, 1};
            b = new int[]{2147483647, 0};
            Assert.assertEquals(1, handler.smallestDifference(a, b));

        }


        public int smallestDifference(int[] a, int[] b) {
            Arrays.sort(a);
            Arrays.sort(b);
            System.out.printf("%s, %s \n", Arrays.toString(a), Arrays.toString(b));
            int i = 0, j = 0, m = a.length, n = b.length;
            long res = Integer.MAX_VALUE;
            while (i < m && j < n) {
                res = Math.min(res, Math.abs((long) a[i] - (long) b[j]));
                if (a[i] < b[j]) i++;
                else if (a[i] > b[j]) j++;
                else return 0;
            }

            return (int) res;
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
