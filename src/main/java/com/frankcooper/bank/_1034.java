package com.frankcooper.bank;

public class _1034 {


    class _1st {


        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;


        public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
            R = grid.length;
            C = grid[0].length;
            dfs(grid, r0, c0, color);

            return grid;
        }


        public void dfs(int[][] grid, int r, int c, int color) {


            grid[r][c] = color;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];

//                if (!inArea(r, c)) return;

                dfs(grid, nr, nc, color);
            }


        }


        public boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }


}
