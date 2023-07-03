package com.frankcooper.platform.leetcode.bank._201_300;

/*import java.util.*;
import org.junit.Assert;*/
public class _221 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
            handler.maximalSquare(matrix);


        }

        public int maximalSquare(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
            int R = matrix.length, C = matrix[0].length;
            int[][] f = new int[R][C];
            int maxL = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    f[r][c] = matrix[r][c] == '1' ? 1 : 0;
                    maxL = Math.max(maxL, f[r][c]);
                }
            }

            for (int r = 1; r < R; r++) {
                for (int c = 1; c < C; c++) {
                    if (matrix[r][c] == '1') {
                        // System.out.printf("%d,%d\n",r,c);
                        f[r][c] = Math.min(Math.min(f[r - 1][c], f[r][c - 1]), f[r - 1][c - 1]) + 1;
                        // System.out.printf("%d\n", f[r][c]);
                        maxL = Math.max(maxL, f[r][c]);
                    }
                }
            }
            return maxL * maxL;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
