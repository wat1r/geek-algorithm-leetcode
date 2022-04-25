package com.frankcooper.bank._1001_1500;

import org.junit.Assert;

public class _1310 {
    static _1310 handler = new _1310();

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 8};
        int[][] queries = {{0, 1}, {1, 2}, {0, 3}, {3, 3}};
        Assert.assertArrayEquals(new int[]{2, 7, 14, 8}, handler.xorQueries(arr, queries));
        arr = new int[]{16};
        queries = new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}};
        handler.xorQueries(arr, queries);


    }

    /**
     * 方法一：前缀和
     * 我们用数组 pre 表示数组 arr 的「前缀异或和」，即
     * <p>
     * pre[0] = 0
     * pre[i] = arr[0] ^ arr[1] ^ ... ^ arr[i - 1]
     * 其中 ^ 表示异或（xor）操作。这样以来，当我们要计算 arr[Li] 到 arr[Ri] 的异或值时，我们可以通过
     * <p>
     * pre[Li] ^ pre[Ri + 1] = (arr[0] ^ ... ^ arr[Li - 1]) ^ (arr[0] ^ ... ^ arr[Ri])
     * = (arr[0] ^ ... ^ arr[Li - 1]) ^ (arr[0] ^ ... ^ arr[Li - 1]) ^ (arr[Li] ^ ... ^ arr[Ri]) （异或运算的结合律）
     * = 0 ^ (arr[Li] ^ ... ^ arr[Ri]) （异或运算的逆运算，即 a ^ a = 0）
     * = arr[Li] ^ ... ^ arr[Ri]
     * <p>
     *
     * @param arr
     * @param queries
     * @return
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length, m = queries.length;
        int[] pre = new int[n + 1];
        pre[0] = 0;
        int sum = arr[0];
        for (int i = 1; i <= n; i++) {
            pre[i] ^= sum;
            if (i < n) sum ^= arr[i];
        }

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = pre[queries[i][0]] ^ pre[queries[i][1] + 1];
        }
        return res;
    }

    static class _1st {
        public int[] xorQueries(int[] arr, int[][] queries) {
            int n = arr.length;
            int[] pre = new int[n + 1];
            pre[0] = 0;
            for (int i = 1; i <= n; i++) pre[i] = pre[i - 1] ^ arr[i - 1];
            int m = queries.length;
            int[] res = new int[m];
            for (int i = 0; i < m; i++) {
                int l = queries[i][0], r = queries[i][1];
                res[i] = pre[l] ^ pre[r + 1];
            }
            return res;
        }
    }

    static class _2nd {
        public int[] xorQueries(int[] arr, int[][] queries) {
            int n = arr.length;
            int[] pre = new int[n + 1];
            for (int i = 0; i < n; i++) pre[i + 1] = pre[i] ^ arr[i];
            int m = queries.length;
            int[] ans = new int[m];
            for (int i = 0; i < m; i++) {
                int[] q = queries[i];
                ans[i] = pre[q[1] + 1] ^ pre[q[0]];
            }
            return ans;
        }
    }

}
