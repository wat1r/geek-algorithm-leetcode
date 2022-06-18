package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _447 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int numberOfBoomerangs(int[][] points) {

            int res = 0;
            for (int i = 0; i < points.length; i++) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int j = 0; j < points.length; j++) {
                    int dx = points[i][0] - points[j][0];
                    int dy = points[i][1] - points[j][1];
                    int dis = dx * dx + dy * dy;
                    map.put(dis, map.getOrDefault(dis, 0) + 1);
                }
                for (int cnt : map.values()) {
                    res += cnt * (cnt - 1);
                }
            }
            return res;
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
