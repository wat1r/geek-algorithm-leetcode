package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _373 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         * 373. 查找和最小的K对数字 Medium
         *
         * @param nums1
         * @param nums2
         * @param k
         * @return
         */
        public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            PriorityQueue<Pair> queue = new PriorityQueue<>(k);
            for (int i = 0; i < Math.min(nums1.length, k); i++) {
                for (int j = 0; j < Math.min(nums2.length, k); j++) {
                    Pair temp = new Pair(nums1[i], nums2[j]);
                    if (queue.size() < k) {
                        queue.add(temp);
                    } else if (queue.peek().sum > temp.sum) {
                        queue.poll();
                        queue.add(temp);
                    }
                }
            }
            List<int[]> resList = new ArrayList<>();
            while (!queue.isEmpty()) {
                resList.add(queue.poll().A);
            }
            return resList;
        }


        class Pair implements Comparable<Pair> {
            int[] A = new int[2];
            int sum;

            public Pair(int row, int col) {
                A[0] = row;
                A[1] = col;
                this.sum = row + col;
            }


            @Override
            public int compareTo(Pair o) {
                return o.sum - this.sum;
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /**
         * 373. 查找和最小的K对数字 Medium
         *
         * @param nums1
         * @param nums2
         * @param k
         * @return
         */
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            PriorityQueue<Pair> queue = new PriorityQueue<>(k);
            for (int i = 0; i < Math.min(nums1.length, k); i++) {
                for (int j = 0; j < Math.min(nums2.length, k); j++) {
                    Pair temp = new Pair(nums1[i], nums2[j]);
                    if (queue.size() < k) {
                        queue.add(temp);
                    } else if (queue.peek().sum > temp.sum) {
                        queue.poll();
                        queue.add(temp);
                    }
                }
            }
            List<List<Integer>> resList = new ArrayList<>();
            while (!queue.isEmpty()) {
                resList.add(queue.poll().A);
            }
            return resList;
        }


        class Pair implements Comparable<Pair> {
            List<Integer> A = new ArrayList<>();
            int sum;

            public Pair(int row, int col) {
                A.add(row);
                A.add(col);
                this.sum = row + col;
            }


            @Override
            public int compareTo(Pair o) {
                return o.sum - this.sum;
            }
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
