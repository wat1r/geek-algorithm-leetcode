package com.frankcooper.bank._1_100;

/*import java.util.*;
import org.junit.Assert;*/
public class _70 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int climbStairs(int n) {
            if (n <= 2) return n;
            int[] f = new int[n];
            f[0] = 1;
            f[1] = 2;
            for (int i = 2; i < n; i++) {
                f[i] = f[i - 1] + f[i - 2];
            }
            return f[n - 1];
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        int[] memo;

        public int climbStairs(int n) {
            memo = new int[n + 1];
            return helper(n);
        }


        private int helper(int n) {
            if (memo[n] != 0) return memo[n];
            if (n == 1) return 1;
            if (n == 2) return 2;
            return memo[n] = (helper(n - 1) + helper(n - 2));
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
