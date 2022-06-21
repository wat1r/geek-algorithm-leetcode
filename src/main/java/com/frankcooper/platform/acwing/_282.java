package com.frankcooper.platform.acwing;

import java.util.Scanner;

public class _282 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        static class Main {
            public static void main(String[] args) {
                Scanner in = new Scanner(System.in);
                int n = in.nextInt();
                int[] stones = new int[n];
                int[] sum = new int[n + 1];
                for (int i = 1; i <= n; i++) {
                    stones[i - 1] = in.nextInt();
                    sum[i] = sum[i - 1] + stones[i - 1];
                }
                int[][] f = new int[n + 1][n + 1];
                for (int len = 2; len <= n; len++) {
                    for (int i = 1; i + len - 1 <= n; i++) {
                        int j = i + len - 1;
                        f[i][j] = Integer.MAX_VALUE;
                        for (int k = i; k < j; k++) {
                            f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j] + sum[j] - sum[i - 1]);
                        }
                    }
                }
                System.out.println(f[1][n]);
            }


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
