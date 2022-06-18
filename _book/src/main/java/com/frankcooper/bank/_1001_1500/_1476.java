package com.frankcooper.platform.leetcode.bank._1001_1500;

public class _1476 {


    class _1st {
        class SubrectangleQueries {

            int[][] matrix;

            public SubrectangleQueries(int[][] rectangle) {
                matrix = rectangle;

            }

            public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
                for (int r = row1; r <= row2; ++r)
                    for (int c = col1; c <= col2; ++c)
                        matrix[r][c] = newValue;
            }

            public int getValue(int row, int col) {
                return matrix[row][col];
            }
        }
    }


}
