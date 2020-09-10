package com.frankcooper.bank;

import java.util.Arrays;

/**
 * @Date 2020/9/10
 * @Author Frank Cooper
 * @Description
 */
public class _435 {


    static _435 handler = new _435();


    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}};
        handler.eraseOverlapIntervals(intervals);
    }


    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) return 0;
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int res = 0;
        int prev = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[prev][1] > intervals[i][0]) {
                if (intervals[prev][1] > intervals[i][1]) {
                    prev = i;
                }
                res++;
            } else {
                prev = i;
            }
        }
        return res;
    }


}
