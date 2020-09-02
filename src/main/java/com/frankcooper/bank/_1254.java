package com.frankcooper.bank;

/**
 * @Date 2020/9/2
 * @Author Frank Cooper
 * @Description
 */
public class _1254 {

    static _1254 handler = new _1254();

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0}};
        handler.closedIsland(grid);
    }


    int m, n;
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res += dfs(grid, i, j);
                }
            }
        }

        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (outArea(i, j)) return 0;
        System.out.println(String.format("i:%d,j:%d", i, j));
        if (grid[i][j] == 1) return 1;
        int count = 1;
        grid[i][j] = 1;
        for (int[] d : dirs) {
            int nextI = i + d[0], nextJ = j + d[1];
            count = Math.min(count, dfs(grid, nextI, nextJ));
        }
        return count;
    }


    private boolean outArea(int i, int j) {
        return i < 0 || i >= m || j < 0 || j >= n;
    }
}
