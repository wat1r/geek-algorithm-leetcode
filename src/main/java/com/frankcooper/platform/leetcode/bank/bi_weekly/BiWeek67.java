package com.frankcooper.platform.leetcode.bank.bi_weekly;


import org.junit.Assert;

import java.util.*;

public class BiWeek67 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int[] maxSubsequence(int[] nums, int k) {
            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(new int[]{i, nums[i]});
            }
            list.sort(((o1, o2) -> o2[1] - o1[1]));//按value值降序排列
            List<int[]> resList = list.subList(0, k);
            resList.sort(((o1, o2) -> o1[0] - o2[0]));
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = resList.get(i)[1];
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] security = {5, 3, 3, 3, 5, 6, 2};
            int time = 2;
            handler.goodDaysToRobBank(security, time);
        }

        public List<Integer> goodDaysToRobBank(int[] security, int time) {
            int n = security.length;
            int[] a = new int[n], b = new int[n];
            for (int i = 0; i < n; i++) {
                if (i > 0 && security[i] <= security[i - 1]) {
                    a[i] = 1;
                }
                if (i < n - 1 && security[i] <= security[i + 1]) {
                    b[i] = 1;
                }
            }
            int[] sa = new int[n + 1], sb = new int[n + 1];
            for (int i = 0; i < n; i++) {
                sa[i + 1] = sa[i] + a[i];
                sb[i + 1] = sb[i] + b[i];
            }
            List<Integer> res = new ArrayList<>();
            for (int i = time; i < n - time; i++) {
                int t1 = sa[i + 1] - sa[i - time + 1];
                int t2 = sb[i + time] - sb[i];
                if (t1 == time && t2 == time) {
                    res.add(i);
                }
            }
            return res;
        }
    }

    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[][] bombs = {{1, 1, 100000}, {100000, 100000, 1}};
            Assert.assertEquals(1, handler.maximumDetonation(bombs));
        }

        int maxx = 1;
        boolean[] memo;

        public int maximumDetonation(int[][] bombs) {
            memo = new boolean[bombs.length];
            for (int i = 0; i < bombs.length; i++) {
                dfs(bombs, i);
                Arrays.fill(memo, false);
            }
            return maxx;
        }

        public int dfs(int[][] bombs, int i) {
            if (memo[i]) return 0;
            memo[i] = true;
            int ans = 1;
            for (int j = 0; j < bombs.length; j++) {
                if (memo[j]) continue;
                if (check(bombs, i, j)) {
                    ans += dfs(bombs, j);
                }
            }
            maxx = Math.max(maxx, ans);
            return ans;
        }


        //long 类型 防止乘数较大 溢出
        //case :{{1, 1, 100000}, {100000, 100000, 1}}
        private boolean check(int[][] bombs, int a, int b) {
            int[] aa = bombs[a], bb = bombs[b];
            int x1 = aa[0], y1 = aa[1], r1 = aa[2];
            int x2 = bb[0], y2 = bb[1];
            long t1 = (long) (x1 - x2) * (x1 - x2) + (long) (y1 - y2) * (y1 - y2);
            long t2 = (long) r1 * r1;
            return t1 <= t2;
        }
    }


}
