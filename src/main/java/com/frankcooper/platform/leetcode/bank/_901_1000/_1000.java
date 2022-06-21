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
            int[][] f = new int[n + 1][n + 1];
            int[] sum = new int[n + 1];
            for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + stones[i - 1];
            for (int len = k; len <= n; len++) {
                for (int i = 1; i + len - 1 <= n; i++) {
                    int j = i + len - 1;
                    f[i][j] = Integer.MAX_VALUE;
                    for (int m = i; m < j; m += k - 1) {
                        f[i][j] = Math.min(f[i][j], f[i][m] + f[m + 1][j] + sum[j] - sum[i - 1]);
                    }
                }
            }
            return f[1][n];
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
