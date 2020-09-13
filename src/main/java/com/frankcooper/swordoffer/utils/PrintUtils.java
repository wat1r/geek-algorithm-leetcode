package com.frankcooper.swordoffer.utils;

/**
 * @Date 2020/9/13
 * @Author Frank Cooper
 * @Description
 */
public class PrintUtils {

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                System.out.print(String.format("%3s", matrix[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }
}
