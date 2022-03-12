package com.frankcooper.lintcode;


import java.util.*;

import org.junit.Assert;


public class _115 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int numDistinct(String s, String t) {
            if (s == null && t == null) return 0;
            int m = s.length(), n = t.length();
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++) dp[i][0] = 1;
            for (int j = 1; j <= n; j++) dp[0][j] = 0;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] += dp[i - 1][j - 1];
                }
            }
            return dp[m][n];
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
