package com.frankcooper.classify.data_structure.matrix;

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

    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[][] matrix = {{1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16}};
            handler.display(matrix);
            handler.rotate(matrix);
            handler.display(matrix);
        }


        private void rotate(int[][] matrix) {
            transpose(matrix);
            reverseColumns(matrix);
        }


        //按矩阵的的列翻转
        private void reverseColumns(int[][] matrix) {
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0, k = matrix[0].length - 1; j < k; j++, k--) {
                    int t = matrix[j][i];//[j,i] <-> [k,i]
                    matrix[j][i] = matrix[k][i];
                    matrix[k][i] = t;
                }
            }
        }


        //转置
        private void transpose(int[][] matrix) {
            for (int r = 0; r < matrix.length; r++) {
                for (int c = r; c < matrix[0].length; c++) {
                    int t = matrix[c][r];
                    matrix[c][r] = matrix[r][c];
                    matrix[r][c] = t;
                }
            }
        }


        private void display(int[][] matrix) {
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    System.out.printf("%d\t", matrix[r][c]);
                }
                System.out.print("\n");
            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[][] matrix = {{1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16}};
            handler.rotateMatrix(matrix);
        }


        private void rotateMatrix(int[][] matrix) {
            int N = matrix.length;
            for (int i = N - 1; i >= 0; i--) {
                for (int j = N - 1; j >= 0; j--) {
                    System.out.printf("%d ", matrix[i][j]);
                }
                System.out.println();
            }
        }
    }

    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            int[][] matrix = {{1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16}};
            handler.display(matrix);
            handler.rotate(matrix);
            handler.display(matrix);
        }


        private void rotate(int[][] matrix) {
            transpose(matrix);
            reverseColumns(matrix);
            transpose(matrix);
            reverseColumns(matrix);
        }


        //按矩阵的的列翻转
        private void reverseColumns(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0, k = matrix[0].length - 1; j < k; j++, k--) {
                    int t = matrix[j][i];//[j,i] <-> [k,i]
                    matrix[j][i] = matrix[k][i];
                    matrix[k][i] = t;
                }
            }
        }


        //转置
        private void transpose(int[][] matrix) {
            for (int r = 0; r < matrix.length; r++) {
                for (int c = r; c < matrix[0].length; c++) {
                    int t = matrix[c][r];
                    matrix[c][r] = matrix[r][c];
                    matrix[r][c] = t;
                }
            }
        }


        private void display(int[][] matrix) {
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    System.out.printf("%d\t", matrix[r][c]);
                }
                System.out.print("\n");
            }
        }
    }

    static class _6th {
        public static void main(String[] args) {
            _6th handler = new _6th();
            int[][] matrix = {{1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16}};
            handler.display(matrix);
            handler.rotateMatrix(matrix);
            handler.display(matrix);
            matrix = new int[][]{
                    {1, 2, 3, 4, 5},
                    {6, 7, 8, 9, 10},
                    {11, 12, 13, 14, 15},
                    {16, 17, 18, 19, 20},
                    {21, 22, 23, 24, 25}
            };
            handler.display(matrix);
            handler.rotateMatrix(matrix);
            handler.display(matrix);

        }

        private void display(int[][] matrix) {
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    System.out.printf("%d\t", matrix[r][c]);
                }
                System.out.print("\n");
            }
        }

        private void reverseRow(int[][] matrix, int r) {
            int C = matrix[r].length;
            for (int c = 0; c < C / 2; c++) {
                int t = matrix[r][c];
                matrix[r][c] = matrix[r][C - c - 1];
                matrix[r][C - c - 1] = t;
            }
        }

        private void rotateMatrix(int[][] matrix) {
            int R = matrix.length, C = matrix[0].length;
            if (R % 2 != 0) {
                reverseRow(matrix, R / 2);
            }
            for (int r = 0; r <= R / 2 - 1; r++) {
                for (int c = 0; c < C; c++) {
                    int t = matrix[r][c];
                    matrix[r][c] = matrix[R - r - 1][C - c - 1];
                    matrix[R - r - 1][C - c - 1] = t;
                }
            }
        }
    }

    static class _48_1 {
        public void rotate(int[][] matrix) {
            //考虑每一个环
            int R = matrix.length, C = matrix[0].length;
            for (int x = 0; x < R / 2; x++) {
                for (int y = x; y < R - x - 1; y++) {
                    int t = matrix[x][y];
                    matrix[x][y] = matrix[R - 1 - y][x];
                    matrix[R - 1 - y][x] = matrix[R - 1 - x][C - 1 - y];
                    matrix[R - 1 - x][C - 1 - y] = matrix[y][C - 1 - x];
                    matrix[y][C - 1 - x] = t;
                }
            }
        }
    }

    static class _48_2 {
        public void rotate(int[][] matrix) {
            transpose(matrix);
            reverseColumns(matrix);
        }

        //按矩阵的的列翻转
        private void reverseColumns(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0, k = matrix.length - 1; j < k; j++, k--) {
                    int t = matrix[i][j];//[i,j] <-> [i,k]
                    matrix[i][j] = matrix[i][k];
                    matrix[i][k] = t;
                }
            }
        }


        //转置
        private void transpose(int[][] matrix) {
            for (int r = 0; r < matrix.length; r++) {
                for (int c = r; c < matrix[0].length; c++) {
                    int t = matrix[c][r];
                    matrix[c][r] = matrix[r][c];
                    matrix[r][c] = t;
                }
            }
        }
    }

    static class _867_1 {

    }

//    static class _7th {
//
//        int MAX = 100;
//
//        // Fills temp array into mat[][] using spiral order
//// traversal.
//        void fillSpiral(int mat[][], int m, int n, int temp[]) {
//            int k = 0, l = 0;
//
//    /*  k - starting row index
//        m - ending row index
//        l - starting column index
//        n - ending column index  */
//            int tIdx = 0;  // Index in temp array
//            while (k < m && l < n) {
//                /* first row from the remaining rows */
//                for (int i = l; i < n; ++i)
//                    mat[k][i] = temp[tIdx++];
//                k++;
//
//                /* last column from the remaining columns */
//                for (int i = k; i < m; ++i)
//                    mat[i][n - 1] = temp[tIdx++];
//                n--;
//
//                /* last row from the remaining rows */
//                if (k < m) {
//                    for (int i = n - 1; i >= l; --i)
//                        mat[m - 1][i] = temp[tIdx++];
//                    m--;
//                }
//
//                /* first column from the remaining columns */
//                if (l < n) {
//                    for (int i = m - 1; i >= k; --i)
//                        mat[i][l] = temp[tIdx++];
//                    l++;
//                }
//            }
//        }
//
//        // Function to spirally traverse matrix and
//// rotate each ring of matrix by K elements
//// mat[][] --> matrix of elements
//// M     --> number of rows
//// N    --> number of columns
//        void spiralRotate(int mat[][], int M, int N, int k) {
//            // Create a temporary array to store the result
//            int[] temp = new int[M * N];
//
//    /*      s - starting row index
//            m - ending row index
//            l - starting column index
//            n - ending column index;  */
//            int m = M, n = N, s = 0, l = 0;
//
////            int start = temp;  // Start position of current ring
//            int tIdx = 0;  // Index in temp
//            while (s < m && l < n) {
//                // Initialize end position of current ring
//                int *end = start;
//
//                // copy the first row from the remaining rows
//                for (int i = l; i < n; ++i) {
//                    temp[tIdx++] = mat[s][i];
//                    end++;
//                }
//                s++;
//
//                // copy the last column from the remaining columns
//                for (int i = s; i < m; ++i) {
//                    temp[tIdx++] = mat[i][n - 1];
//                    end++;
//                }
//                n--;
//
//                // copy the last row from the remaining rows
//                if (s < m) {
//                    for (int i = n - 1; i >= l; --i) {
//                        temp[tIdx++] = mat[m - 1][i];
//                        end++;
//                    }
//                    m--;
//                }
//
//                /* copy the first column from the remaining columns */
//                if (l < n) {
//                    for (int i = m - 1; i >= s; --i) {
//                        temp[tIdx++] = mat[i][l];
//                        end++;
//                    }
//                    l++;
//                }
//
//                // if elements in current ring greater than
//                // k then rotate elements of current ring
//                if (end - start > k) {
//                    // Rotate current ring using revarsal
//                    // algorithm for rotation
//                    reverse(start, start + k);
//                    reverse(start + k, end);
//                    reverse(start, end);
//
//                    // Reset start for next ring
//                    start = end;
//                } else // There are less than k elements in ring
//                    break;
//            }
//
//            // Fill tenp array in original matrix.
//            fillSpiral(mat, M, N, temp);
//        }
//
//    }

}
