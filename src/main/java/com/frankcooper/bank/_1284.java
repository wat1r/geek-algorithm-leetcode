package com.frankcooper.bank;


import com.frankcooper.swordoffer.utils.PrintUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Hard
 */
public class _1284 {

    static _1284 handler = new _1284();


    public static void main(String[] args) {
        handler.test();
    }


    private void test() {
        _1st test = new _1st();
        int[][] matrix = {{0, 0, 1},
                {1, 0, 0},
                {0, 1, 1}};
        int R = 3;
        int C = 3;
//        test.encode(matrix, R, C);
//        test.decode(99, R, C);

        matrix = new int[][]{{0, 0}, {0, 1}};
        test.minFlips(matrix);


    }


    class _1st {


        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {0, 0}};
        int R, C;

        public int minFlips(int[][] mat) {
            R = mat.length;
            C = mat[0].length;
            int start = encode(mat, R, C);
            if (0 == start) return 0;
            int level = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            Set<Integer> visited = new HashSet<>();
            visited.add(start);
            while (!queue.isEmpty()) {
                level++;
                int size = queue.size();
                for (int k = 0; k < size; k++) {
                    int[][] currMatrix = decode(queue.poll(), R, C);
//                    System.out.printf("currMatrix:\n");
//                    PrintUtils.printMatrix(currMatrix);
                    for (int r = 0; r < R; r++) {
                        for (int c = 0; c < C; c++) {
                            transform(currMatrix, r, c, R, C);
//                            System.out.printf("r:%d,c:%d\n", r, c);
//                            PrintUtils.printMatrix(currMatrix);
                            int currX = encode(currMatrix, R, C);
                            if (0 == currX) {
                                return level;
                            }
                            if (!visited.contains(currX)) {
                                queue.offer(currX);
                                visited.add(currX);
                            }
                            transform(currMatrix, r, c, R, C);//复原状态
//                            System.out.printf("recover\n");
//                            PrintUtils.printMatrix(currMatrix);
                        }
                    }

                }


            }


            return -1;
        }


        private void transform(int[][] matrix, int r, int c, int R, int C) {
            for (int[] dir : directions) {
                int nextR = r + dir[0], nextC = c + dir[1];
                if (inArea(nextR, nextC)) {
                    matrix[nextR][nextC] ^= 1;
                }
            }
        }


        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }

        /**
         *
         * @param matrix 二维矩阵
         * @param R 矩阵的行数
         * @param C 矩阵的列数
         * @return
         */
        private int encode(int[][] matrix, int R, int C) {
            int x = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    x = x * 2 + matrix[r][c];
                }
            }
            return x;
        }

        /**
         * @param x 源数
         * @param R 矩阵的行数
         * @param C 矩阵的列数
         * @return
         */
        private int[][] decode(int x, int R, int C) {
            int[][] matrix = new int[R][C];
            for (int r = R - 1; r >= 0; r--) {
                for (int c = C - 1; c >= 0; c--) {
                    matrix[r][c] = x & 1;
                    x >>= 1;
                }
            }
            return matrix;
        }


    }


}
