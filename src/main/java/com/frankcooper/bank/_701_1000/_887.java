package com.frankcooper.bank._701_1000;


import com.frankcooper.Main;
import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

import java.util.Arrays;

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
//            PrintUtils.printMatrix(dp);
            for (int j = 1; j <= K; j++) dp[1][j] = 1;
//            PrintUtils.printMatrix(dp);
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
                            r = m - 1;
                        } else {
                            l = m;
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

        int INF = Integer.MAX_VALUE / 2;

        public int superEggDrop(int K, int N) {
            int[][] f = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    f[i][j] = INF;
                }
            }
            f[0][0] = 0;//0层0个鸡蛋
            for (int i = 1; i <= N; i++) f[i][1] = i;//1层以上1个鸡蛋
            for (int j = 1; j <= K; j++) f[1][j] = 1;//1层超过1个鸡蛋
            for (int i = 2; i <= N; i++) {
                for (int j = 2; j <= K; j++) {
                    for (int k = 1; k <= i; k++) {
                        //一般情况
                        f[i][j] = Math.min(f[i][j], Math.max(f[k - 1][j - 1], f[i - k][j]) + 1);
                    }
                }
            }
            return f[N][K];//返回N层K个鸡蛋的结果
        }
    }

    static class _4th {
        int INF = Integer.MAX_VALUE / 2;

        public int superEggDrop(int K, int N) {
            int[][] f = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    f[i][j] = INF;
                }
            }
            f[1][0] = 0;
            for (int i = 1; i <= N; i++) f[i][1] = i;
            for (int j = 1; j <= K; j++) f[1][j] = 1;
            for (int i = 2; i <= N; i++) {
                for (int j = 2; j <= K; j++) {
                    int l = 1, r = i;
                    while (l < r) {
                        int m = l + (r - l + 1) / 2;
                        //二分，比较t1部分和t2部分
                        int t1 = f[m - 1][j - 1], t2 = f[i - m][j];
                        if (t1 > t2) {
                            r = m - 1;
                        } else {
                            l = m;
                        }
                    }
                    f[i][j] = Math.min(f[i][j], Math.max(f[l - 1][j - 1], f[i - l][j]) + 1);
                }
            }
            return f[N][K];
        }
    }

    static class _5th {
        public int superEggDrop(int K, int N) {
            return helper(K, N);
        }

        private int helper(int k, int n) {
            //楼层为0和1的时候
            if (n == 0 || n == 1) return n;
            //鸡蛋只有1个的时候
            if (k == 1) return n;
            int res = Integer.MAX_VALUE / 2;
            for (int i = 1; i <= n; i++) {
                //碎与不碎的情况
                int t = Math.max(helper(k - 1, i - 1), helper(k, n - i)) + 1;
                res = Math.min(res, t);
            }
            return res;
        }

    }

    static class _6th {
        Integer[][] memo;

        public int superEggDrop(int K, int N) {
            memo = new Integer[K + 1][N + 1];
            return helper(K, N);
        }

        private int helper(int k, int n) {
            if (n == 0 || n == 1) return n;
            if (k == 1) return n;
            if (memo[k][n] != null) return memo[k][n];
            int res = Integer.MAX_VALUE / 2;
            for (int i = 1; i <= n; i++) {
                int t = Math.max(helper(k - 1, i - 1), helper(k, n - i)) + 1;
                res = Math.min(res, t);
            }
            return memo[k][n] = res;
        }
    }

    static class _6th_1 {
        Integer[][] memo;

        public int superEggDrop(int K, int N) {
            memo = new Integer[K + 1][N + 1];
            return helper(K, N);
        }

        private int helper(int k, int n) {
            if (n == 0 || n == 1) return n;
            if (k == 1) return n;
            if (memo[k][n] != null) return memo[k][n];
            int lo = 1, hi = n, t = 0;
            int res = Integer.MAX_VALUE / 2;
            while (lo <= hi) {
                int mid = lo + hi >> 1;
                //两部分
                int t1 = helper(k - 1, mid - 1);
                int t2 = helper(k, n - mid);
                t = Math.max(t1, t2) + 1;//去子问题最大
                if (t1 < t2) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
                res = Math.min(res, t);//更新res
            }
            return memo[k][n] = res;//记忆化
        }
    }
}
