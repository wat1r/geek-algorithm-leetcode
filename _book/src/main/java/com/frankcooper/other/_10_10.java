package com.frankcooper.other;

import java.util.ArrayList;
import java.util.*;

public class _10_10 {


    static _10_10 handler = new _10_10();

    public static void main(String[] args) {
        List<Integer> arrList = new ArrayList<Integer>();
        arrList.add(3);
        arrList.add(5);
        arrList.add(7);
        arrList.add(2);
        Collections.sort(arrList);
        int idx = Collections.binarySearch(arrList, 6);

        System.out.println("end");


    }


    static class _3rd {
        class FenwickTree {
            int n;
            int[] C;

            //初始化
            public FenwickTree(int n) {
                this.n = n;
                this.C = new int[n];
            }

            // 单点更新：将 i 位置与其直接关联的 C 都更新一遍
            public void update(int i) {
                while (i < n) {
                    C[i]++;
                    i += lowbit(i);
                }
            }

            //传进来的值-1过，查询之前有多少个数
            //区间查询：查询小于等于 i 的元素个数
            public int query(int i) {
                int sum = 0;
                while (i >= 1) {
                    sum += C[i];
                    i -= lowbit(i);
                }
                return sum;
            }

            //算lowbit
            public int lowbit(int x) {
                return x & (-x);
            }
        }

        class StreamRank {
            FenwickTree fenwickTree;

            public StreamRank() {
                fenwickTree = new FenwickTree(50_005);
            }

            public void track(int x) {
                fenwickTree.update(x + 1);
            }

            public int getRankOfNumber(int x) {
                return fenwickTree.query(x + 1);
            }
        }

    }


    static class _2nd {


        static _2nd handler = new _2nd();

        public static void main(String[] args) {
            String[] op = {"StreamRank", "getRankOfNumber", "track", "getRankOfNumber", "track", "track", "track", "getRankOfNumber", "getRankOfNumber"};
            int[][] arr = {{}, {1}, {0}, {0}, {2}, {3}, {2}, {2}, {3}};
            StreamRank streamRank = null;
            for (int i = 0; i < op.length; i++) {
                switch (op[i]) {
                    case "StreamRank":
                        streamRank = new StreamRank();
                        break;
                    case "getRankOfNumber":
                        streamRank.getRankOfNumber(arr[i][0]);
                        break;
                    case "track":
                        streamRank.track(arr[i][0]);
                        break;
                    default:
                        break;
                }
            }
        }


        static class StreamRank {
            int N = 50_005;
            int[] C;
            int idx;


            public StreamRank() {
                C = new int[N];
                idx = 0;
            }

            public void track(int x) {
                for (int i = x + 1; i <= N; i += lowbit(i)) {
                    C[i]++;
                }
            }

            public int lowbit(int x) {
                return x & (-x);
            }

            public int getRankOfNumber(int x) {
                int res = 0;
                for (int i = x + 1; i > 0; i -= lowbit(i)) {
                    res += C[i];
                }
                return res;
            }

        }

    }


    static class _1st {

        static _1st handler = new _1st();

        public static void main(String[] args) {
            String[] op = {"StreamRank", "getRankOfNumber", "track", "getRankOfNumber", "track", "track", "track", "getRankOfNumber", "getRankOfNumber"};
            int[][] arr = {{}, {1}, {0}, {0}, {2}, {3}, {2}, {2}, {3}};
            StreamRank streamRank = null;
            for (int i = 0; i < op.length; i++) {
                switch (op[i]) {
                    case "StreamRank":
                        streamRank = new StreamRank();
                        break;
                    case "getRankOfNumber":
                        streamRank.getRankOfNumber(arr[i][0]);
                        break;
                    case "track":
                        streamRank.track(arr[i][0]);
                        break;
                    default:
                        break;
                }
            }
        }

        static class StreamRank {
            private List<Integer> list;

            public StreamRank() {
                list = new ArrayList<>();
            }

            public void track(int x) {
                System.out.printf("track:%d\n", x);
                int idx = Collections.binarySearch(list, x);
                if (idx < 0) {
                    idx = -idx - 1;
                }
                list.add(idx, x);
            }

            public int getRankOfNumber(int x) {
                System.out.printf("getRankOfNumber:%d\n", x);
                int idx = Collections.binarySearch(list, x);
                if (idx < 0) {
                    idx = -idx - 1;
                }
                //这里的 x >= list.get(idx) 是为了处理 0 1 2 2  3  当 getRankOfNumber(2)的时候
                while (idx < list.size() && x >= list.get(idx)) {
                    idx++;
                }
                return idx;
            }
        }
    }

}
