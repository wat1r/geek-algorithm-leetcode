package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.Random;

public class _478 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        class Solution {

            double sx, sy;
            double sr;

            public Solution(double radius, double x_center, double y_center) {
                sx = x_center;
                sy = y_center;
                sr = radius;
            }

            public double[] randPoint() {
                Random random = new Random();
                while (true) {
                    double tx = random.nextDouble() * 2 * sr - sr, ty = random.nextDouble() * 2 * sr - sr;
                    if (tx * tx + ty * ty <= sr * sr) {
                        return new double[]{sx + tx, sy + ty};
                    }
                }
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class Solution {

            double sx, sy;
            double sr;


            public Solution(double radius, double x_center, double y_center) {
                sx = x_center;
                sy = y_center;
                sr = radius;
            }

            public double[] randPoint() {
                Random random = new Random();
                double l = Math.sqrt(random.nextDouble()) * sr;
                double d = random.nextDouble() * 2 * Math.PI;
                double tx = sx + l * Math.cos(d);
                double ty = sy + l * Math.sin(d);
                return new double[]{tx, ty};
            }
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
