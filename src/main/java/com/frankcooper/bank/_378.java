package com.frankcooper.bank;

public class _378 {

    static _378 handler = new _378();

    public static void main(String[] args) {
        int[][] matrix = {{1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}};
        matrix = new int[][]{{1, 3, 5, 7, 9, 11},
                {2, 4, 6, 8, 10, 12},
                {3, 5, 7, 9, 11, 13},
                {4, 6, 8, 10, 12, 14},
                {5, 7, 9, 11, 13, 15},
                {6, 8, 10, 12, 14, 16}};
        int k = 15;
        handler.kthSmallest(matrix, k);


    }


    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        //left/right表示的具体的值而非索引
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + (right - left) >> 1;
            if (validate(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;

    }

    /**
     * 判断小于等于mid这个值的个数是否大于等于k
     *
     * @param matrix 源数组矩阵
     * @param mid    中间的值
     * @param k      目标的第k个数
     * @param n      矩阵的最大边界
     * @return
     */
    private boolean validate(int[][] matrix, int mid, int k, int n) {
        int i = n - 1, j = 0;
        int count = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                count += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return count >= k;
    }


}
