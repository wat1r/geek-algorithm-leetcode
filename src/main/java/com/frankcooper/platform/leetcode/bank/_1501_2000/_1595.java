package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _1595 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] A = PrintUtils.processSymbol("[[15,96],[36,2]]");
            List<List<Integer>> cost = new ArrayList<>();
            for (int[] a : A) {
                List<Integer> c = new ArrayList<>();
                cost.add(c);
                for (int i : a) {
                    c.add(i);
                }
            }
            handler.connectTwoGroups(cost);

        }

        public int connectTwoGroups(List<List<Integer>> cost) {
            int m = cost.size(), n = cost.get(0).size();
            int[][] costMatrix = new int[m][1 << n];
            for (int k = 0; k < m; k++) {
                for (int i = 0; i < (1 << n); i++) {
                    int sum = 0;
                    for (int j = 0; j < n; j++) {
                        if ((i & (1 << j)) > 0) {
                            sum += cost.get(k).get(j);
                        }
                    }
                    costMatrix[k][i] = sum;
                }
            }
            int[][] f = new int[m][1 << n];
            for (int i = 1; i < m; i++) {
                Arrays.fill(f[i], Integer.MAX_VALUE);
            }
            f[0] = costMatrix[0];
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < (1 << n); j++) {
                    for (int k = 1; k < (1 << n); k++) {
                        f[i][j | k] = Math.min(f[i][j | k], f[i - 1][k] + costMatrix[i][j]);
                    }
                }
            }
            return f[m - 1][(1 << n) - 1];
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
