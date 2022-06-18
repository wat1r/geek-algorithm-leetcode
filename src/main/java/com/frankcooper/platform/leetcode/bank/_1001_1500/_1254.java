package com.frankcooper.platform.leetcode.bank._1001_1500;

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

//    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};//对应了上右下左四个方向
//    int rows, cols;
//    boolean[][] visited;
//
//    public int closedIsland(int[][] grid) {
//        if (grid == null || grid.length == 0 || grid[0].length == 0) {
//            return 0;
//        }
//        rows = grid.length;
//        cols = grid[0].length;
//        int count = 0;
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if (grid[i][j] == 0) {
//                    count += dfsA(grid, i, j);
//                }
//            }
//        }
//        return count;
//    }
//
//    private int dfsA(int[][] grid, int x, int y) {
//        if (x < 0 || x >= rows || y < 0 || y >= cols ) {
//            return 0;
//        }
//
//        System.out.println(String.format("i:%d,j:%d", x, x));
//        if(grid[x][y] == 1) return 1;
//        int res = 1;
//        grid[x][y] = 1;
//        for (int i = 0; i < 4; i++) {
//            int nextX = x + directions[i][0];
//            int nextY = y + directions[i][1];
//            res =Math.min(res,dfsA(grid,nextX,nextY));
//        }
//        return res;
//    }


    int m, n;
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
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
        if (!inArea(i, j)) return 0;
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


    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }
}
