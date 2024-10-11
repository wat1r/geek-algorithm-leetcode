package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _48 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[][] m = PrintUtils.processSymbol("[[1,2,3],[4,5,6],[7,8,9]]");
            handler.rotate(m);

        }


        public void rotate(int[][] matrix) {
            print(matrix);
            transpose(matrix);
            print(matrix);
            reverseColumns(matrix);
            print(matrix);
        }

        private void print(int[][] matrix) {
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    System.out.printf("%d ", matrix[r][c]);
                }
                System.out.println();
            }
        }

        //按矩阵的的列翻转
        private void reverseColumns(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0, k = matrix.length - 1; j < k; j++, k--) {
                    int t = matrix[i][j];//[i,j] <-> [i,k]
                    matrix[i][j] = matrix[i][k];
                    matrix[i][k] = t;
                }
            }
        }


        //转置
        private void transpose(int[][] matrix) {
            for (int r = 0; r < matrix.length; r++) {
                for (int c = r; c < matrix[0].length; c++) {
                    int t = matrix[c][r];
                    matrix[c][r] = matrix[r][c];
                    matrix[r][c] = t;
                }
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] m = PrintUtils.processSymbol("[[1,2,3],[4,5,6],[7,8,9]]");
            handler.rotate(m);
        }

        public void rotate(int[][] matrix) {
            int n = matrix.length;
            // 水平翻转
            for (int i = 0; i < n / 2; ++i) {
                for (int j = 0; j < n; ++j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[n - i - 1][j];
                    matrix[n - i - 1][j] = temp;
                }
            }
            print(matrix);
            // 主对角线翻转
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < i; ++j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
            print(matrix);
        }

        private void print(int[][] matrix) {
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    System.out.printf("%d ", matrix[r][c]);
                }
                System.out.println();
            }
            System.out.println("-----------------------------------");
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
