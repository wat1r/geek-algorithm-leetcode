package com.frankcooper.platform.leetcode.bank._2001_3000;

/*import java.util.*;
import org.junit.Assert;*/
public class _2140 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public long mostPoints(int[][] questions) {
            int n = questions.length;
            long[] dp = new long[n + 1];
            for (int i = n - 1; i >= 0; i--) {
                dp[i] = Math.max(dp[i + 1], questions[i][0] + dp[Math.min(n, i + questions[i][1] + 1)]);
            }
            return dp[0];
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
