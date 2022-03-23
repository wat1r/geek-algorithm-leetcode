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


    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] matrix = {
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16}
            };
            int N = matrix.length;
            handler.display(matrix, N);
            handler.rotateMatrix(matrix, N);
            handler.display(matrix, N);
        }


        private void rotateMatrix(int[][] matrix, int N) {
            //考虑每一个环
            for (int x = 0; x < N / 2; x++) {
                for (int y = x; y < N - x - 1; y++) {
                    int t = matrix[x][y];
                    matrix[x][y] = matrix[y][N - 1 - x];
                    matrix[y][N - 1 - x] = matrix[N - 1 - x][N - 1 - y];
                    matrix[N - 1 - x][N - 1 - y] = matrix[N - 1 - y][x];
                    matrix[N - 1 - y][x] = t;
                }
            }
        }


        private void display(int[][] matrix, int N) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    System.out.printf("%d\t", matrix[r][c]);
                }
                System.out.print("\n");
            }
        }
    }
}
