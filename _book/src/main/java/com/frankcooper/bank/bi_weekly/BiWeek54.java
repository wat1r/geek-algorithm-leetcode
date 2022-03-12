package com.frankcooper.bank.bi_weekly;

import com.frankcooper.utils.PrintUtils;

import java.util.Arrays;

/*import java.util.*;
import org.junit.Assert;*/
public class BiWeek54 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


            int[][] ranges = PrintUtils.processSymbol("[[25,42],[7,14],[2,32],[25,28],[39,49],[1,50],[29,45],[18,47]]");
            int left = 15;
            int right = 38;
//            handler.isCovered(ranges, left, right);
            ranges = PrintUtils.processSymbol("[[15,36],[15,23],[15,44],[30,49],[2,19],[27,36],[7,42],[12,41]]");
            left = 19;
            right = 47;
            handler.isCovered(ranges, left, right);

        }

        public boolean isCovered(int[][] ranges, int left, int right) {
            Arrays.sort(ranges, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            for (int i = left; i <= right; i++) {
                boolean f = false;
                for (int[] x : ranges) {
                    if (i >= x[0] && i <= x[1]) {
                        f = true;
                        break;
                    }
                }
                if (!f) return false;
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] chalk = {3, 4, 1, 2};
            int k = 25;
//            handler.chalkReplacer(chalk, k);
            chalk = new int[]{1};
            k = 1000000000;
            handler.chalkReplacer(chalk, k);

        }


        public int chalkReplacer(int[] chalk, int k) {
            long sum = 0;
            for (int x : chalk) sum += x;
            int l = 0, r = k;
            while (l < r) {
                int mid = (l + r + 1) >> 1;//上取整
                if (sum * mid > k) r = mid - 1;
                else l = mid;
            }
            k -= sum * l;
            for (int i = 0; i < chalk.length; i++) {
                k -= chalk[i];
                if (k < 0) return i;
            }
            return 0;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();

            int[][] mat = PrintUtils.processSymbol("[[5,1,6],[5,4,3],[2,7,3]]");
//            handler.check(mat);
            int[][] grid = PrintUtils.processSymbol("[[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]");
            handler.largestMagicSquare(grid);

        }

        public int largestMagicSquare(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int res = 1;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int k = Math.min(m - i, n - j);
                    while (k >= 1) {
                        if (res >= k) break;
                        if (check(grid, i, j, k)) {
                            res = k;
                        }
                        k--;
                    }
                }
            }
            return res;
        }


        /**
         * 计算行列主副对角线值书否相等
         *
         * @param mat
         * @param r
         * @param c
         * @param k
         * @return
         */
        private boolean check(int[][] mat, int r, int c, int k) {
            int diag1 = 0, diag2 = 0;
            int[] rows = new int[k];
            int[] cols = new int[k];
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    rows[i] += mat[r + i][c + j];
                    cols[j] += mat[r + i][c + j];
                    if (i == j) diag1 += mat[r + i][c + j];
                    if ((i + j) == k - 1) diag2 += mat[r + i][c + j];
                }
            }
            if (diag1 != diag2) return false;
            for (int i = 0; i < k; i++) {
                if (rows[i] != diag1 || cols[i] != diag1) return false;
            }
            return true;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int minOperationsToFlip(String expression) {

            return 0;
        }
    }
}
