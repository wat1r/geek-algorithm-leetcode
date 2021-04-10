package com.frankcooper.bank._501_1000;


import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _887 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            Assert.assertEquals(handler.superEggDrop(2, 6), 3);
        }


        int INF = Integer.MAX_VALUE / 2;

        public int superEggDrop(int K, int N) {
            int[][] dp = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    dp[i][j] = INF;
                }
            }
            for (int i = 1; i <= N; i++) dp[i][1] = i;
            for (int j = 1; j <= K; j++) dp[1][j] = 1;
            dp[1][0] = 0;
//            PrintUtils.printMatrix(dp);
            for (int i = 2; i <= N; i++) {
                for (int j = 2; j <= K; j++) {
                    for (int k = 1; k <= i; k++) {
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[k - 1][j - 1], dp[i - k][j]) + 1);
                    }
                }
            }
//            PrintUtils.printMatrix(dp);
            return dp[N][K];
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        int INF = Integer.MAX_VALUE / 2;

        public int superEggDrop(int K, int N) {
            int[][] dp = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    dp[i][j] = INF;
                }
            }
            for (int i = 1; i <= N; i++) dp[i][1] = i;
            for (int j = 1; j <= K; j++) dp[1][j] = 1;
            dp[1][0] = 0;
//            PrintUtils.printMatrix(dp);
            for (int i = 2; i <= N; i++) {
                for (int j = 2; j <= K; j++) {
                    int l = 1, r = i;
                    while (l < r) {
                        int m = l + (r - l + 1) / 2;
                        int b1 = dp[m - 1][j - 1], b2 = dp[i - m][j];
                        if (b1 > b2) {
                            r = m-1;
                        }else {
                            l  = m;
                        }
                    }
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[l - 1][j - 1], dp[i - l][j]) + 1);

//                    for (int k = 1; k <= i; k++) {
//                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[k - 1][j - 1], dp[i - k][j]) + 1);
//                    }
                }
            }
//            PrintUtils.printMatrix(dp);
            return dp[N][K];
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }
}
