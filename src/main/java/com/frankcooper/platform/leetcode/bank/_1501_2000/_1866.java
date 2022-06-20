package com.frankcooper.platform.leetcode.bank._1501_2000;

/*import java.util.*;
import org.junit.Assert;*/
public class _1866 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        // dp[i][j] 表示在i根棍子下能看到其中的j根，排列的组合数量
        public int rearrangeSticks(int n, int k) {
            int MOD = (int) 1e9 + 7;
            long[][] dp = new long[n + 1][k + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= n; ++i) {
                // i个棍最多只能看到i个，所以 j <= min(k, i)
                for (int j = 1; j <= Math.min(k, i); ++j) {
                    //case1：把最小的木棍放在第一个位置，最小的木棍肯定会被看到，然后在剩下的i-1个木棍中找j-1个排列
                    //case2: 把最小的木棍放在除第一个位置外的任意的位置（i-1种排法）,在i-1根木棍中找j个排列
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] * (i - 1);
                    dp[i][j] %= MOD;
                }
            }
            return (int) dp[n][k];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        // dp[i][j] 表示在i根棍子下能看到其中的j根，排列的组合数量
        public int rearrangeSticks(int n, int k) {
            int MOD = (int) 1e9 + 7;
            long[] dp = new long[k + 1];
            dp[0] = 1;
            for (int i = 1; i <= n; ++i) {
                // i个棍最多只能看到i个，所以 j <= min(k, i)
                long[] t = new long[k + 1];
                for (int j = 1; j <= Math.min(k, i); ++j) {
                    //case1：把最小的木棍放在第一个位置，最小的木棍肯定会被看到，然后在剩下的i-1个木棍中找j-1个排列
                    //case2: 把最小的木棍放在除第一个位置外的任意的位置（i-1种排法）,在i-1根木棍中找j个排列
                    t[j] = dp[j - 1] + dp[j] * (i - 1);
                    t[j] %= MOD;
                }
                dp = t;
            }
            return (int) dp[k];
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
