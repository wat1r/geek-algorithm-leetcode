package com.frankcooper.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _2022 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int[][] construct2DArray(int[] original, int m, int n) {
            if (original.length != m * n) return new int[][]{};
            int[][] arr = new int[m][n];
            for (int i = 0; i < original.length; i++) {
                int r = i / n, c = i % n;
                arr[r][c] = original[i];
            }
            return arr;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int sumOfUnique(int[] nums) {
            int n = nums.length;
            int[] arr = new int[101];
            for (int a : nums) arr[a]++;
            int ans = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 1) ans += i;
            }
            return ans;
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
