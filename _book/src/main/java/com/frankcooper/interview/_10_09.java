package com.frankcooper.interview;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _10_09 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] matrix = PrintUtils.processSymbol("[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]");
            int target = 5;
            Assert.assertTrue(handler.searchMatrix(matrix, target));

        }


        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
            int M = matrix.length, N = matrix[0].length;
            int i = 0, j = N - 1;
            while (i < M && j >= 0) {
                if (matrix[i][j] == target) return true;
                else if (matrix[i][j] > target) j--;
                else i++;
            }
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
            int M = matrix.length, N = matrix[0].length;
            int i = M - 1, j = 0;
            while (i >= 0 && j < N) {
                if (matrix[i][j] == target) return true;
                else if (matrix[i][j] > target) i--;
                else j++;
            }
            return false;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
//
//        public boolean searchMatrix(int[][] matrix, int target) {
//            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
//            int M = matrix.length, N = matrix[0].length;
//            int i = 0, j = N - 1;
//            while (i < M && j >= 0) {
//                int l = 0, r = j;
//
//            }
//
//            return false;
//        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
