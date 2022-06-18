package com.frankcooper.platform.leetcode.interview;

/**
 * @Date 2020/9/2
 * @Author Frank Cooper
 * @Description
 */
public class _08_10 {

    static _08_10 handler = new _08_10();

    public static void main(String[] args) {
        int[][] image = {{0, 0, 0}, {0, 1, 1}};
        int sr = 1, sc = 1, newColor = 1;
//        handler.floodFill(image, sr, sc, newColor);
    }


    int m, n;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        m = image.length;
        n = image[0].length;
        int oldColor = image[sr][sc];
        dfs(image, sr, sc, oldColor, newColor);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if (!inArea(sr, sc) || image[sr][sc] != oldColor) return;
        if (image[sr][sc] == -1) return;
        image[sr][sc] = -1;//
        for (int[] dir : dirs) {
            int dR = sr + dir[0], dC = sc + dir[1];
            dfs(image, dR, dC, oldColor, newColor);
        }
        image[sr][sc] =newColor;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }


    class _1st {
        int m, n;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited;


        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            m = image.length;
            n = image[0].length;
            visited = new boolean[m][n];
            int oldColor = image[sr][sc];
            dfs(image, sr, sc, oldColor, newColor);
            return image;
        }

        private void dfs(int[][] image, int sr, int sc, int oldColor, int newColor) {
            if (!inArea(sr, sc) || image[sr][sc] != oldColor) return;
            if (visited[sr][sc]) return;
            image[sr][sc] = newColor;
            visited[sr][sc] = true;
            for (int[] dir : dirs) {
                int dR = sr + dir[0], dC = sc + dir[1];
                dfs(image, dR, dC, oldColor, newColor);
            }

        }

        private boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }
    }

}
