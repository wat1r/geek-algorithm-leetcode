package com.frankcooper.bank;

import com.frankcooper.swordoffer.utils.PrintUtils;

public class _1034 {

    public static void main(String[] args) {

        _1st handler = new _1st();
        int[][] grid = PrintUtils.processSymbol("[[1,1,1],[1,1,1],[1,1,1]]");
        int r0 = 1, c0 = 1, color = 2;
        handler.colorBorder(grid, r0, c0, color);
    }


    /**
     * 边界特性：首先值等于初始值origin；四个方向至少有一个方向的元素不等于origin（或是边界）。
     * 对于搜索到的位置，检查该点是否是边界，如果是边界则将其置为color。
     */
    static class _1st {


        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;
        boolean[][] visited;
        int oldColor;


        public int[][] colorBorder(int[][] grid, int r0, int c0, int newColor) {
            R = grid.length;
            C = grid[0].length;
            visited = new boolean[R][C];
            oldColor = grid[r0][c0];
            dfs(grid, r0, c0, newColor);
            return grid;
        }


        public int dfs(int[][] grid, int r, int c, int newColor) {
            if (!inArea(r, c)) return 0;
            if (visited[r][c]) return 1;
            if (grid[r][c] != oldColor) return 0;
            visited[r][c] = true;
            int cnt = 0;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                cnt += dfs(grid, nr, nc, newColor);
            }
            if (r == 1 && c == 1) {
                System.out.printf("%s\n","d");
            }
            if (cnt < 4) grid[r][c] = newColor;
            return 1;
        }


        public boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }


}
