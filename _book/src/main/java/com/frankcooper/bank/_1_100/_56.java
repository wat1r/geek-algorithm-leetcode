package com.frankcooper.bank._1_100;

import java.util.*;

import org.junit.Assert;

public class _56 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int[][] merge(int[][] arr) {
            List<int[]> res = new ArrayList<>();
            if (arr == null || arr.length == 0) return res.toArray(new int[0][]);
            Arrays.sort(arr, (a, b) -> a[0] - b[0]);
            int n = arr.length, i = 0;
            while (i < n) {
                int start = arr[i][0], end = arr[i][1];
                while (i < n - 1 && end >= arr[i + 1][0]) {
                    end = Math.max(end, arr[i + 1][1]);
                    i++;
                }
                res.add(new int[]{start, end});
                i++;
            }
            return res.toArray(new int[0][]);
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
