package com.frankcooper.lintcode;

/**
 * 同leetcode 256
 */
public class _515 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int minCost(int[][] costs) {
            if (costs == null || costs.length == 0) return 0;
            int n = costs.length;
            int[][] f = new int[n][3];
            f[0][0] = costs[0][0];
            f[0][1] = costs[0][1];
            f[0][2] = costs[0][2];
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.min(f[i - 1][1], f[i - 1][2]) + costs[i][0];
                f[i][1] = Math.min(f[i - 1][0], f[i - 1][2]) + costs[i][1];
                f[i][2] = Math.min(f[i - 1][0], f[i - 1][1]) + costs[i][2];
            }
            return Math.min(f[n - 1][0], Math.min(f[n - 1][1], f[n - 1][2]));
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
