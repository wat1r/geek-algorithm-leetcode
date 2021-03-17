package com.frankcooper.bank._401_500;

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


}
