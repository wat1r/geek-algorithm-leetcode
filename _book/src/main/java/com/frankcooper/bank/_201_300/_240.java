package com.frankcooper.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _240 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean searchMatrix(int[][] mat, int target) {
            int R = mat.length, C = mat[0].length;
            int r = 0, c = C - 1;
            while (r < R && c >= 0) {
                if (mat[r][c] == target) return true;
                else if (mat[r][c] < target) r++;
                else if (mat[r][c] > target) c--;
            }
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public boolean searchMatrix(int[][] mat, int target) {

            return false;
        }

        //找当前的二维数组的target的下标索引
        private int search(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l <= r) {

            }
            return 0;
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
