package com.frankcooper.bank.bi_weekly;

import com.frankcooper.lintcode._300;
import com.frankcooper.swordoffer.utils.PrintUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class BiWeek45 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {1, -3, 2, 3, -4};
            Assert.assertEquals(handler.maxAbsoluteSum(nums), 5);
            nums = new int[]{2, -5, 1, -4, 3, -2};
            Assert.assertEquals(handler.maxAbsoluteSum(nums), 8);
            nums = new int[]{-7, -1, 0, -2, 1, 3, 8, -2, -6, -1, -10, -6, -6, 8, -4, -9, -4, 1, 4, -9};
            Assert.assertEquals(handler.maxAbsoluteSum(nums), 44);

        }

        public int maxAbsoluteSum(int[] nums) {
            int[] preSum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) preSum[i + 1] = preSum[i] + nums[i];
//            int ans = 0;
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = 0; i <= nums.length; i++) {
                min = Math.min(min, preSum[i]);
                max = Math.max(max, preSum[i]);
            }
            return Math.abs(max - min);
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();

            Assert.assertEquals(handler.minimumLength("bbbbbbbbbbbbbbbbbbb"), 0);
            Assert.assertEquals(handler.minimumLength("ca"), 2);
            Assert.assertEquals(handler.minimumLength("cabaabac"), 0);
            Assert.assertEquals(handler.minimumLength("aabccabba"), 3);
            Assert.assertEquals(handler.minimumLength("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccbbbbbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbcccccccbcaabccbacaabcbaccaacccaabbcaabbbbcaccbbcbbbbbabcacbccbaaaccaaaabcacbccbbcaabccacbccbbcaaaccaaccbbcaabaabbcaccaabbcbaacbaccbaaabccbaabcacbabcabbccbacaabbcaacaaacaaacbabbcabccbbacaabacabcacacbcacaabbabcbaaaccccacbbabcbccbaaccbbbabbbbaabcccaabaacccccccbbabcbcbcbcbcbbbbccbbaaccaaaaccacabbaccbbabccaacbcbccaabcacacacaaabbbaaccccaccaabcabbabacacbbaacbcabbbcaccccacccbcaccccccccbbcccbbaabbcbcaabcccbabcbcbccacaccbcaacbaaaaaababbaaccbcccaccccababcccacbccbbacabcbbbccbcababbaaaacaabccbaaacbacbcaababbcbacccacaccbabcabbccaccacbccaaccabbabcbbccaabccaacabbaabccacbabcaababccbcaacababbabcacccaaabcaabcbbbbabcbccbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcccccccccc"), 594);
        }


        public int minimumLength(String s) {
            int n = s.length(), l = 0, r = n - 1;
            char[] chas = s.toCharArray();
            int ans = n;
            while (l < r) {
//                System.out.printf("l:%d,r:%d\n", l, r);
                char prelc = chas[l], lc = chas[l], prerc = chas[r], rc = chas[r];
//                System.out.printf("prerc:%s,lc:%s,lr:%s,prerc:%s\n", prelc, chas[l], chas[r], prerc);
                if (lc != rc) break;
                while (lc == prelc && l < r) {
                    lc = chas[++l];
                }
                while (rc == prerc && l <= r) {
                    rc = chas[--r];
                }
                ans = r - l + 1;
            }
            return ans;
        }
    }


    static class _4th {

        public static void main(String[] args) {
            _4th handler = new _4th();

            int[][] events = PrintUtils.processSymbol("[[1,2,4],[3,4,3],[2,3,1]]");
            int k = 2;
            Assert.assertEquals(handler.maxValue(events, k), 7);
            events = PrintUtils.processSymbol("[[1,2,4],[3,4,3],[2,3,10]]");
            k = 2;
//            Assert.assertEquals(handler.maxValue(events, k), 10);
            events = PrintUtils.processSymbol("[[1,1,1],[2,2,2],[3,3,3],[4,4,4]]");
            k = 3;
//            Assert.assertEquals(handler.maxValue(events, k), 9);
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


        /*
         找到list中结束时间比target(当前的会议的开始时间) 小的会议的编号，没有返回-1
      */
        private int binarySearch(int[][] events, int r, int target) {
            int l = 0;
            while (l < r) {
                int m = l + (r - l + 1) / 2;//取到右中位数 「左动取左，右动取右」
                if (events[m][1] > target) r = m - 1;
                else if (events[m][1] <= target) l = m;
            }
            System.out.printf("%d\n", l);
            //在最后需要拦一道，如[[10,40],[20,50],[30,45],[40,60]] 这个case，
            // l的默认值是0 但是如果大于target的时候，不符合，返回-1
            return events[l][1] <= target ? l : -1;
        }

    }
}
