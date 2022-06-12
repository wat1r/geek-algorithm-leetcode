package com.frankcooper.bank.week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Week297 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public double calculateTax(int[][] brackets, int income) {
            double pay = 0.0;
            for (int i = 0; i < brackets.length; i++) {
                if (income >= brackets[i][0]) {
                    pay += (brackets[i][0] - ((i > 0) ? brackets[i - 1][0] : 0)) * brackets[i][1] * 0.01;
                } else {
                    pay += (income - ((i > 0) ? brackets[i - 1][0] : 0)) * brackets[i][1] * 0.01;
                    break;
                }
            }
            return pay;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

            int[][] grid = {{5, 3}, {4, 0}, {2, 1}};
            int[][] moveCost = {{9, 8}, {1, 5}, {10, 12}, {18, 6}, {2, 4}, {14, 3}};
            handler.minPathCost(grid, moveCost);
        }


        //TLE
        int m, n;
        int minSum = Integer.MAX_VALUE;
        int[][] grid;
        int[][] moveCost;

        public int minPathCost(int[][] grid, int[][] moveCost) {
            m = grid.length;
            n = grid[0].length;
            this.grid = grid;
            this.moveCost = moveCost;
            for (int c = 0; c < n; c++) {
                build(0, c, 0);
            }
            return minSum;

        }


        private void build(int x, int y, int sum) {
            if (minSum != Integer.MAX_VALUE) {
                if (sum >= minSum) return;
            }
            if (x == m - 1) {
                sum += grid[x][y];
                minSum = Math.min(minSum, sum);
                return;
            }
            sum += grid[x][y];
            for (int j = 0; j < n; j++) {
                int i = grid[x][y];
                build(x + 1, j, sum + moveCost[i][j]);
            }
        }


    }

    static class _2nd_1 {
        public static void main(String[] args) {
            _2nd_1 handler = new _2nd_1();

        }

        //动态规划
        public int minPathCost(int[][] grid, int[][] moveCost) {
            int m = grid.length, n = grid[0].length;
            int[][] dp = new int[m][n];//dp[i][j]表示grid[i][j]为结尾，累积的路径的最小代价
            for (int i = 0; i < m; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
            //初始化:第一行的路径
            for (int j = 0; j < n; j++) dp[0][j] = grid[0][j];
            //一般化
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        //从[i-1]行的每一个位置grid[i-1][k]跳到当前位置grid[i][j]
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                    }
                }
            }
            int res = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) res = Math.min(res, dp[m - 1][j]);
            return res;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int distributeCookies(int[] cookies, int k) {
            int n = cookies.length;
            boolean[] vis = new boolean[n];
            List<List<Integer>> arr = new ArrayList<>();

            return 0;

        }


        private void helper(int index, List<List<Integer>> arr, int[] cookies, boolean[] vis) {


//            for (int i = 0; i < cookies.length; i++) {
//                for()
//            }


        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
