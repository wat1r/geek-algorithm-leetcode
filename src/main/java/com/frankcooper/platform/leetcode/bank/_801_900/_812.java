package com.frankcooper.platform.leetcode.bank._801_900;

public class _812 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] p = {{35, -23}, {-12, -48}, {-34, -40}, {21, -25}, {-35, -44}, {24, 1}, {16, -9}, {41, 4}, {-36, -49}, {42, -49}, {-37, -20}, {-35, 11}, {-2, -36}, {18, 21}, {18, 8}, {-24, 14}, {-23, -11}, {-8, 44}, {-19, -3}, {0, -10}, {-21, -4}, {23, 18}, {20, 11}, {-42, 24}, {6, -19}};
            handler.largestTriangleArea(p);

        }

        public double largestTriangleArea(int[][] points) {
            int n = points.length;
            double maxx = 0.0;
            for (int i = 0; i < n; i++) {
                int x1 = points[i][0], y1 = points[i][1];
                for (int j = i + 1; j < n; j++) {
                    int x2 = points[j][0], y2 = points[j][1];
                    double a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
                    for (int k = j + 1; k < n; k++) {
                        int x3 = points[k][0], y3 = points[k][1];
                        double b = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
                        double c = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
                        double s = (a + b + c) * 0.5;
                        //使用绝对值
                        //case：{{35, -23}, {-12, -48}, {-34, -40}, {21, -25}, {-35, -44}, {24, 1}, {16, -9}, {41, 4}, {-36, -49}, {42, -49}, {-37, -20}, {-35, 11}, {-2, -36}, {18, 21}, {18, 8}, {-24, 14}, {-23, -11}, {-8, 44}, {-19, -3}, {0, -10}, {-21, -4}, {23, 18}, {20, 11}, {-42, 24}, {6, -19}};
                        double t = Math.sqrt(s * Math.abs((s - a)) * Math.abs((s - b)) * Math.abs((s - c)));
                        maxx = Math.max(maxx, t);
                    }
                }
            }
            return maxx;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public double largestTriangleArea(int[][] points) {
            int n = points.length;
            double maxx = 0.0;
            for (int i = 0; i < n; i++) {
                int x1 = points[i][0], y1 = points[i][1];
                for (int j = i + 1; j < n; j++) {
                    int x2 = points[j][0], y2 = points[j][1];
                    for (int k = j + 1; k < n; k++) {
                        int x3 = points[k][0], y3 = points[k][1];
                        double t = 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - y1 * x2 - y2 * x3 - y3 * x1);
                        maxx = Math.max(maxx, t);
                    }
                }
            }
            return maxx;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[][] p = {{2, 5}, {7, 7}, {10, 8}, {10, 10}, {1, 1}};
            handler.largestTriangleArea(p);
        }

        //精度问题
        //case:{{2, 5}, {7, 7}, {10, 8}, {10, 10}, {1, 1}}
        public double largestTriangleArea(int[][] points) {
            int n = points.length;
            double maxx = 0.0;
            for (int i = 0; i < n; i++) {
                int x1 = points[i][0], y1 = points[i][1];
                for (int j = i + 1; j < n; j++) {
                    int x2 = points[j][0], y2 = points[j][1];
                    double a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
                    for (int k = j + 1; k < n; k++) {
                        int x3 = points[k][0], y3 = points[k][1];
                        double b = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
                        double c = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
                        double cos = Math.abs((a * a + b * b - c * c)) / (2 * a * b);
                        double sin = Math.sqrt((double) 1 - cos * cos);
                        double t = 0.5 * a * b * sin;
                        maxx = Math.max(maxx, t);
                    }
                }
            }
            return maxx;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
