package com.frankcooper.bank;

/**
 * Created by FrankCooper
 * Date 2020/9/19 14:29
 * Description
 */
public class _733 {


    static _733 handler = new _733();

    public static void main(String[] args) {

    }


    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int m, n;
    boolean[][] visited;
    int oldColor;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        this.m = image.length;
        this.n = image[0].length;
        oldColor = image[sr][sc];
        visited = new boolean[m][n];
        dfs(image, sr, sc, newColor);

        return image;
    }

    private void dfs(int[][] image, int r, int c, int newColor) {
        if (!inArea(r, c)) return;
        if (visited[r][c] || image[r][c] != oldColor) return;
        image[r][c] = newColor;
        visited[r][c] = true;
        for (int[] dir : directions) {
            int nextR = r + dir[0], nextC = c + dir[1];
            dfs(image, nextR, nextC, newColor);
        }
    }

    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }


}
