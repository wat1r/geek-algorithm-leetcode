package com.frankcooper.lintcode;

import java.util.*;

import org.junit.Assert;

public class _1272 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        int n;

        public int kthSmallest(int[][] matrix, int k) {
            this.n = matrix.length;
            int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
            while (lo < hi) {
                int mid = lo + hi >> 1;
                int count = check(matrix, mid);
                if (count < k) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo;
        }


        /**
         * 判断matrix中小于等于target的数量
         *
         * @param matrix
         * @param target
         * @return
         */
        private int check(int[][] matrix, int target) {
            int ans = 0;
            int i = n - 1, j = 0;
            while (i >= -0 && j < n) {
                if (matrix[i][j] <= target) {
                    ans += i + 1;
                    j++;
                } else {
                    i--;
                }
            }
            return ans;
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
