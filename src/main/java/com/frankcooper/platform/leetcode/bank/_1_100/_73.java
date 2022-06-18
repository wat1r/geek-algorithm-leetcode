package com.frankcooper.platform.leetcode.bank._1_100;


import java.util.HashSet;
import java.util.Set;

public class _73 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public void setZeroes(int[][] matrix) {
            int R = matrix.length, C = matrix[0].length;
            Set<Integer> rows = new HashSet<>();
            Set<Integer> cols = new HashSet<>();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (matrix[i][j] == 0) {
                        rows.add(i);
                        cols.add(j);
                    }
                }
            }
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (rows.contains(i) || cols.contains(j)) matrix[i][j] = 0;
                }
            }

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public void setZeroes(int[][] matrix) {
            int R = matrix.length, C = matrix[0].length;
            boolean r0f = false, c0f = false;
            for (int i = 0; i < R; i++) {//取第一列
                if (matrix[i][0] == 0) {
                    c0f = true;
                    break;
                }
            }
            for (int j = 0; j < C; j++) {//取第一行
                if (matrix[0][j] == 0) {
                    r0f = true;
                    break;
                }
            }
            for (int i = 1; i < R; i++) {//用第一行和第一列做标记
                for (int j = 1; j < C; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = matrix[0][j] = 0;
                    }
                }
            }
            for (int i = 1; i < R; i++) {
                for (int j = 1; j < C; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
                }
            }
            if (r0f) {
                for (int j = 0; j < C; j++) matrix[0][j] = 0;
            }
            if (c0f) {
                for (int i = 0; i < R; i++) matrix[i][0] = 0;
            }

        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
