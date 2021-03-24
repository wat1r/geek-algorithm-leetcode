package com.frankcooper.bank._1_100;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _52 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        int n;

        public int totalNQueens(int n) {
            this.n = n;
            Set<Integer> cols = new HashSet<>();
            Set<Integer> dia1 = new HashSet<>();
            Set<Integer> dia2 = new HashSet<>();
            return backtrack(0, cols, dia1, dia2);
        }


        public int backtrack(int row, Set<Integer> cols, Set<Integer> dia1, Set<Integer> dia2) {
            if (row == n) return 1;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (cols.contains(i)) continue;
                if (dia1.contains(row - i)) continue;
                if (dia2.contains(row + i)) continue;
                cols.add(i);
                dia1.add(row - i);
                dia2.add(row + i);
                count += backtrack(row + 1, cols, dia1, dia2);
                cols.remove(i);
                dia1.remove(row - i);
                dia2.remove(row + i);
            }
            return count;
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
