package com.frankcooper.lintcode;

import java.util.LinkedList;
import java.util.Queue;

public class _1563 {


    /**
     * bfs
     */
    static class _1st {
        int R, C;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public int shortestPath(int[][] A) {
            // Write your code here
            R = A.length;
            C = A[0].length;
            Queue<int[]> q = new LinkedList<>();
            boolean[][] vis = new boolean[R][C];
            q.offer(new int[]{0, 0});
            vis[0][0] = true;
            int steps = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                steps++;
                for (int i = 0; i < size; i++) {
                    int[] c = q.poll();
                    int cr = c[0], cc = c[1];
                    for (int[] d : dirs) {
                        int nr = cr + d[0], nc = cc + d[1];
                        if (!inArea(nr, nc) || vis[nr][nc]) continue;
                        if (A[nr][nc] == 2) return steps;
                        if (A[nr][nc] == 1) continue;
                        q.offer(new int[]{nr, nc});
                        vis[nr][nc] = true;
                    }
                }
            }
            return -1;
        }

        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }


    /**
     * dfs
     */
    static class _2nd {

        int R, C;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[][] vis;
        int res;

        public int shortestPath(int[][] A) {
            // Write your code here
            R = A.length;
            C = A[0].length;
            vis = new boolean[R][C];
            res = R * C;
            dfs(A, 0, 0, 0);
            return res == R * C ? -1 : res;
        }


        private void dfs(int[][] A, int r, int c, int steps) {
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (!inArea(nr, nc) || vis[nr][nc]) continue;
                if (A[nr][nc] == 2) {
                    steps++;
                    res = Math.min(res, steps);
                    return;
                } else if (A[nr][nc] == 1) {
                    continue;
                } else if (A[nr][nc] == 0) {
                    steps++;
                    vis[nr][nc] = true;
                    dfs(A, nr, nc, steps);
                    vis[nr][nc] = false;
                    steps--;
                }


            }
        }


        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }


}
