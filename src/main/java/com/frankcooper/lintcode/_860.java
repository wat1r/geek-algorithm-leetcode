package com.frankcooper.lintcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date 2020/8/17
 * @Author Frank Cooper
 * @Description
 */
public class _860 {
    static _860 handler = new _860();

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 1, 0, 0, 1}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 1, 0, 1, 1}};
//        handler.numberofDistinctIslands1st(grid);

    }


    int m, n;
    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int numberofDistinctIslands1st(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, path, i, j);
                    System.out.println(path.toString());
                    set.add(path.toString());
                }
            }
        }
        return set.size();

    }

    /**
     * @param grid
     * @param i
     * @param j
     * @param path  路径
     * @param seedI 种子坐标
     * @param seedJ 种子坐标
     */
    private void dfs(int[][] grid, int i, int j, StringBuilder path, int seedI, int seedJ) {
        if (!inArea(i, j) || grid[i][j] != 1) return;
        grid[i][j] = 2;
        path.append(i - seedI).append(j - seedJ);
        for (int[] direction : directions) {
            int nextI = i + direction[0];
            int nextJ = j + direction[1];
            dfs(grid, nextI, nextJ, path, seedI, seedJ);
        }
    }

    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }


    static class _2nd {
        static _2nd handler = new _2nd();

        public static void main(String[] args) {
            int[][] grid = new int[][]{{1, 1, 0, 0, 1}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 1, 0, 1, 1}};
            handler.numberofDistinctIslands(grid);
        }

        int m, n;

        public int numberofDistinctIslands(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
            m = grid.length;
            n = grid[0].length;
            Set<String> set = new HashSet<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        StringBuilder path = new StringBuilder();
                        dfs(grid, i, j, path);
                        System.out.println(path.toString());
                        set.add(path.toString());
                    }
                }
            }
            return set.size();
        }

        private void dfs(int[][] grid, int i, int j, StringBuilder path) {
            if (!inArea(i, j) || grid[i][j] != 1) return;
            grid[i][j] = 2;
            dfs(grid, i - 1, j, path.append("u")); //up;
            dfs(grid, i, j + 1, path.append("r")); //right;
            dfs(grid, i + 1, j, path.append("d")); //down;
            dfs(grid, i, j - 1, path.append("l")); //left;
        }

        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }

    }

}
