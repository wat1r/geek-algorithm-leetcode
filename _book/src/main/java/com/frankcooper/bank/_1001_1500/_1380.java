package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

public class _1380 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public List<Integer> luckyNumbers(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[] minR = new int[m];
            int MAX = (int) 1e5 + 5;
            Arrays.fill(minR, MAX);
            int[] maxC = new int[n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    minR[i] = Math.min(minR[i], matrix[i][j]);
                    maxC[j] = Math.max(maxC[j], matrix[i][j]);
                }
            }
            List<Integer> res = new ArrayList<>();
            Set<Integer> maxCSet = new HashSet<>();
            for (int x : maxC) {
                maxCSet.add(x);
            }
            for (int x : minR) {
                if (maxCSet.contains(x)) {
                    res.add(x);
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public List<Integer> luckyNumbers(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[] minR = new int[m];
            int MAX = (int) 1e5 + 5;
            Arrays.fill(minR, MAX);
            int[] maxC = new int[n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    minR[i] = Math.min(minR[i], matrix[i][j]);
                    maxC[j] = Math.max(maxC[j], matrix[i][j]);
                }
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int t = matrix[i][j];
                    if (t == minR[i] && t == maxC[j]) res.add(t);
                }
            }
            return res;
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
