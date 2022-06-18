package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _17_08 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] height = {65, 70, 56, 75, 60, 68};
            int[] weight = {100, 150, 90, 190, 95, 110};
//            Assert.assertEquals(6, handler.bestSeqAtIndex(height, weight));
            height = new int[]{1, 2, 2, 3, 4};
            weight = new int[]{1, 3, 4, 5, 7};
            handler.bestSeqAtIndex(height, weight);
        }


        public int bestSeqAtIndex(int[] height, int[] weight) {
            int n = height.length;
            int[][] arr = new int[n][2];//0位置存height， 1位置存weight
            for (int i = 0; i < n; i++) arr[i] = new int[]{height[i], weight[i]};
            //按height升序，相同时 按weight降序
            Arrays.sort(arr, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
            int res = 0;
            int[] f = new int[n];
            for (int i = 0; i < n; i++) {
                f[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (arr[i][1] > arr[j][1]) {//
                        f[i] = Math.max(f[i], f[j] + 1);
                    }
                }
                res = Math.max(res, f[i]);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] height = {65, 70, 56, 75, 60, 68};
            int[] weight = {100, 150, 90, 190, 95, 110};
//            Assert.assertEquals(6, handler.bestSeqAtIndex(height, weight));
            height = new int[]{1, 2, 2, 3, 4};
            weight = new int[]{1, 3, 4, 5, 7};
            handler.bestSeqAtIndex(height, weight);
        }


        public int bestSeqAtIndex(int[] height, int[] weight) {
            int n = height.length;
            int[][] arr = new int[n][2];//0位置存height， 1位置存weight
            for (int i = 0; i < n; i++) arr[i] = new int[]{height[i], weight[i]};
            //按height升序，相同时 按weight降序
            Arrays.sort(arr, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
            int res = 0;
            int[] f = new int[n];
            for (int[] pair : arr) {
                int i = binarySearch(f, 0, res, pair[1]);
                if (i < 0)
                    i = -(i + 1);
                f[i] = pair[1];
                if (i == res)
                    ++res;
            }
            return res;
        }


        private int binarySearch(int[] a, int fromIndex, int toIndex,
                                 int key) {
            int low = fromIndex;
            int high = toIndex - 1;

            while (low <= high) {
                int mid = (low + high) >>> 1;
                int midVal = a[mid];

                if (midVal < key)
                    low = mid + 1;
                else if (midVal > key)
                    high = mid - 1;
                else
                    return mid; // key found
            }
            return -(low + 1);  // key not found.
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
