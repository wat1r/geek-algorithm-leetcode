package com.frankcooper.bank._101_200;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _149 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] points = PrintUtils.processSymbol("[[1,1],[2,2],[3,3]]");
            handler.maxPoints(points);

        }


        public int maxPoints(int[][] points) {
            int n = points.length;
            if (n <= 2) return n;
            int res = 0;
            for (int i = 0; i < n; i++) {
                if (res >= n - i || res > n / 2) {
                    break;
                }
                Map<Integer, Integer> map = new HashMap<>();
                for (int j = i + 1; j < n; j++) {
                    int x = points[i][0] - points[j][0];
                    int y = points[i][1] - points[j][1];
                    if (x == 0) y = 1;
                    else if (y == 0) x = 1;
                    else {
                        if (y < 0) {
                            x = -x;
                            y = -y;
                        }
                        int c = gcd(Math.abs(x), Math.abs(y));
                        x /= c;
                        y /= c;
                    }
                    int k = y + x * 20001;
                    map.put(k, map.getOrDefault(k, 0) + 1);
                }
                int maxv = 0;
                for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                    int cnt = e.getValue();
                    maxv = Math.max(maxv, cnt + 1);
                }
                res = Math.max(res, maxv);
            }
            return res;
        }


        private int gcd(int a, int b) {
            return b != 0 ? gcd(b, a % b) : a;
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
