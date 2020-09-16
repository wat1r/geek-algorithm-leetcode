package com.frankcooper.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            boolean[][] visitedP = new boolean[m][n];
            boolean[][] visitedA = new boolean[m][n];

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
                    if (visitedP[i][j] && visitedA[i][j]) {
                        resList.add(Arrays.asList(i, j));
                    }
                }
            }
            return resList;
        }


        public void dfs(int i, int j, boolean[][] visited) {
            visited[i][j] = true;
            for (int[] dir : directions) {
                int nextI = i + dir[0], nextJ = j + dir[1];
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


}
