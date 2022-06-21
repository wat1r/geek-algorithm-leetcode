package com.frankcooper.platform.leetcode.bank._901_1000;

/*import java.util.*;
import org.junit.Assert;*/
public class _1000 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int mergeStones(int[] stones, int k) {
            int n = stones.length;
            if ((n - 1) % (k - 1) != 0) return -1;
            int[][] dp = new int[n + 1][n + 1];
            int[] sum = new int[n + 1];
            for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + stones[i - 1];
            for (int len = k; len <= n; len++) {
                for (int i = 1; i + len - 1 <= n; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int p = i; p < j; p += k - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][p] + dp[p + 1][j]);
                    }
                    if ((j - i) % (k - 1) == 0) {
                        dp[i][j] += sum[j] - sum[i - 1];
                    }
                }
            }
            return dp[1][n];
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
