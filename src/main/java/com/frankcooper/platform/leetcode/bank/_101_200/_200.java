package com.frankcooper.platform.leetcode.bank._101_200;

public class _200 {

    static class _1st {


        public static void main(String[] args) {
            char[][] grid = {
                    {'1', '1', '0', '0', '0'},
                    {'1', '1', '0', '0', '0'},
                    {'0', '0', '1', '0', '0'},
                    {'0', '0', '0', '1', '1'}
            };
            _1st handler = new _1st();
            handler.numIslands(grid);
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    System.out.printf("%s ", grid[i][j]);
                }
                System.out.println();
            }
        }

        int m, n;

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public int numIslands(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        dfs(grid, i, j);
                        ans++;
                    }
                }
            }
            return ans;
        }

        private void dfs(char[][] grid, int i, int j) {
            if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') {
                return;
            }
            grid[i][j] = '0';
            for (int[] d : dirs) {
                int ni = i + d[0], nj = j + d[1];
                dfs(grid, ni, nj);
            }
        }
    }


    static class _2nd {

        public static void main(String[] args) {

        }

        int R, C;

        public int numIslands(char[][] grid) {
            this.R = grid.length;
            this.C = grid[0].length;
            int res = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (grid[r][c] == '1') {
                        dfs(grid, r, c);
                        res++;
                    }
                }
            }
            return res;
        }

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        private void dfs(char[][] grid, int r, int c) {
            if (r < 0 || r >= R || c < 0 || c >= C || grid[r][c] != '1') {
                return;
            }
            grid[r][c] = '0';
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                dfs(grid, nr, nc);
            }
        }

    }

}
