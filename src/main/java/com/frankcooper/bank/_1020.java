package com.frankcooper.bank;

import com.frankcooper.swordoffer.utils.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

public class _1020 {


    public static void main(String[] args) {
        int[][] A = PrintUtils.processSymbol("[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]");
        _1st handler = new _1st();
        A = PrintUtils.processSymbol("[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]");
        handler.numEnclaves(A);
    }


    /**
     * bfs
     */
    static class _1st {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;

        public int numEnclaves(int[][] A) {
            R = A.length;
            C = A[0].length;
            PrintUtils.printMatrix(A);
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (isBoard(r, c) && A[r][c] == 1) {//边界上的1作为种子来进行扩散
                        bfs(A, r, c);
                    }
                }
            }
            //统计1的个数（即封闭的陆地的个数）
            int cnt = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (A[r][c] == 1) cnt++;
                }
            }
            return cnt;
        }


        private void bfs(int[][] A, int sr, int sc) {
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{sr, sc});
            A[sr][sc] = 0;//边缘进来的点是1，染色为0
            while (!q.isEmpty()) {
                int[] c = q.poll();
                int cr = c[0], cc = c[1];
                for (int[] d : dirs) {
                    int nr = cr + d[0], nc = cc + d[1];
                    if (!inArea(nr, nc)) continue;
                    if (A[nr][nc] == 1) {//如果当前点是陆地1，翻转为0
                        q.offer(new int[]{nr, nc});
                        A[nr][nc] = 0;
                    }
                }
            }
        }

        //边缘的点
        private boolean isBoard(int r, int c) {
            return r == 0 || r == R - 1 || c == 0 || c == C - 1;
        }

        //在区域内的点
        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }


    /**
     * dfs
     */
    static class _2nd {

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;

        public int numEnclaves(int[][] A) {
            R = A.length;
            C = A[0].length;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (isBoard(r, c) && A[r][c] == 1) {
                        dfs(A, r, c);
                    }
                }
            }
            //统计1的个数（即封闭的陆地的个数）
            int cnt = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (A[r][c] == 1) cnt++;
                }
            }
            return cnt;

        }


        private void dfs(int[][] A, int r, int c) {
            if (!inArea(r, c) || A[r][c] != 1) return;
            A[r][c] = 0;
            for (int[] d : dirs) {
                dfs(A, r + d[0], c + d[1]);
            }
        }

        //边缘的点
        private boolean isBoard(int r, int c) {
            return r == 0 || r == R - 1 || c == 0 || c == C - 1;
        }

        //在区域内的点
        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }

    }

}
