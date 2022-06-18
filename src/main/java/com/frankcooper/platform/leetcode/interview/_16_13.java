package com.frankcooper.platform.leetcode.interview;

public class _16_13 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[] square1 = {-52, 15, 7};
            int[] square2 = {-4, -110, 15};
            double[] expected = new double[]{-50.00413, 22.0, 6.72314, -110.0};
//            Assert.assertArrayEquals(expected,handler.cutSquares(square1,square2));
            double[] actual = handler.cutSquares(square1, square2);

        }

        public double[] cutSquares(int[] square1, int[] square2) {
            //计算两个正方形的中心点 center
            int x1 = square1[0], y1 = square1[1], h1 = square1[2];
            int x2 = square2[0], y2 = square2[1], h2 = square2[2];
            double[] c1 = new double[]{x1 + h1 / 2.0, y1 + h1 / 2.0};
            double[] c2 = new double[]{x2 + h2 / 2.0, y2 + h2 / 2.0};
            double[] res = new double[4];
            //中心点重合 || 直线斜率为无穷大，即与x轴垂直，与y轴平行
            if (c1[0] == c2[0]) {
                res[0] = c1[0];
                res[1] = Math.min(y1, y2);//取两个正方形的最下面的一条边的最小y坐标
                res[2] = c2[0];
                //取两个正方形的最上面的一条边的最大y坐标
                res[3] = Math.max(y1 + h1, y2 + h2);
                return res;
            }
            //斜率
            double k = (c2[1] - c1[1]) / (c2[0] - c1[0]);
            if (Math.abs(k) < 1) {//小于1的情况，与左右的四条边相交
                //left right
                double lx = Math.min(x1, x2);
                double rx = Math.max(x1 + h1, x2 + h2);
                double ly = c1[1] - k * (c1[0] - lx);
                double ry = c1[1] - k * (c1[0] - rx);
                return new double[]{lx, ly, rx, ry};
            } else {//大于1的情况，与上下的四条边相交
                //down up
                double dy = Math.min(y1, y2);
                double uy = Math.max(y1 + h1, y2 + h2);
                double dx = c1[0] - (c1[1] - dy) / k;
                double ux = (uy - c1[1]) / k + c1[0];
                return dx < ux ? new double[]{dx, dy, ux, uy} : new double[]{ux, uy, dx, dy};
            }
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
