package com.frankcooper.bank._401_500;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Date 2020/9/9
 * @Author Frank Cooper
 * @Description
 */
public class _452 {

    static _452 handler = new _452();

    public static void main(String[] args) {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        points = new int[][]{{-2147483648, 2147483647}};
        handler.findMinArrowShots(points);
    }


    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int res = 0;
        int curr = Integer.MIN_VALUE;
        //处理{{-2147483648, 2147483647}}这种case
        if (points[0][0] == Integer.MIN_VALUE) res++;
        for (int[] p : points) {
            if (p[0] > curr) {
                curr = p[1];
                res++;
            }
        }
        return res;
    }
}
