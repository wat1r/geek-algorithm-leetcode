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


            Map<Integer, PriorityQueue<int[]>> movie2NonRent = new HashMap<>();//k是每个电影，v是每个电影对应的店铺价格信息
            Map<Integer, int[]>[] shops;//[i]是店铺的编号， k是每个电影，v是每个电影对应的店铺价格信息
            //[0]表示价格，[1]表示商店shopi
            PriorityQueue<int[]> rentQueue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    if (a[2] != b[2]) return a[2] - b[2];
                    else if (a[0] != b[0]) return a[0] - b[0];
                    return a[1] - b[1];
                }
            });
            Set<int[]> set = new HashSet<>();
//            PriorityQueue<int[]> nonRentQueue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

//            PriorityQueue<int[]> alreadyRentQueue = new PriorityQueue<>();

            public MovieRentingSystem(int n, int[][] entries) {
                shops = new Map[n];
                Arrays.fill(shops, new HashMap<>());
                for (int[] e : entries) {
                    int shop = e[0], movie = e[1], price = e[2];
                    PriorityQueue<int[]> pq = movie2NonRent.getOrDefault(movie, new PriorityQueue<>((a, b) -> a[2] == b[2] ? a[0] - b[0] : a[2] - b[2]));
                    pq.offer(e);
                    shops[shop].put(movie, e);
                }
            }

            public List<Integer> search(int movie) {
                List<Integer> res = new ArrayList<>();
                PriorityQueue<int[]> nonRentQueue = movie2NonRent.get(movie);
                if (nonRentQueue == null || nonRentQueue.isEmpty()) return res;
                int k = 5;
                for (int[] e : nonRentQueue) {
                    if (k == 0) break;
                    if (!set.contains(e[0])) set.add(e);
                    res.add(e[0]);
                    k--;
                }
                return res;
            }

            public void rent(int shop, int movie) {
                int[] e = shops[shop].get(movie);
                set.add(e);
                rentQueue.offer(e);

            }

            public void drop(int shop, int movie) {
                int[] e = shops[shop].get(movie);
                set.remove(e);
                rentQueue.remove(e);
            }

            public List<List<Integer>> report() {
                int k = 5;
                PriorityQueue<int[]> t = rentQueue;
                List<List<Integer>> res = new ArrayList<>();
                Stack<int[]> stack = new Stack<>();
                while (!t.isEmpty() && k > 0) {
                    int[] arr = t.poll();
                    stack.add(arr);
                    List<Integer> ll = new ArrayList<>();
                    ll.add(arr[0]);
                    ll.add(arr[1]);
                    res.add(ll);
                    k--;
                }
                while (!stack.isEmpty())
                    rentQueue.add(stack.pop());
                return res;

            }
        }


    }
}
