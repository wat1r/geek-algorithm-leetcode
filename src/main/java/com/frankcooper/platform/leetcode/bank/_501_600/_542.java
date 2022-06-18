package com.frankcooper.platform.leetcode.bank._501_600;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2020/8/25
 * @Author Frank Cooper
 * @Description
 */
public class _542 {

    static _542 handler = new _542();


    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}};
        handler.updateMatrix(matrix);
    }

    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};//四个方向
    int m, n;

    public int[][] updateMatrix(int[][] matrix) {

        m = matrix.length;
        n = matrix[0].length;

        int[][] res = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    //初始化时，queue有多个源点
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //当前层数的所有节点都需要拿出来
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                //当前的curr坐标
                int currX = curr[0], currY = curr[1];
                if (matrix[currX][currY] == 1) res[currX][currY] = dist; //赋值
                for (int[] d : directions) {
                    //由单当前的curr坐标生出的next坐标，上下左右四个方向
                    int nextX = currX + d[0];
                    int nextY = currY + d[1];
                    //坐标越界或是已经放进queue中，跳过
                    if (!inArea(nextX, nextY) || visited[nextX][nextY]) {
                        continue;
                    }
                    //放进queue中且标记被放进去
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
            dist++;//每一层后+1
        }
        return res;
    }


    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }


    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] matrix = {{0, 0, 0},
                    {0, 1, 0},
                    {1, 1, 1}};
            handler.updateMatrix(matrix);
        }

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};//四个方向


        public int[][] updateMatrix(int[][] mat) {
            int R = mat.length, C = mat[0].length;
            boolean[][] vis = new boolean[R][C];
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (mat[i][j] == 0) {
                        q.offer(new int[]{i, j});
                        vis[i][j] = true;
                    }
                }
            }
            int dist = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int k = 0; k < size; k++) {
                    int[] c = q.poll();
                    if (mat[c[0]][c[1]] == 1) mat[c[0]][c[1]] = dist;
                    for (int[] d : dirs) {
                        int nr = c[0] + d[0], nc = c[1] + d[1];
                        if (nr < 0 || nr >= R || nc < 0 || nc >= C || vis[nr][nc]) {
                            continue;
                        }
                        q.offer(new int[]{nr, nc});
                        vis[nr][nc] = true;
                    }
                }
                dist++;
            }
            return mat;
        }
    }

    static class _3rd {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};//四个方向


        public int[][] updateMatrix(int[][] mat) {
            int R = mat.length, C = mat[0].length;
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (mat[i][j] == 0) {
                        q.offer(new int[]{i, j});
                    } else {
                        mat[i][j] = -1;
                    }
                }
            }
            while (!q.isEmpty()) {
                int size = q.size();
                for (int k = 0; k < size; k++) {
                    int[] p = q.poll();
                    int r = p[0], c = p[1];
                    for (int[] d : dirs) {
                        int nr = r + d[0], nc = c + d[1];
                        if (nr < 0 || nr >= R || nc < 0 || nc >= C || mat[nr][nc] != -1) {
                            continue;
                        }
                        mat[nr][nc] = mat[r][c] + 1;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
            return mat;
        }
    }
}

