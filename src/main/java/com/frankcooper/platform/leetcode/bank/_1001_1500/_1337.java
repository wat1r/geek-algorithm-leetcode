package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _1337 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[][] mat = PrintUtils.processSymbol("[[1,1,0,0,0],[1,1,1,1,0],[1,0,0,0,0],[1,1,0,0,0],[1,1,1,1,1]]");
            int k = 3;
//            Assert.assertArrayEquals(new int[]{2, 0, 3}, handler.kWeakestRows(mat, k));
            mat = PrintUtils.processSymbol("[[1,0],[0,0],[1,0]]");
            k = 2;
            Assert.assertArrayEquals(new int[]{1, 0}, handler.kWeakestRows(mat, k));
        }


        /**
         * @param mat
         * @param k
         * @return
         */

        public int[] kWeakestRows(int[][] mat, int k) {
            int m = mat.length, n = mat[0].length;
            int[] sum = new int[m];
            PriorityQueue<int[]> pq = new PriorityQueue<>(k, (a, b) -> (b[1] - a[1]) == 0 ? b[0] - a[0] : b[1] - a[1]);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) sum[i] += mat[i][j];
                if (!pq.isEmpty() && pq.size() > k && pq.peek()[1] > sum[i]) {
                    pq.poll();
                }
                pq.offer(new int[]{i, sum[i]});
                if (!pq.isEmpty() && pq.size() > k) pq.poll();
            }
            int[] ans = new int[k];
            while (!pq.isEmpty() && k > 0) {
                --k;
                ans[k] = pq.poll()[0];
            }
            return ans;
        }


    }

    static class _2nd {


        /**
         * int[][] arr = PrintUtils.processSymbol("[[1,0],[0,0],[1,0]]");
         * Arrays.sort(arr, (a, b) -> a[0] - b[0]);
         * Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
         * Arrays.sort(arr, new Comparator<int[]>() {
         *
         * @Override public int compare(int[] o1, int[] o2) {
         * return o1[0] - o2[0];
         * }
         * });
         **/


        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] mat = PrintUtils.processSymbol("[[1,1,0,0,0],[1,1,1,1,0],[1,0,0,0,0],[1,1,0,0,0],[1,1,1,1,1]]");
            int k = 3;
            Assert.assertArrayEquals(new int[]{2, 0, 3}, handler.kWeakestRows(mat, k));

        }


        public int[] kWeakestRows(int[][] mat, int k) {
            int m = mat.length, n = mat[0].length;
            int[][] arr = new int[m][2];//[0]记录士兵的个数，[1]记录当前的行号
            for (int i = 0; i < m; i++) {
                int cnt = binarySearch(mat[i], 0, n - 1);
                System.out.printf("%d-->%d\n", i, cnt);
                arr[i][0] = cnt;
                arr[i][1] = i;
            }
            Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            int[] ans = new int[k];
            for (int i = 0; i < k; i++) ans[i] = arr[i][1];
            return ans;
        }

        //二分拿士兵的数量,士兵都在队伍的最左侧

        /**
         * 返回每一行的1的个数
         * 上取整
         * [mid,r]这个区间是需要的
         * [l,mid-1]这个区间是需要的
         * @param arr
         * @param l
         * @param r
         * @return
         */
        private int binarySearch(int[] arr, int l, int r) {
            while (l < r) {
                int mid = (l + r + 1) / 2;//上取整
                if (arr[mid] == 0) r = mid - 1;
                else l = mid;
            }
            //l下标从0开始，要计算个数，士兵都是从左到右排序，l能越界
            return arr[l] == 0 ? 0 : l + 1;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[][] mat = PrintUtils.processSymbol("[[1,1,0,0,0],[1,1,1,1,0],[1,0,0,0,0],[1,1,0,0,0],[1,1,1,1,1]]");
            int k = 3;
            Assert.assertArrayEquals(new int[]{2, 0, 3}, handler.kWeakestRows(mat, k));
        }


        public int[] kWeakestRows(int[][] mat, int k) {
            int m = mat.length, n = mat[0].length;
            int[][] arr = new int[m][2];//[0]记录士兵的个数，[1]记录当前的行号
            for (int i = 0; i < m; i++) {
                int cnt = binarySearch(mat[i], 0, n - 1);
                System.out.printf("%d-->%d\n", i, cnt);
                arr[i][0] = cnt;
                arr[i][1] = i;
            }
            Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            int[] ans = new int[k];
            for (int i = 0; i < k; i++) ans[i] = arr[i][1];
            return ans;
        }

        //二分拿士兵的数量,士兵都在队伍的最左侧
        private int binarySearch(int[] arr, int l, int r) {
            while (l < r) {
                int mid = (l + r) / 2;
                if (arr[mid] == 1) l = mid + 1;
                else r = mid;
            }
            return arr[l] == 1 ? l + 1 : l;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
