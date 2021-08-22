package com.frankcooper.bank._1_100;

/**
 * @Date 2020/7/3
 * @Author Frank Cooper
 * @Description
 */
public class _74 {
    static _74 handler = new _74();

    public static void main(String[] args) {

    }


    public boolean searchMatrix(int[][] matrix, int target) {
        int R = matrix.length, C = matrix[0].length;
        int i = 0, j = C - 1;
        while (i < R && j >= 0) {
            if (matrix[i][j] > target) j--;
            else if (matrix[i][j] < target) i++;
            else if (matrix[i][j] == target) return true;
        }
        return false;
    }

    static class _1st {
        public boolean searchMatrix(int[][] matrix, int target) {
            int R = matrix.length, C = matrix[0].length;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (matrix[i][j] == target) return true;
                }
            }
            return false;

        }
    }
}
