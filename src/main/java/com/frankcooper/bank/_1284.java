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


    class _2nd {
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {0, 0}};
        int R, C;
        Integer INF = Integer.MAX_VALUE;
        int res = INF;

        public int minFlips(int[][] mat) {
            R = mat.length;
            C = mat[0].length;
            dfs(mat, 0, 0);
            return res == INF ? -1 : res;
        }

        private void dfs(int[][] mat, int idx, int flipCnt) {
            if (idx == R * C) {
                boolean f = true;
                for (int r = 0; r < R; r++) {
                    for (int c = 0; c < C; c++) {
                        if (mat[r][c] != 0) {
                            f = false;
                        }
                    }
                }
                if (f) {
                    res = Math.min(res, flipCnt);
                }
                return;
            }
            int r = idx / C, c = idx % C;
            dfs(mat, idx + 1, flipCnt);
            transform(mat, r, c, R, C);
            dfs(mat, idx + 1, flipCnt + 1);
            transform(mat, r, c, R, C);
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
            //判断当前的十进制数是否访问过
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

        /**
         * 转换，将当前点(r,c)翻转，其周边的四个点翻转
         *
         * @param matrix 矩阵
         * @param r      当前点的行
         * @param c      当前点的列
         * @param R      总行
         * @param C      总列
         */
        private void transform(int[][] matrix, int r, int c, int R, int C) {
            for (int[] dir : directions) {
                int nextR = r + dir[0], nextC = c + dir[1];
                if (inArea(nextR, nextC)) {
                    matrix[nextR][nextC] ^= 1;
                }
            }
        }

        //在矩阵区域范围内
        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }

        /**
         * 当前矩阵转化为一个十进制的数
         *
         * @param matrix 二维矩阵
         * @param R      矩阵的行数
         * @param C      矩阵的列数
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
         * 当前十进制的数反解析为一个矩阵
         *
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
