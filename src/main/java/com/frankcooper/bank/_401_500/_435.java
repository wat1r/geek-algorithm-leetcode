package com.frankcooper.bank._401_500;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Date 2020/9/10
 * @Author Frank Cooper
 * @Description
 */
public class _435 {


    static _435 handler = new _435();


    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
//        intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}};
//        handler.eraseOverlapIntervals1st(intervals);

        int i = 1;

//        System.out.println(1==nu);
//        System.out.println("1".equals(1));
        System.out.println("1".equals(String.valueOf(i)));
    }


    public int eraseOverlapIntervals1st(int[][] intervals) {
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


    public int eraseOverlapIntervals2nd(int[][] A) {
        Arrays.sort(A, (Comparator.comparingInt(o -> o[0])));
        int ans = 0;
        int prev = 0;
        for (int i = 1; i < A.length; ++i) {
            if (A[prev][1] > A[i][0]) {
                if (A[prev][1] > A[i][1]) prev = i;
                ans++;
            } else {
                prev = i;
            }
        }
        return ans;
    }

    public int eraseOverlapIntervals(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return 0;
        Arrays.sort(A, Comparator.comparingInt(o -> o[0]));
        int N = A.length, f[] = new int[N];
        Arrays.fill(f, 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j][1] <= A[i][0]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        return N - Arrays.stream(f).max().getAsInt();
    }


}
