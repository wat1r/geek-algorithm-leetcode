package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _08_02 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] grid = {
                    {0, 0, 0},
                    {0, 1, 0},
                    {0, 0, 0}
            };
            handler.pathWithObstacles(grid);
        }


        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> pathWithObstacles(int[][] grid) {

            int m = grid.length, n = grid[0].length;
            if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) return res;
            int[][] dp = new int[m][n];//从起点位置能否到达当前点[i,j] 0不能 1能
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
                int up = 0;//向上
                if (r > 0) up = dp[r - 1][c];
                int left = 0;//向左
                if (c > 0) left = dp[r][c - 1];
                if (up >= left) r--;
                else c--;
            }
            res.add(Arrays.asList(0, 0));
            Collections.reverse(res);//逆序
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> pathWithObstacles(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[][] dp = new int[m][n];//dp[i][j]表示 到达[i,j]的最短距离
            for (int i = 1; i < m; i++) {
                if (grid[i][0] == 1) break;
//                dp[i][0] =
            }


            return res;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        List<List<Integer>> res = new ArrayList<>();
        int[][] grid;
        int m, n;
        boolean[][] vis;

        public List<List<Integer>> pathWithObstacles(int[][] grid) {
            this.grid = grid;
            this.m = grid.length;
            this.n = grid[0].length;
            this.vis = new boolean[m][n];
            dfs(0, 0);
            return res;
        }


        public boolean dfs(int i, int j) {
            //出口条件：越界(坐标只增不减)，障碍，被访问过
            if (i >= m || j >= n || grid[i][j] == 1 || vis[i][j]) return false;
            res.add(Arrays.asList(i, j));//添加路径
            vis[i][j] = true;
            if (i == m - 1 && j == n - 1) return true;//找到终点
            if (dfs(i + 1, j) || dfs(i, j + 1)) {//从上面或者左边来
                return true;
            }
            res.remove(res.size() - 1);//回溯移除路径
            return false;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public List<List<Integer>> pathWithObstacles(int[][] grid) {
            List<List<Integer>> res = new ArrayList<>();
            int m = grid.length, n = grid[0].length;
            Queue<int[]> q = new LinkedList<>();
            boolean[][] vis = new boolean[m][n];
            int[][] path = new int[m][n];
//            path[0][0] = new int[]{-1,-1}
            q.offer(new int[]{0, 0});
            vis[0][0] = true;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1];
                if (x == m - 1 && y == n - 1) {
                    int[] p = cur;
                    while (p[0] != -1) {
                        res.add(0, Arrays.asList(p[0], p[1]));
//                        p = path[p[0]][p[1]];
                    }
                    res.add(Arrays.asList(x, y));
                }

            }
            return res;

        }

    }
}
