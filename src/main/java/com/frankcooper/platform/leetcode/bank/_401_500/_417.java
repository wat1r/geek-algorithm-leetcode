package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Queue;

public class _417 {

    static _417 handler = new _417();

    public static void main(String[] args) {

    }


    static class _1st {


        int m, n;
        int[][] matrix;
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};


        public List<List<Integer>> pacificAtlantic(int[][] matrix) {
            List<List<Integer>> resList = new ArrayList<>();
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return resList;
            this.m = matrix.length;
            this.n = matrix[0].length;
            this.matrix = matrix;
            boolean[][] visitedP = new boolean[m][n];//到达太平洋的bool[][]
            boolean[][] visitedA = new boolean[m][n];//到达大西洋的bool[][]

            for (int j = 0; j < n; j++) {
                dfs(0, j, visitedP);
                dfs(m - 1, j, visitedA);
            }
            for (int i = 0; i < m; i++) {
                dfs(i, 0, visitedP);
                dfs(i, n - 1, visitedA);
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (visitedP[i][j] && visitedA[i][j]) {//都能到达，开始收集结果
                        resList.add(Arrays.asList(i, j));
                    }
                }
            }
            return resList;
        }


        public void dfs(int i, int j, boolean[][] visited) {
            //标记被访问过
            visited[i][j] = true;
            for (int[] dir : directions) {
                int nextI = i + dir[0], nextJ = j + dir[1];
                //下一个坐标需要满足3个条件
                //1.在区域范围内
                //2.比上一个位置(i,j)的值要大，因为我们从外层逆着水流方向找的
                //3.没有被访问过
                if (inArea(nextI, nextJ) &&
                        matrix[nextI][nextJ] >= matrix[i][j] && !visited[nextI][nextJ]) {
                    dfs(nextI, nextJ, visited);
                }
            }


        }

        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }
    }

    static class _2nd {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;
        int[][] heights;

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            List<List<Integer>> res = new ArrayList<>();
            R = heights.length;
            C = heights[0].length;
            this.heights = heights;
            boolean[][] visP = new boolean[R][C];
            boolean[][] visA = new boolean[R][C];
            for (int c = 0; c < C; c++) {
                dfs(0, c, visP);
                dfs(R - 1, c, visA);
            }
            for (int r = 0; r < R; r++) {
                dfs(r, 0, visP);
                dfs(r, C - 1, visA);
            }
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (visP[r][c] && visA[r][c]) {
                        res.add(Arrays.asList(r, c));
                    }
                }
            }
            return res;
        }


        public void dfs(int r, int c, boolean[][] vis) {
            vis[r][c] = true;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                //下一个坐标需要满足3个条件
                //1.在区域范围内
                //2.比上一个位置(r,c)的值要大，因为我们从外层逆着水流方向找的
                //3.没有被访问过
                if (inArea(nr, nc) && !vis[nr][nc] && heights[nr][nc] >= heights[r][c]) {
                    dfs(nr, nc, vis);
                }
            }
        }

        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }

    }


    static class _3rd {
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int R, C;

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            List<List<Integer>> res = new LinkedList<>();
            if (heights == null || heights.length == 0 || heights[0].length == 0) return res;
            R = heights.length;
            C = heights[0].length;
            boolean[][] visP = new boolean[R][C];
            boolean[][] visA = new boolean[R][C];
            Queue<int[]> qP = new LinkedList<>();
            Queue<int[]> qA = new LinkedList<>();
            for (int r = 0; r < R; r++) { //行
                qP.offer(new int[]{r, 0});
                qA.offer(new int[]{r, C - 1});
                visP[r][0] = true;
                visA[r][C - 1] = true;
            }
            for (int c = 0; c < C; c++) { //列
                qP.offer(new int[]{0, c});
                qA.offer(new int[]{R - 1, c});
                visP[0][c] = true;
                visA[R - 1][c] = true;
            }
            bfs(heights, qP, visP);
            bfs(heights, qA, visA);
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (visP[r][c] && visA[r][c]) res.add(Arrays.asList(r, c));
                }
            }
            return res;
        }

        public void bfs(int[][] heights, Queue<int[]> queue, boolean[][] vis) {
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1];
                for (int[] d : dir) {
                    int nr = r + d[0], nc = c + d[1];
                    if (inArea(nr, nc) && !vis[nr][nc] && heights[nr][nc] >= heights[r][c]) {
                        vis[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }

}
