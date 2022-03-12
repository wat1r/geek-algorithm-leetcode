package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1201 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            int n = 3, a = 2, b = 3, c = 5;
            int n = 5, a = 2, b = 11, c = 13;
            handler.nthUglyNumber(n, a, b, c);
        }

        public int nthUglyNumber(int n, int a, int b, int c) {
            int[] dp = new int[n + 1];
            int i = 0, j = 0, k = 0;
            dp[0] = 1;
            for (int m = 1; m <= n; m++) {
                int t1 = a * dp[i], t2 = b * dp[j], t3 = c * dp[k];
                dp[m] = Math.min(t1, Math.min(t2, t3));
                if (dp[m] == t1) i++;
                if (dp[m] == t2) j++;
                if (dp[m] == t3) k++;
            }
            return dp[n];
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
