package com.frankcooper.interview;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _01_07 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            /**
             * [
             *   [1,2,3],
             *   [4,5,6],
             *   [7,8,9]
             * ]
             */
            int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            handler.rotate(matrix);
        }


        public void rotate(int[][] matrix) {
            int N = matrix.length;
            int[][] res = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    res[j][N - 1 - i] = matrix[i][j];
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = res[i][j];
                }
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public void rotate(int[][] matrix) {
            int N = matrix.length;
            //先按对角线翻转
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    int t = matrix[j][i];
                    matrix[j][i] = matrix[i][j];
                    matrix[i][j] = t;
                }
            }
            int m = N >> 1;
            //再按中间列翻转
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < m; j++) {
                    int t = matrix[i][j];
                    matrix[i][j] = matrix[i][N - 1 - j];
                    matrix[i][N - 1 - j] = t;
                }
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
