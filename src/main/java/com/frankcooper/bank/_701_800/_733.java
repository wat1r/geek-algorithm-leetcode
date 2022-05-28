package com.frankcooper.bank._701_800;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by FrankCooper
 * Date 2020/9/19 14:29
 * Description
 */
public class _733 {


    static _733 handler = new _733();

    public static void main(String[] args) {

    }


    class _3rd {

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int oldColor = image[sr][sc];
            if (oldColor == newColor) return image;
            R = image.length;
            C = image[0].length;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{sr, sc});
            image[sr][sc] = newColor;
            while (!queue.isEmpty()) {
                int[] p = queue.poll();
                for (int[] d : dirs) {
                    int nr = p[0] + d[0], nc = p[1] + d[1];
                    if (!inArea(nr, nc)) continue;
                    if (image[nr][nc] == oldColor) {
                        image[nr][nc] = newColor;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            return image;
        }

        public boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }

    }


    class _2nd {

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int oldColor = image[sr][sc];
            if (oldColor == newColor) return image;
            R = image.length;
            C = image[0].length;
            dfs(image, sr, sc, newColor, oldColor);
            return image;
        }


        public void dfs(int[][] image, int r, int c, int newColor, int oldColor) {
            if (!inArea(r, c)) return;
            if (image[r][c] != oldColor) return;
            image[r][c] = newColor;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                dfs(image, nr, nc, newColor, oldColor);
            }
        }

        public boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }

    }

    class _1st {
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

    static class _4th {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int R = image.length, C = image[0].length;
            if (image[sr][sc] == newColor) return image;
            int oldColor = image[sr][sc];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{sr, sc});
            image[sr][sc] = newColor;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int[] d : dirs) {
                    int nr = cur[0] + d[0], nc = cur[1] + d[1];
                    if (nr >= R || nr < 0 || nc >= C || nc < 0) continue;
                    if (image[nr][nc] != oldColor) continue;
                    image[nr][nc] = newColor;
                    q.add(new int[]{nr, nc});
                }
            }
            return image;


        }
    }


}
