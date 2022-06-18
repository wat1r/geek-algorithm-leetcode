package com.frankcooper.platform.acwing;


import java.util.Scanner;

public class _1365 {

    static class _1st {
        static long[][] f = new long[40][1000];


        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int m = n * (n + 1) / 2;
            if ((m % 2) == 1) {
                System.out.println(0);
                return;
            }
            m >>= 1;
            f[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    f[i][j] = f[i - 1][j];
                    if (j >= i) f[i][j] += f[i - 1][j - i];
                }
            }
            System.out.println(f[n][m] / 2);
        }
    }


    /**
     * 一维空间DP
     */

    static class _2nd {

        static long[] f = new long[1000];

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int m = n * (n + 1) / 2;
            if ((m % 2) == 1) {
                System.out.println(0);
                return;
            }
            m >>= 1;
            f[0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = m; j >= i; --j) {
                    f[j] += f[j - i];
                }
            }
            System.out.println(f[m] / 2);

        }
    }


}
