package com.frankcooper.bank;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * Created by FrankCooper
 * Date 2020/10/25 8:27
 * Description
 */
public class _1024 {


    static _1024 handler = new _1024();

    public static void main(String[] args) {
        int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        int T = 10;
        clips = new int[][]{{0, 2}, {1, 3}, {1, 4}, {2, 5}, {4, 6}};
        T = 6;
        handler.videoStitching(clips, T);
    }

    int INF = Integer.MAX_VALUE >> 1;

    public int videoStitching(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] c : clips) {
                if (c[0] <= i && i <= c[1]) {
                    dp[i] = Math.min(dp[i], dp[c[0]] + 1);
                }
            }
//            System.out.println(String.format("%d--->%s",i,JSON.toJSONString(dp)));
        }
        return dp[T] == INF ? -1 : dp[T];
    }


}
