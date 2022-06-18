package com.frankcooper.platform.leetcode.interview;

public class ArrayTest {
    static ArrayTest handler = new ArrayTest();

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5}, {2, 4, 6}, {5, 9, 10}};
        System.out.println(handler.find(matrix, 6));

    }

    public boolean find(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0, col = 0;
        int m = matrix.length, n = matrix[0].length;
        while (row < m && col < n) {
            while (matrix[row][col] < target && col < n) {
                col++;
            }
            while (matrix[row][col] < target && row < n) {
                row++;
            }
            while (matrix[row][col] > target && col >= 0) {
                col--;
            }
            while (matrix[row][col] > target && row >= 0) {
                row--;
            }

        }
        return false;
    }


    public boolean find2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n;
        while (low < high) {
            int mid = (low + high) / 2;
            if (matrix[mid / n][mid % n] == target) {
                return true;
            } else if (matrix[mid / n][mid % n] < target) {
                low = mid + 1;
            } else if (matrix[mid / n][mid % n] > target) {
                high = mid;
            }
        }
        return false;
    }


}
