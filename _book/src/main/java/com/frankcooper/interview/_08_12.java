package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import org.junit.Assert;

public class _08_12 {

    static class _1st {


        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        int n;
        List<List<String>> res = new ArrayList<>();
        Set<Integer> cols = new HashSet<>();
        Set<Integer> diag1 = new HashSet<>();
        Set<Integer> diag2 = new HashSet<>();

        public List<List<String>> solveNQueens(int n) {
            this.n = n;
            int[] queens = new int[n];
            Arrays.fill(queens, -1);
            backtrack(queens, 0);
            return res;
        }


        private void backtrack(int[] queens, int row) {
            if (row == n) {
                res.add(build(queens));
                return;
            }
            for (int i = 0; i < n; i++) {
                if (cols.contains(i)) continue;
                if (diag1.contains(row - i)) continue;
                if (diag2.contains(row + i)) continue;
                queens[row] = i;
                cols.add(i);
                diag1.add(row - i);
                diag2.add(row + i);
                backtrack(queens, row + 1);
                queens[row] = -1;
                cols.remove(i);
                diag1.remove(row - i);
                diag2.remove(row + i);
            }
        }


        private List<String> build(int[] queens) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[queens[i]] = 'Q';
                res.add(new String(row));
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
