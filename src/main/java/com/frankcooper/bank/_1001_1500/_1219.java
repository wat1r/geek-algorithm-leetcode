package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1219 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] grid = {{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}};
            handler.getMaximumGold(grid);

        }

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;
        int[][] grid;
        int[][] vis;

        public int getMaximumGold(int[][] grid) {
            R = grid.length;
            C = grid[0].length;
            this.grid = grid;
            this.vis = new int[R][C];
            int res = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (grid[r][c] != 0) {
                        vis[r][c] = 1;
                        res = Math.max(res, dfs(r, c));
                        vis[r][c] = 0;
//                        System.out.println(res);
                    }
                }
            }
            return res;
        }


        private int dfs(int r, int c) {
            int ans = grid[r][c];
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || grid[nr][nc] == 0 || vis[nr][nc] == 1) {
                    continue;
                }
                vis[nr][nc] = 1;
                ans = Math.max(ans, grid[r][c] + dfs(nr, nc));
                vis[nr][nc] = 0;
            }
            return ans;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;
        int[][] grid;
        int res = 0;

        public int getMaximumGold(int[][] grid) {
            R = grid.length;
            C = grid[0].length;
            this.grid = grid;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (grid[r][c] != 0) {
                        dfs(r,c,0);
                    }
                }
            }
            return res;
        }


        /**
         * 记枚举的起点为 (i, j)，我们就可以从 (i, j) 开始进行递归 + 回溯，枚举所有可行的开采路径。
         * 我们用递归函数 \textit{dfs}(x, y, \textit{gold})dfs(x,y,gold) 进行枚举，其中 (x, y)(x,y) 表示当前所在的位置，
         * \textit{gold}gold 表示在开采位置 (x, y)(x,y) 之前，已经拥有的黄金数量
         *
         * @param r
         * @param c
         * @param v
         */
        private void dfs(int r, int c, int v) {
            v += grid[r][c];
            res = Math.max(res, v);
            //暂存grid[r][c]的值，并标记grid[r][c]为0 ，不能回头走这个点
            int ans = grid[r][c];
            grid[r][c] = 0;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || grid[nr][nc] == 0) {
                    continue;
                }
                dfs(nr, nc, v);
            }
            grid[r][c] =ans;
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
