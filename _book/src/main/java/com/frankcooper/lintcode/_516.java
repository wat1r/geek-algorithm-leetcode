package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _516 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] costs = PrintUtils.processSymbol("[[14,2,11],[11,14,5],[14,3,10]]");
//            costs = PrintUtils.processSymbol("[[1,2,3]]");
            handler.minCostII(costs);

        }


        public int minCostII(int[][] costs) {
            if (costs == null || costs.length == 0) return 0;
            int n = costs.length, k = costs[0].length;
            int[][] f = new int[n][k];
            //需要初始化
            for (int i = 0; i < n; i++) Arrays.fill(f[i], Integer.MAX_VALUE);
            for (int j = 0; j < k; j++) {
                f[0][j] = costs[0][j];
            }
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    for (int m = 0; m < k; m++) {
                        if (m == j) continue;
                        f[i][j] = Math.min(f[i - 1][m] + costs[i][j], f[i][j]);
                    }
                }
            }
            int res = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                res = Math.min(res, f[n - 1][j]);
            }
            return res;

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
