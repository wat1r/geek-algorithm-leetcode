package com.frankcooper.acwing;

import java.util.*;

import org.junit.Assert;

public class _790 {

    static class _1st {
        public static void main(String[] args) {

            Scanner in = new Scanner(System.in);
            double n = in.nextDouble();
            double l = -1000.0, r = 10000;
            while ((r - l) > Math.pow(10, -8)) {
                double mid = (l + r) / 2;
                if (mid * mid * mid > n) r = mid;
                else l = mid;
            }
            System.out.printf("%.6f", l);
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
