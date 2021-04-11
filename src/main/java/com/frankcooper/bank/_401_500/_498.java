package com.frankcooper.bank._401_500;

import com.frankcooper.swordoffer.utils.PrintUtils;

public class _498 {


    public static void main(String[] args) {
        _1st handler = new _1st();
        int[][] matrix = PrintUtils.processSymbol("[[1,2,3],[4,5,6],[7,8,9]]");
        matrix = PrintUtils.processSymbol("[[1]]");
        handler.findDiagonalOrder(matrix);
    }


    static class _1st {

        /**
         * https://leetcode-cn.com/problems/diagonal-traverse/solution/xiao-bai-kan-guo-lai-zui-zhi-bai-yi-li-jie-ban-ben/
         *
         * @param matrix
         * @return
         */
        public int[] findDiagonalOrder(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[]{};
            int R = matrix.length, C = matrix[0].length;
            int[] ans = new int[R * C];
            int r = 0, c = 0, idx = 0;
            for (int i = 0; i < R + C - 1; i++) {
                if (i % 2 == 0) {
                    while (r >= 0 && c < C) {
                        ans[idx++] = matrix[r][c];
                        r--;
                        c++;
                    }
                    if (c < C) {
                        r++;
                    } else {
                        r += 2;
                        c--;
                    }

                } else {
                    while (r < R && c >= 0) {
                        ans[idx++] = matrix[r][c];
                        r++;
                        c--;
                    }
                    if (r < R) {
                        c++;
                    } else {
                        c += 2;
                        r--;
                    }
                }
            }
            return ans;
        }
    }


}
