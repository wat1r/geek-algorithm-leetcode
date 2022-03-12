package com.frankcooper.bank._701_1000;

/**
 * @Date 2020/7/9
 * @Author Frank Cooper
 * @Description
 */
public class _980 {
    static _980 handler = new _980();

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        handler.uniquePathsIII(grid);
    }


    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int X = 0, Y = 0, paths = 1;//开始时的坐标轴及路径长度
    int m = 0, n = 0;//行*列

    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        helper(X, Y, grid);
        return dfs(X, Y, paths, grid);
    }

    /**
     * @param x     坐标的row
     * @param y     坐标的col
     * @param paths 当前路径所剩的长度
     * @param grid  矩阵
     * @return
     */
    private int dfs(int x, int y, int paths, int[][] grid) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == -1) return 0;//其中的-1表示是原矩阵的-1
        System.out.println(String.format("%d,%d", x, y));
        if (grid[x][y] == 2) {
            return paths == 0 ? 1 : 0;
        }
        grid[x][y] = -1;//如果当前的位置未被走过，标记为-1表示已经走过
        int res = 0;
        for (int i = 0; i < directions.length; i++) {//四个方向的结果都走，res+注意
            res += dfs(x + directions[i][0], y + directions[i][1], paths - 1, grid);
        }
        grid[x][y] = 0;//恢复当前的位置表示可走
        return res;
    }

    /**
     * 1.拿到开始时的坐标轴，即grid[i][j] =1时
     * 2.从1走到2要走过多少步，即矩阵中0的个数+1
     *
     * @param x    坐标的row
     * @param y    坐标的col
     * @param grid 矩阵
     */
    private void helper(int x, int y, int[][] grid) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    X = i;
                    Y = j;
                    continue;
                }
                if (grid[i][j] == 0) paths++;
            }
        }
    }
}
