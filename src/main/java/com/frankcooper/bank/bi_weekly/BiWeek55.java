package com.frankcooper.bank.bi_weekly;

import java.util.*;

import org.junit.Assert;

public class BiWeek55 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean canBeIncreasing(int[] nums) {
            for (int i = -1; i < nums.length; i++) {
                boolean f = false;
                int prev = 0;
                for (int j = 0; j < nums.length; j++) {
                    if (i == j) continue;
                    if (prev >= nums[j]) f = true;
                    prev = nums[j];
                }
                if (!f) return true;
            }
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public String removeOccurrences(String s, String part) {

            while (true) {
                int idx = s.indexOf(part);
                if (idx == -1) break;
                s = s.substring(0, idx) + s.substring(idx + part.length());
            }
            return s;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        //状态机DP
        public long maxAlternatingSum(int[] nums) {
            // f[i][0] 表示[]0...i-1]个数中选偶数个数的最大交替和
            //f[i][1]  表示[]0...i-1]个数中选奇数个数的最大交替和
            int n = nums.length;
            long[][] f = new long[n + 1][2];
            for (int i = 0; i <= n; i++) Arrays.fill(f[i], Integer.MIN_VALUE >> 1);//防止溢出
            f[0][0] = 0;
            for (int i = 1; i <= n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] - nums[i - 1]);
                f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] + nums[i - 1]);
            }
            return Math.max(f[n][0], f[n][1]);
        }
    }


    static class _3rd_1 {
        public long maxAlternatingSum(int[] nums) {
            int n = nums.length;
            long[] f = new long[2];
            f[0] = 0;
            f[1] = Integer.MIN_VALUE >> 1;
            for (int i = 1; i <= n; i++) {
                f[0] = Math.max(f[0], f[1] - nums[i - 1]);
                f[1] = Math.max(f[1], f[0] + nums[i - 1]);
            }
            return Math.max(f[0], f[1]);
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        class MovieRentingSystem {


            //[0]表示价格，[1]表示商店shopi

            PriorityQueue<int[]> nonRentQueue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

            PriorityQueue<int[]> alreadyRentQueue = new PriorityQueue<>();

            public MovieRentingSystem(int n, int[][] entries) {
                for (int[] e : entries) {
                    int shop = e[0], movie = e[1], price = e[2];
                    nonRentQueue.offer(new int[]{price, shop, movie});
                }


            }

            public List<Integer> search(int movie) {
                List<Integer> res = new ArrayList<>();
                int k = 5;
                for (int[] e : nonRentQueue) {
                    if (k == 0) break;
                    if (movie == e[2]) res.add(e[1]);
                    k--;
                }
                return res;
            }

            public void rent(int shop, int movie) {


            }

            public void drop(int shop, int movie) {

            }

            public List<List<Integer>> report() {


                return null;
            }
        }


    }
}
