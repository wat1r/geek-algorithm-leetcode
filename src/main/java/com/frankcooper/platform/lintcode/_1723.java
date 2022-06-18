package com.frankcooper.platform.lintcode;

public class _1723 {

    static class _1st {


        int R, C;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int res = Integer.MAX_VALUE;

//        public int shortestPath(int[][] grid, int k) {
//            // write your code here
//        }


//        private void dfs(int[][] grid, int r, int c, int k, int steps) {
//            if (!inArea(r, c) || grid[r][c] == -1) return;
//            if (grid[r][c] == 2) {
//
//            }
//        }

        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }

    }
}
