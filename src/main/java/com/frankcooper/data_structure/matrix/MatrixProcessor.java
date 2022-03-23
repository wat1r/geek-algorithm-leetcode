package com.frankcooper.data_structure.matrix;

public class MatrixProcessor {
    public static void main(String[] args) {

    }


    static class _1st {


        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] source = {{1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12}};
            int m = source.length, n = source[0].length;
            handler.display(source, m, n);
            int[][] dest = new int[n][m];
            handler.rotate(m, n, source, dest);
            handler.display(dest, n, m);
        }


        /**
         * @param m      source的 row
         * @param n      source的 col
         * @param source 源矩阵
         * @param dest   目标矩阵
         */
        private void rotate(int m, int n, int[][] source, int[][] dest) {
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    dest[c][m - 1 - r] = source[r][c];
                }
            }
        }


        private void display(int[][] matrix, int R, int C) {
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    System.out.printf("%d\t", matrix[r][c]);
                }
                System.out.print("\n");
            }
        }

    }
}
