package com.frankcooper.platform.leetcode.bank.bi_weekly;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class BiWeek50 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 5, 2, 4, 1};
            handler.minOperations(nums);

        }

        public int minOperations(int[] nums) {
            int res = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] <= nums[i - 1]) {
                    int delta = nums[i - 1] - nums[i] + 1;
                    res += delta;
                    nums[i] = nums[i] + delta;
                }
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] points = PrintUtils.processSymbol("[[1,3],[3,3],[5,3],[2,2]]");
            int[][] queries = PrintUtils.processSymbol("[[2,3,1],[4,3,1],[1,1,2]]");
            Assert.assertArrayEquals(handler.countPoints(points, queries), new int[]{3, 2, 2});

        }

        public int[] countPoints(int[][] points, int[][] queries) {
            int n = queries.length;
            int[] res = new int[n];
            int i = 0;
            for (int[] q : queries) {
                int x1 = q[0], y1 = q[1], r = q[2];
                int cnt = 0;
                for (int[] p : points) {
                    int x2 = p[0], y2 = p[1];
                    double dist = Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
                    if (dist <= r * 1.0) cnt++;
                }
                res[i++] = cnt;
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {0, 1, 1, 3};
            int maximumBit = 2;
            handler.getMaximumXor(nums, maximumBit);
        }

        int maximumBit;

        public int[] getMaximumXor(int[] nums, int maximumBit) {
            this.maximumBit = maximumBit;
            int p = 0, n = nums.length;
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                p ^= nums[i];
                ans[n - 1 - i] = getK(p);
            }
            return ans;
        }


        private int getK(int p) {
            int res = 0;
            for (int i = 0; i < maximumBit; i++) {
                int bit = p & 1;
                res |= ((bit == 0 ? 1 : 0) << i);
                p >>= 1;
            }
            return res;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
