package com.frankcooper.platform.leetcode.bank._1501_2000;


// import java.util.*;
// import org.junit.Assert;


import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

import java.util.Arrays;

public class _1751 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        class Pair {
            //开始时间，结束时间，会议价值
            int s, e, v;

            public Pair(int s, int e, int v) {
                this.s = s;
                this.e = e;
                this.v = v;
            }
        }


        public int maxValue(int[][] events, int K) {
            Arrays.sort(events, (o1, o2) -> o1[1] - o2[1]);
            int N = events.length;
            //预处理组装
            Pair[] p = new Pair[N + 1];
            for (int i = 1; i <= N; i++) p[i] = new Pair(events[i - 1][0], events[i - 1][1], events[i - 1][2]);
            //`dp[i][j]`表示在前`i`个会议中，最多只能参加`j`个会议，所能获取到的最大价值
            int[][] dp = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    //不参加i这个会议
                    dp[i][j] = dp[i - 1][j];
                    //二分找最靠近i的且其结束时间比i的开始时间小的那个会议，返回l这个会议下标
                    int l = 0, r = i - 1;
                    while (l < r) {
                        int m = l + (r - l + 1) / 2;//取右中位数
                        if (p[m].e < p[i].s) l = m;
                        else r = m - 1;//右动
                    }
                    //参加i这个会议
                    dp[i][j] = Math.max(dp[i][j], p[i].v + dp[l][j - 1]);
                }
            }
//            PrintUtils.printMatrix(dp);
            return dp[N][K];
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int maxValue(int[][] events, int k) {
            int n = events.length;
            //因为递归是自顶向下，按会议的开始时间从小到大排序，区别于自底向上的dp 填表的方式
            Arrays.sort(events, (o1, o2) -> o1[0] - o2[0]);
            return helper(events, 0, n, k);
        }

        private int helper(int[][] events, int curr, int n, int k) {
            //退出递归的出口，当curr到末尾或者剩余参加会议的机会或是次数k用完了
            if (curr >= n || k <= 0) return 0;
            int i = 0;//找curr之后满足条件最靠近curr这个会议的 下个会议
            for (i = curr + 1; i < n; i++) {
                if (events[i][0] > events[curr][1]) break;
            }
            //参加当前的会议，下个会议是 i 此 i 会议开始的时间大于当前会议 curr的结束时间 此时k用掉了一次，-1
            //不参加当前会议，跳到下个会议  此时k没有使用 保持不变
            int ans = Math.max(helper(events, i, n, k - 1) + events[curr][2], helper(events, curr + 1, n, k));
            return ans;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[][] events = PrintUtils.processSymbol("[[1,2,4],[3,4,3],[2,3,1]]");
            int k = 2;
            Assert.assertEquals(handler.maxValue(events, k), 7);
            events = PrintUtils.processSymbol("[[21,77,43],[2,74,47],[6,59,22],[47,47,38],[13,74,57],[27,55,27],[8,15,8]]");
            k = 4;
            Assert.assertEquals(handler.maxValue(events, k), 57);
        }

        Integer[][] memo;

        public int maxValue(int[][] events, int k) {
            int n = events.length;
            //        Arrays.sort(events, (e1, e2) -> (e1[0] == e2[0] ? e1[1]-e2[1] : e1[0]-e2[0]));
            Arrays.sort(events, (o1, o2) -> o1[0] - o2[0]);
            memo = new Integer[n + 1][k + 1];
            return helper(events, 0, n, k);
        }

        private int helper(int[][] events, int curr, int n, int k) {
            if (curr >= n || k <= 0) return 0;
            //如果已经被计算过，直接返回
            if (memo[curr][k] != null) return memo[curr][k];
            int i = 0;
            for (i = curr + 1; i < n; i++) {
                if (events[i][0] > events[curr][1]) break;
            }
            //参加当前的会议，下个会议是 i 此 i 会议开始的时间大于当前会议 curr的结束时间 此时k用掉了一次，-1
            //不参加当前会议，跳到下个会议  此时k没有使用 保持不变
            int ans = Math.max(helper(events, i, n, k - 1) + events[curr][2], helper(events, curr + 1, n, k));
            System.out.printf("%d\n", ans);
            return memo[curr][k] = ans;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[][] events = PrintUtils.processSymbol("[[1,2,4],[3,4,3],[2,3,1]]");
            int k = 2;
//            Assert.assertEquals(handler.maxValue(events, k), 7);
            events = PrintUtils.processSymbol("[[1,1,1],[2,2,2],[3,3,3],[4,4,4]]");
            k = 3;
            Assert.assertEquals(handler.maxValue(events, k), 9);

        }


        public int maxValue(int[][] events, int K) {
            int N = events.length;
            Arrays.sort(events, (o1, o2) -> o1[1] - o2[1]);
            // //`dp[i][j]`表示在前`i`个会议中，最多只能参加`j`个会议，所能获取到的最大价值
            int[][] dp = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                int[] curr = events[i - 1];
                int s = curr[0], e = curr[1], v = curr[2];
                int l = 1, r = i - 1;
                while (l < r) {
                    int m = l + (r - l + 1) / 2;
                    //dp的下标于events错开了一位，这里m-1
                    if (events[m - 1][1] < s) l = m;
                    else r = m - 1;
                }
//                System.out.printf("l:%d  r:%d\n", l, r);
                //最后需要拦一道，确保得到的 会议的结束时间是小于 当前会议的开始时间 ，拿到这个会议的下标 p
                int p = events[l - 1][1] < s ? r : 0;
//                System.out.println(p);
                //遍历前这 K次参加会议的机会，参加当前i 会议 与不参加当前i会议
                for (int j = 1; j <= K; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[p][j - 1] + v);
                }
            }
//            PrintUtils.printMatrix(dp);
            return dp[N][K];
        }

    }
}
