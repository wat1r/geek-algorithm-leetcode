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

    static class _2nd {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            int i = 0, j = n - 1;
            while (i < m && j >= 0) {
                if (target == matrix[i][j]) {
                    return true;
                } else if (target > matrix[i][j]) i++;
                else if (target < matrix[i][j]) j--;
            }
            return false;
        }
    }

    static class _3rd {

        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
            int target = 3;
            handler.searchMatrix(matrix, target);
        }


        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            int l = 0, r = m - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (matrix[mid][0] <= target) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            int row = l;
            l = 0;
            r = n - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (matrix[row][mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            int col = l;
            return matrix[row][col] == target;
        }
    }
}
