package com.frankcooper.bank;

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


    public int islandPerimeter(int[][] grid) {
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

}
