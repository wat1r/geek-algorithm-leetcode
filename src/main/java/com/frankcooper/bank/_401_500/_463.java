package com.frankcooper.bank._401_500;

/**
 * @Date 2020/8/13
 * @Author Frank Cooper
 * @Description
 */
public class _463 {


    public static void main(String[] args) {

    }


    public int islandPerimeter1st(int[][] grid) {
        int res = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res += 4;
                    if (i > 0 && grid[i - 1][j] == 1) res -= 2;
                    if (j > 0 && grid[i][j - 1] == 1) res -= 2;
                }
            }
        }
        return res;
    }


    public int islandPerimeter2nd(int[][] grid) {
        int res = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    res += 4;
                    if (i < m - 1 && grid[i + 1][j] == 1) res -= 2;
                    if (j < n - 1 && grid[i][j + 1] == 1) res -= 2;
                }
            }
        }
        return res;
    }

    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m, n;

    public int islandPerimeter(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (!inArea(i, j)) return 1;
        if (grid[i][j] == 0) return 1;
        if (grid[i][j] == 2) return 0;
        grid[i][j] = 2;
        int tmp = 0;
        tmp += dfs(grid, i - 1, j);
        tmp += dfs(grid, i, j - 1);
        tmp += dfs(grid, i + 1, j);
        tmp += dfs(grid, i, j + 1);


//        for (int[] direction : directions) {
//            int nextI = i + direction[0];
//            int nextJ = j + direction[1];
//            tmp += dfs(grid, nextI, nextJ);
//        }
        return tmp;
    }

    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }


}
