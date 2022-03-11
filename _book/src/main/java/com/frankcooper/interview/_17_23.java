package com.frankcooper.interview;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _17_23 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] mat = PrintUtils.processSymbol("[[1,0,1],[0,0,1],[0,0,1]]");
            int[] expected = new int[]{1, 0, 2};
//            Assert.assertArrayEquals(expected, handler.findSquare(mat));
            mat = PrintUtils.processSymbol("[[0,1,1],[1,0,1],[1,1,0]]");
            expected = new int[]{0, 0, 1};
            Assert.assertArrayEquals(expected, handler.findSquare(mat));

        }

        //r 最小 c 最小
        public int[] findSquare(int[][] mat) {
            int R = mat.length, C = mat[0].length;
            //记录当前点[r,c]到右边，能探到的黑色点的距离
            int[][] maxRow = new int[R][C];
            //记录当前点[r,c]到下边，能探到的黑色点的距离
            int[][] maxCol = new int[R][C];
            //从右下角的点开始遍历
            for (int r = R - 1; r >= 0; --r) {
                for (int c = C - 1; c >= 0; --c) {
                    if (mat[r][c] == 0) {//本身是黑色点，开始计算,只有本身一个黑色点的话，边长是0
                        maxRow[r][c] = 1;
                        if (c < C - 1) maxRow[r][c] += maxRow[r][c + 1];
                        maxCol[r][c] = 1;
                        if (r < R - 1) maxCol[r][c] += maxCol[r + 1][c];
                    }
                }
            }
//            PrintUtils.printMatrix(maxRow);
//            PrintUtils.printMatrix(maxCol);
            int[] res = new int[3];//结果集
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (mat[r][c] == 0) {
//                        System.out.printf("%d:%d\n", r, c);
                        //到下面和到右边所能探到的最大距离
                        int upper = Math.min(maxRow[r][c], maxCol[r][c]);
                        //小于当前结果集的边长的，没有意义
                        int bound = res[2];
                        for (int size = upper; size > bound; size--) {
                            if (maxRow[r + size - 1][c] >= size && maxCol[r][c + size - 1] >= size) {
                                res[0] = r;
                                res[1] = c;
                                res[2] = size;
                                break;//最大边结束了，break掉
                            }
                        }
                    }
                }
            }
            //特判一下
            if (res[2] == 0) return new int[]{};
            return res;
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
