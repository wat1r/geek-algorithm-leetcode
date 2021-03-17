package com.frankcooper.bank._301_400;


import com.frankcooper.swordoffer.utils.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

public class _329 {
//    static _329 handler = new _329();

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        _1st handler = new _1st();
        handler.longestIncreasingPath(matrix);
    }


    static class _1st {
        int[][] memo;
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int m, n;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
            m = matrix.length;
            n = matrix[0].length;
            memo = new int[m][n];
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    res = Math.max(res, dfs(matrix, i, j));
                    PrintUtils.printMatrix(memo);
                }
            }
            return res;
        }

        private int dfs(int[][] matrix, int i, int j) {
            if (memo[i][j] != 0) return memo[i][j];
            memo[i][j] += 1;
            for (int[] dir : directions) {
                int nextI = i + dir[0], nextJ = j + dir[1];
                if (inArea(nextI, nextJ) && matrix[i][j] < matrix[nextI][nextJ]) {
                    memo[i][j] = Math.max(memo[i][j], dfs(matrix, nextI, nextJ) + 1);
                }
            }
            return memo[i][j];
        }


        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }
    }


    static class _2nd {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;


        public int longestIncreasingPath(int[][] matrix) {
            R = matrix.length;
            C = matrix[0].length;
            int[][] outdegrees = new int[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    for (int[] d : dirs) {
                        int nr = r + d[0], nc = c + d[1];
                        if (!inArea(nr, nc)) continue;//顺着方向找出度
                        if (matrix[r][c] < matrix[nr][nc]) outdegrees[r][c]++;
                    }
                }
            }
            Queue<int[]> q = new LinkedList<>();
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (outdegrees[r][c] == 0) q.offer(new int[]{r, c});
                }
            }
            int ans = 0;
            while (!q.isEmpty()) {
                ans++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] curr = q.poll();
                    int r = curr[0], c = curr[1];
                    for (int[] d : dirs) {
                        int nr = r + d[0], nc = c + d[1];
                        if (!inArea(nr, nc)) continue;
                        if (matrix[nr][nc] < matrix[r][c]) {//逆着方向找
                            outdegrees[nr][nc]--;
                            if (outdegrees[nr][nc] == 0) q.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
            return ans;
        }


        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }


}
