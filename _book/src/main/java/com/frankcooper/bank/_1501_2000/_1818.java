package com.frankcooper.bank._1501_2000;

import java.util.*;

public class _1818 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        int MOD = (int) 1e9 + 7;

        public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {

            int n = nums1.length;
            TreeSet<Integer> treeSet = new TreeSet<>();
            long all = 0, ans = 0;
            for (int i = 0; i < n; i++) {
                treeSet.add(nums1[i]);
                all += Math.abs(nums1[i] - nums2[i]);
            }
            long maxn = 0;
            for (int i = 0; i < n; i++) {
                int x = treeSet.floor(nums2[i]);
                long cur = Math.abs(nums1[i] - nums2[i]);
                if (x != treeSet.last()) {
                    maxn = Math.max(maxn, cur - Math.abs(x - nums2[i]));
                }
                if (x != treeSet.first()) {
                    maxn = Math.max(maxn, cur - Math.abs(-x - nums2[i]));
                }
            }
            return (int) (all - maxn) % MOD;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums1 = new int[]{1, 7, 5};
            int[] nums2 = new int[]{2, 3, 5};
            nums1 = new int[]{53, 48, 14, 71, 31, 55, 6, 80, 28, 19, 15, 40, 7, 21, 69, 15, 5, 42, 86, 15, 11, 54, 44, 62, 9, 100, 2, 26, 81, 87, 87, 18, 45, 29, 46, 100, 20, 87, 49, 86, 14, 74, 74, 52, 52, 60, 8, 25, 21, 96, 7, 90, 91, 42, 32, 34, 55, 20, 66, 36, 64, 67, 44, 51, 4, 46, 25, 57, 84, 23, 10, 84, 99, 33, 51, 28, 59, 88, 50, 41, 59, 69, 59, 65, 78, 50, 78, 50, 39, 91, 44, 78, 90, 83, 55, 5, 74, 96, 77, 46};
            nums2 = new int[]{39, 49, 64, 34, 80, 26, 44, 3, 92, 46, 27, 88, 73, 55, 66, 10, 4, 72, 19, 37, 40, 49, 40, 58, 82, 32, 36, 91, 62, 21, 68, 65, 66, 55, 44, 24, 78, 56, 12, 79, 38, 53, 36, 90, 40, 73, 92, 14, 73, 89, 28, 53, 52, 46, 84, 47, 51, 31, 53, 22, 24, 14, 83, 75, 97, 87, 66, 42, 45, 98, 29, 82, 41, 36, 57, 95, 100, 2, 71, 34, 43, 50, 66, 52, 6, 43, 94, 71, 93, 61, 28, 84, 7, 79, 23, 48, 39, 27, 48, 79};
            handler.minAbsoluteSumDiff(nums1, nums2);
        }

        int MOD = (int) 1e9 + 7;

        public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
            int n = nums1.length;
            TreeSet<Integer> ts = new TreeSet<>();
            long sum = 0;
            for (int i = 0; i < n; i++) {
                ts.add(nums1[i]);
                sum += Math.abs(nums1[i] - nums2[i]);
            }
            int maxDecrease = 0;
            for (int i = 0; i < n; i++) {
                if (nums1[i] != nums2[i]) {
                    int diff = Math.abs(nums1[i] - nums2[i]);
                    int max = 0;
                    Integer ceil = ts.ceiling(nums2[i]);
                    Integer floor = ts.floor(nums2[i]);
                    if (ceil != null) {
                        max = Math.max(max, diff - Math.abs(nums2[i] - ceil));
                    }
                    if (floor != null) {
                        max = Math.max(max, diff - Math.abs(nums2[i] - floor));
                    }
                    maxDecrease = Math.max(maxDecrease, max);

                }
            }
            return (int) ((sum - maxDecrease) % MOD);

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
