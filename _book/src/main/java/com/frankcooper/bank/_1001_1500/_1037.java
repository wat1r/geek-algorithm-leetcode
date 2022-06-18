package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1037 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public boolean isBoomerang(int[][] points) {
            int x0 = points[0][0], y0 = points[0][1];
            int x1 = points[1][0], y1 = points[1][1];
            int x2 = points[2][0], y2 = points[2][1];
            return !((y1 - y0) * (x2 - x0) == (y2 - y0) * (x1 - x0));
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
