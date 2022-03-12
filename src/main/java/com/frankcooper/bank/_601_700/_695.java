package com.frankcooper.bank._601_700;

/**
 * @Date 2020/8/13
 * @Author Frank Cooper
 * @Description
 */
public class _695 {

    int m, n;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};//对应了上右下左四个方向

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int seg = dfs(grid, i, j);
                res = Math.max(res, seg);
            }
        }
        return res;

    }

    private int dfs(int[][] grid, int i, int j) {
        if (isOut(i, j) || grid[i][j] != 1) return 0;
        grid[i][j] = 2;
        int seg = 1;
        for (int[] direction : directions) {
            int nextI = i + direction[0];
            int nextJ = j + direction[1];
            seg += dfs(grid, nextI, nextJ);
        }
        return seg;

    }

    public boolean isOut(int i, int j) {
        return i < 0 || i >= m || j < 0 || j >= n;
    }


    static class _2nd {


        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] grid = new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        }

        int R, C;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};//对应了上右下左四个方向

        public int maxAreaOfIsland(int[][] grid) {
            R = grid.length;
            C = grid[0].length;
            int maxArea = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    maxArea = Math.max(maxArea, dfs(grid, r, c));
                }
            }
            return maxArea;
        }

        public int dfs(int[][] grid, int r, int c) {
            if (r >= R || r < 0 || c >= C || c < 0 || grid[r][c] != 1) return 0;
            int res = 1;
            grid[r][c] = 2;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                res += dfs(grid, nr, nc);
            }
            return res;

        }
    }
}
