package com.frankcooper.bank._501_1000;

import com.frankcooper.swordoffer.utils.PrintUtils;

public class _877 {

    static _877 handler = new _877();

    public static void main(String[] args) {
        handler.stoneGame(new int[]{5, 3, 4, 5});
//        handler.test();
//        handler.test1();
//        handler.test2();
        //11000011010100000
//        System.out.println(Integer.toBinaryString(100000).length());
    }


    private void test2() {
        int[][] a = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print("(" + j + "," + (i + j) + ")");
            }
            System.out.println();
        }
    }


    private void test1() {
        int[][] a = {
                {1, 5, 9, 3, 7},
                {1, 5, 9, 3, -7},
                {1, 5, 9, 3, 7},
                {1, 5, 9, 3, 7},
                {1, 5, 9, 3, 7},
                {1, 5, 9, 3, 7},
                {1, 5, 9, 3, 7},
                {1, 5, 9, 3, 7},
                {-1, 5, 9, 3, 7}
        };
        PrintUtils.printMatrix(a);
        int m = a.length;
        int n = a[0].length;
        int sum = m + n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(a[j][i - j]);
            }
            System.out.println();
        }
        System.out.println("----------------");
        for (int i = 0; i < m - n; i++) {
            for (int j = 1 + i, p = 4; j <= n + i && p >= 0; j++, p--) {

                System.out.print(a[j][p]);
            }
        }
        for (int q = 0; q < n - 1; q++) {
            for (int i = n - 1, j = m - n + 1; i > q && j < m; i--, j++) {
                System.out.print(a[m - j][i]);
            }

        }
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
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n - i; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
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


    public boolean stoneGame2nd(int[] piles) {
        int n = piles.length;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) f[i] = piles[i];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[j] = Math.max(piles[i] - f[j], piles[j] - f[j - 1]);
            }
        }
        return f[n - 1] > 0;
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
//        PrintUtils.printMatrix(f);
        return f[0][n - 1] > 0;
    }


//    private void


    /**
     * 倾斜遍历，三维
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][][] f = new int[n][n][2];
        for (int i = 0; i < n; i++) f[i][i][0] = piles[i];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                int x = j, y = i + j;
                System.out.print("(" + x + "," + (y) + ")  ");
                int left = piles[x] + f[x + 1][y][1];
                int right = piles[y] + f[x][y - 1][1];
                if (left >= right) {
                    f[x][y][0] = left;
                    f[x][y][1] = f[x + 1][y][0];
                } else {
                    f[x][y][0] = right;
                    f[x][y][1] = f[x][y - 1][0];
                }
            }
            System.out.println();
            PrintUtils.printMatrix(f, 2);
            System.out.println();
        }
//        PrintUtils.printMatrix(f);
//        return f[0][n - 1] > 0;
        PrintUtils.printMatrix(f, 2);
        return f[n - 1][n - 1][0] > f[n - 1][n - 1][1];
    }

}
