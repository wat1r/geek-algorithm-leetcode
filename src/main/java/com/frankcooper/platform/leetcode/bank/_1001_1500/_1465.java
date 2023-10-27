package com.frankcooper.platform.leetcode.bank._1001_1500;

import org.junit.Assert;

import java.util.Arrays;

/*import java.util.*;
import org.junit.Assert;*/
public class _1465 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int h = 5, w = 4;
            int[] hc = new int[]{1, 2, 4};
            int[] vc = new int[]{1, 3};
//            Assert.assertEquals(4, handler.maxArea(h, w, hc, vc));
            h = 1000000000;
            w = 1000000000;
            hc = new int[]{2};
            vc = new int[]{2};
            Assert.assertEquals(81, handler.maxArea(h, w, hc, vc));

        }


        public int maxArea(int h, int w, int[] hc, int[] vc) {
            int MOD = (int) 1e9 + 7;
            int hd = getMax(h, hc);
            int vd = getMax(w, vc);
            //先将hd转成long
            return (int) ((long) hd * vd % MOD);
        }

        private int getMax(int t, int[] c) {
            Arrays.sort(c);
            int d = Math.max(c[0], t - c[c.length - 1]);
            for (int i = 1; i < c.length; i++) {
                d = Math.max(d, c[i] - c[i - 1]);
            }
            return d;
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
