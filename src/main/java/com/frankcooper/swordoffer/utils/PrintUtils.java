package com.frankcooper.swordoffer.utils;

/**
 * @Date 2020/9/13
 * @Author Frank Cooper
 * @Description
 */
public class PrintUtils {


    public static void main(String[] args) {
        int[][] matrix = PrintUtils.processSymbol("[[7,3,6],[1,4,5],[9,8,2]]");
        PrintUtils.printMatrix(matrix);

    }


    public static int[][] processSymbol(String str) {
        str = str.replace("[[", "").replace("]]", "");

        String[] arr = str.split("],\\[");
        int R = arr.length;
        int C = 0;
        if (arr[0] != null) C = arr[0].split(",").length;
        int[][] matrix = new int[R][C];
        for (int r = 0; r < R; r++) {
            String[] seg = arr[r].split(",");
            for (int c = 0; c < C; c++) {
                matrix[r][c] = Integer.parseInt(seg[c]);
            }
        }
        printMatrix(matrix, 6);
        return matrix;
    }


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


    public static void printMatrix(double[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                System.out.print(String.format("%3s", matrix[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


    public static void printMatrix(int[][] matrix, int d) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
//                if (Integer.MAX_VALUE == matrix[i][j]) {
//                    System.out.print("INF ");
//                } else {
//                    System.out.print(String.format("%" + d + "s", matrix[i][j]) + " ");
//                }
                System.out.print(String.format("%" + d + "s", matrix[i][j]) + " ");
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


    public static void printMatrix(int[][][] matrix, int sense) {
        int m = matrix.length, n = matrix[0].length, p = matrix[0][0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {

                System.out.print(String.format("%6s", "(" + matrix[i][j][0] + "," + matrix[i][j][1] + ")") + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


}
