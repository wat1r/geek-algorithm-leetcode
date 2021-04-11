package com.frankcooper.mixed.skill;

import com.frankcooper.swordoffer.utils.PrintUtils;

public class IteratorOne {
    static IteratorOne iteratorOne = new IteratorOne();

    public static void main(String[] args) {
        _1st handler = new _1st();
        handler.printOne();
        int[][] matrix = PrintUtils.processSymbol("[[1,2,3,4],[5,1,2,3],[9,5,1,2]]");
        iteratorOne.isToeplitzMatrix(matrix);

    }



    private void print(){

    }




    public boolean isToeplitzMatrix(int[][] matrix) {
//
        int R = matrix.length, C = matrix[0].length;
        int b1;
        for (int c = C - 1; c >= 0; c--) {
            b1 = matrix[0][c];
            for (int r = 0; r < R; r++) {
                if (r + c >= C) break;
                if (matrix[r][r + c] != b1) return false;
                System.out.print(matrix[r][r + c] + " ");
            }
            System.out.println();
        }
        int b2;
        for (int c = C - 1; c >= 0; c--) {
            b2 = matrix[0][c];
            for (int r = 0; r < R; r++) {
                if (r - c < 0) continue;
//                if (matrix[r][r - c] != b2) return false;
                System.out.print(matrix[r][r - c] + " ");
            }
            System.out.println();
        }
        return true;
    }

    /**
     * https://www.iteye.com/blog/shmilyaw-hotmail-com-1769105
     */
    static class _1st {

        public void printOne() {
            int[][] vec = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

            //左下-右上方向 从中间开始 向右下方遍历
            //i 代表的是步长 j代表是中间的斜轴
            for (int i = 0; i < vec.length; i++) {
                for (int j = 0; j < vec.length; j++) {
                    if (j + i >= vec.length) break;
                    System.out.print(vec[j][j + i] + " ");
                }
                System.out.println();
            }
            System.out.println("-----------------------------");
            //左下-右上方向 从右上方开始 向中间遍历
            //i 代表的是步长 j代表是中间的斜轴
            for (int i = vec.length - 1; i >= 0; i--) {
                for (int j = 0; j < vec.length; j++) {
                    if (j + i >= vec.length) break;
                    System.out.print(vec[j][j + i] + " ");
                }
                System.out.println();
            }
            System.out.println("-----------------------------");
            //左下-右上方向 从中间开始 向左下方遍历
            //i 代表的是步长 j代表是中间的斜轴
            for (int i = 0; i < vec.length; i++) {
                for (int j = 0; j < vec.length; j++) {
                    if (j - i < 0) continue;
                    System.out.print(vec[j][j - i] + " ");
                }
                System.out.println();
            }
            System.out.println("-----------------------------");
            //左下-右上方向 从左下方开始 向中间白能力
            //i 代表的是步长 j代表是中间的斜轴
            for (int i = vec.length - 1; i >= 0; i--) {
                for (int j = 0; j < vec.length; j++) {
                    if (j - i < 0) continue;
                    System.out.print(vec[j][j - i] + " ");
                }
                System.out.println();
            }
            System.out.println("-----------------------------");
        }
    }

}
