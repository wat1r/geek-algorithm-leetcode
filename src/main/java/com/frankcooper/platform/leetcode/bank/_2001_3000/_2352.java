package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _2352 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] grid = PrintUtils.processSymbol("[[3,1,2,2],[1,4,4,4],[2,4,2,2],[2,5,2,2]]");
            handler.equalPairs(grid);

        }


        public int equalPairs(int[][] grid) {
            int n = grid.length;
            Map<String, Integer> rows = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String row = "";
                for (int j = 0; j < n; j++) {
                    row += grid[i][j];
                    row += "_";
                }
                rows.put(row, rows.getOrDefault(row, 0) + 1);
            }
            int res = 0;
            Map<String, Integer> cols = new HashMap<>();
            for (int j = 0; j < n; j++) {
                String col = "";
                for (int i = 0; i < n; i++) {
                    col += grid[i][j];
                    col += "_";
                }
                cols.put(col, cols.getOrDefault(col, 0) + 1);
                System.out.println(col);
                if (rows.containsKey(col)) {
                    res += rows.get(col);
                }
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
