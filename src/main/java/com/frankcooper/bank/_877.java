package com.frankcooper.bank;

import com.frankcooper.swordoffer.utils.PrintUtils;

public class _877 {

    static _877 handler = new _877();

    public static void main(String[] args) {
//        handler.stoneGame(new int[]{5, 3, 4, 5});
//        handler.test();
        //11000011010100000
        System.out.println(Integer.toBinaryString(100000).length());
    }


    private void test() {
        int n = 9;
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = i;//数组赋值
            }
        }
        PrintUtils.printMatrix(a);
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = 0; j < n; j++) {
                if ((i - j) >= 0 && (i - j) < n) {
                    System.out.print(a[j][i - j]);
                }
            }
            System.out.println();
        }
        for (int i = 0 - n - 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (((i + j) >= 0) && ((i + j) < n)) {
                    System.out.print(a[j][i + j]);
                }
            }
            System.out.println();
        }
    }


    public boolean stoneGame1st(int[] piles) {
        int n = piles.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) f[i][i] = piles[i];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = Math.max(piles[i] - f[i + 1][j], piles[j] - f[i][j - 1]);
            }
        }
        PrintUtils.printMatrix(f);
        return f[0][n - 1] > 0;
    }


//    private void


    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][][] f = new int[n][n][2];
        for (int i = 0; i < n; i++) f[i][i][0] = piles[i];
        for (int i = 0; i < n; i++) {
            int k = i;
            for (int j = 0; j < n - i; j++) {


                int left = piles[i] + f[i + 1][j][1];
                int right = piles[j] + f[i][j - 1][1];
                if (left > right) {
                    f[i][j][0] = left;
                    f[i][j][1] = f[i + 1][j][0];
                } else {
                    f[i][j][0] = right;
                    f[i][j][1] = f[i][j - 1][1];
                }
            }
        }
//        PrintUtils.printMatrix(f);
//        return f[0][n - 1] > 0;
        PrintUtils.printMatrix(f, 2);
        return false;
    }

}
