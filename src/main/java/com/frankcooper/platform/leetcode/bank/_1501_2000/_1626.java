package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.Arrays;
import java.util.Comparator;

public class _1626 {


    static _1626 handler = new _1626();

    public static void main(String[] args) {
        //scores = [4,5,6,5], ages = [2,1,2,1]
        int[] scores = {1, 3, 5, 10, 15};
        int[] ages = {1, 2, 3, 4, 5};
        handler.bestTeamScore1st(scores, ages);
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) arr[i] = new int[]{ages[i], scores[i]};
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i][1];
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j][1] <= arr[i][1]) dp[i] = Math.max(dp[i], dp[j] + arr[i][1]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public int bestTeamScore1st(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{ages[i], scores[i]};
        }
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        int max = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j][1] <= arr[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += arr[i][1];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
