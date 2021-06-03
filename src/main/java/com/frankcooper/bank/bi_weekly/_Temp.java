package com.frankcooper.bank.bi_weekly;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _Temp {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

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

            int[][] grid = PrintUtils.processSymbol("[[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]");
//            handler.getBiggestThree(grid);
            grid = PrintUtils.processSymbol("[[1,2,3],[4,5,6],[7,8,9]]");
            handler.getBiggestThree(grid);
        }


        public int[] getBiggestThree(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            PriorityQueue<Integer> pq = new PriorityQueue<>(3, (a, b) -> b - a);
//            int c = (Math.max(m, n) + 1) / 2;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    pq.offer(grid[i][j]);//直径为0的点,这个点本身
                    int c = Math.min(i, Math.min(j, Math.min(n - j - 1, m - i - 1)));
                    for (int k = 1; k <= c; k++) {
                        int sum = 0;
                        //四个顶点
                        sum += grid[i - k][j];
                        sum += grid[i + k][j];
                        sum += grid[i][j - k];
                        sum += grid[i][j + k];
                        for (int x = 1; x < k; x++) {
                            sum += grid[i - x][j - k + x];
                            sum += grid[i + x][j - k + x];
                            sum += grid[i - x][j + k - x];
                            sum += grid[i + x][j + k - x];
                        }
                        pq.offer(sum);
                    }
                }
            }
            List<Integer> res = new ArrayList<>();
            while (!pq.isEmpty()) {
                if (res.size() >= 3) break;
                if (!res.contains(pq.peek())) res.add(pq.peek());
                pq.poll();
            }
            int[] ans = new int[res.size()];
            for (int i = 0; i < ans.length; i++) ans[i] = res.get(i);
            return ans;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[] nums1 = {1, 2}, nums2 = {2, 3};
            Assert.assertEquals(2, handler.minimumXORSum(nums1, nums2));
        }


        public int minimumXORSum(int[] nums1, int[] nums2) {
            int n = nums2.length;
            int[] f = new int[1 << n];
            Arrays.fill(f, Integer.MAX_VALUE);
            f[0] = 0;
            //f[mask]表示当nums2选中的元素的状态是mask时，并且选中了前count(mask)个元素的情况下，组成的最小的异或值之和
            for (int mask = 1; mask < (1 << n); mask++) {
                for (int i = 0; i < n; i++) {
                    if (((mask >> i) & 1) == 1) {//当前的mask有元素进来
                        //  对应的nums1的元素是nums1[Integer.bitCount(mask) - 1 mask的个数少一个
                        //当前进入候选的是nums2[i]这个元素
                        //f[mask ^ (1 << i)]  是mask上一个状态
//                        f[mask] = Math.min(f[mask], f[mask ^ (1 << i)] + (nums1[Integer.bitCount(mask) - 1] ^ nums2[i]));
                        f[mask] = Math.min(f[mask], f[mask ^ (1 << i)] + (nums1[count(mask) - 1] ^ nums2[i]));
                    }
                }
            }
            return f[(1 << n) - 1];
        }


        private int count(int x) {
            int ans = 0;
            while (x != 0) {
                ans++;
                x -= (x & (-x));
            }
            return ans;
        }

    }
}
