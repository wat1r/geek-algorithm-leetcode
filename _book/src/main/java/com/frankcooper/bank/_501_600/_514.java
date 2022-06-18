package com.frankcooper.platform.leetcode.bank._501_600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _514 {

    static _514 handler = new _514();

    public static void main(String[] args) {
        String ring = "godding";
        String key = "gd";
//        handler.findRotateSteps(ring, key);
    }


    public int findRotateSteps1st(String ring, String key) {
        int n = ring.length(), m = key.length();
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        for (int j : pos[key.charAt(0) - 'a']) {
            dp[0][j] = Math.min(j, n - j) + 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }

        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }

    String key;
    int[][] memo;
    List<Integer>[] pos = new ArrayList[26];

    public int findRotateSteps(String ring, String key) {
        int n = ring.length(), m = key.length();
        this.key = key;
        memo = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(memo[i], -1);
        for (int i = 0; i < 26; i++) pos[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) pos[ring.charAt(i) - 'a'].add(i);
        return dfs(n, 0, 0);
    }

    private int dfs(int n, int c, int currPos) {
        if (currPos == key.length()) return 0;
        if (memo[c][currPos] > 0) return memo[c][currPos];
        List<Integer> list = pos[key.charAt(currPos) - 'a'];
        int min = Integer.MAX_VALUE;
        for (int i : list) {
            int dis = Math.min(Math.abs(c - i), n - Math.abs(c - i)) + 1 + dfs(n, i, currPos + 1);
            if (dis < min) min = dis;
        }
        memo[c][currPos] = min;
        return min;
    }


}
