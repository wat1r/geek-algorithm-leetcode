package com.frankcooper.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _241 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            for (int i = 0; i < m; i++) {
                int l = 0, r = n - 1;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (matrix[i][mid] < target) l = mid + 1;
                    else r = mid;
                }
                if (matrix[i][l] == target) return true;
            }
            return false;
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
