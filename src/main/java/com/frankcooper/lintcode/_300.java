package com.frankcooper.lintcode;


import com.frankcooper.swordoffer.utils.PrintUtils;
import org.junit.Assert;

import java.util.*;

public class _300 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] meeting = PrintUtils.processSymbol("[[10,40],[20,50],[30,45],[40,60]]");
            int[] value = {3, 6, 2, 4};
            Assert.assertEquals(handler.maxValue(meeting, value), 7);
            meeting = PrintUtils.processSymbol("[[10,20],[20,30]]");
            value = new int[]{2,4};
            Assert.assertEquals(handler.maxValue(meeting, value), 6);

        }


        class Pair {
            int start, end, value;

            public Pair(int start, int end, int value) {
                this.start = start;
                this.end = end;
                this.value = value;
            }
        }

        public int maxValue(int[][] meeting, int[] value) {
            List<Pair> list = new ArrayList<>();
            int n = meeting.length;
            for (int i = 0; i < n; i++) list.add(new Pair(meeting[i][0], meeting[i][1], value[i]));
            list.sort(((o1, o2) -> o1.end - o2.end));
            //dp[i]表示会议[0...i]之间 ，及之前的方案所能获取到的价值的最大值
            int[] dp = new int[n];
            for (int i = 0; i < n; i++) {
                Pair curr = list.get(i);
                if (i == 0) {
                    dp[i] = curr.value;
                } else {
                    int idx = binarySearch(list, i - 1, curr.start);
                    if (idx == -1) dp[i] = Math.max(dp[i - 1], curr.value);
                    else dp[i] = Math.max(curr.value + dp[idx], dp[i - 1]);
                }
            }
            return dp[n - 1];
        }


        /*
            找到list中结束时间比target(当前的会议的开始时间) 小的会议的编号，没有返回-1
         */
        private int binarySearch(List<Pair> list, int r, int target) {
            int l = 0;
            while (l < r) {
                int m = l + (r - l + 1) / 2;
                if (list.get(m).end > target) r = m - 1;
                else if (list.get(m).end <= target) l = m;
            }
            System.out.printf("%d\n",l);
            return list.get(l).end <= target ? l : -1;

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
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
