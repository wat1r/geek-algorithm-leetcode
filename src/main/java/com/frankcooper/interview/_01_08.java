package com.frankcooper.interview;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _01_08 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public void setZeroes(int[][] matrix) {
            int R = matrix.length, C = matrix[0].length;
            boolean[] rows = new boolean[R];//标记行为0的数组
            boolean[] cols = new boolean[C];//标记列为0的数组
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (matrix[i][j] == 0) {
                        rows[i] = true;
                        cols[j] = true;
                    }
                }
            }
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (rows[i] || cols[j]) matrix[i][j] = 0;
                }
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

            handler.setZeroes(PrintUtils.processSymbol("[[1,0,3]]"));
        }


        public void setZeroes(int[][] matrix) {
            boolean firstRowHasZero = false;
            boolean firstColHasZero = false;
            int R = matrix.length, C = matrix[0].length;
            for (int i = 0; i < R; i++) {
                if (matrix[i][0] == 0) {
                    firstColHasZero = true;
                    break;
                }
            }
            for (int j = 0; j < C; j++) {
                if (matrix[0][j] == 0) {
                    firstRowHasZero = true;
                    break;
                }
            }
            for (int i = 1; i < R; i++) {
                for (int j = 1; j < C; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
            //注意从1开始
            for (int i = 1; i < R; i++) {
                for (int j = 1; j < C; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
                }
            }
            for (int i = 0; i < R; i++) {
                if (firstColHasZero) matrix[i][0] = 0;
            }
            for (int j = 0; j < C; j++) {
                if (firstRowHasZero) matrix[0][j] = 0;
            }
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.setZeroes(PrintUtils.processSymbol("[[1,0,3]]"));
        }

        public void setZeroes(int[][] matrix) {
            boolean isFirstRowHaveZero = false;
            boolean isFirstColHaveZero = false;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][0] == 0) {
                    isFirstColHaveZero = true;
                }
            }

            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0) {
                    isFirstRowHaveZero = true;
                }
            }

            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[0][j] = 0;
                        matrix[i][0] = 0;
                    }
                }
            }

            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[i].length; j++) {
                    if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }

            for (int i = 0; i < matrix.length; i++) {
                if (isFirstColHaveZero) {
                    matrix[i][0] = 0;
                }
            }

            for (int j = 0; j < matrix[0].length; j++) {
                if (isFirstRowHaveZero) {
                    matrix[0][j] = 0;
                }
            }

        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
