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


    public static void printMatrix(int[][][] matrix) {
        int m = matrix.length, n = matrix[0].length, p = matrix[0][0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < p; ++k) {
                    System.out.print(String.format("%3s", matrix[i][j][k]) + " ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


}
