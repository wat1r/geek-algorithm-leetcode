package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _08_02 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> pathWithObstacles(int[][] grid) {

            int m = grid.length, n = grid[0].length;
            if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) return res;
            int[][] dp = new int[m][n];
            dp[0][0] = 1;
            for (int i = 1; i < m; i++) {
                if (grid[i][0] == 1) dp[i][0] = 0;
                else dp[i][0] = dp[i - 1][0];
            }
            for (int j = 1; j < n; j++) {
                if (grid[0][j] == 1) dp[0][j] = 0;
                else dp[0][j] = dp[0][j - 1];
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (grid[i][j] != 1) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            if (dp[m - 1][n - 1] == 0) return res;
            int r = m - 1, c = n - 1;
            while (r != 0 || c != 0) {
                res.add(Arrays.asList(r, c));
                int up = 0;
                if (r > 0) up = dp[r - 1][c];
                int left = 0;
                if (c > 0) left = dp[r][c - 1];
                if (up >= left) r--;
                else c--;
            }
            res.add(Arrays.asList(0, 0));
            Collections.reverse(res);
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
