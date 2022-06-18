package com.frankcooper.platform.leetcode.bank._1001_1500;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * import java.util.*;
 * import org.junit.Assert;
 */

public class _1353 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] events = PrintUtils.processSymbol("[[1,2],[2,3],[3,4],[1,2]]");
            Assert.assertEquals(handler.maxEvents(events), 4);
            events = PrintUtils.processSymbol("[[1,2],[2,3],[3,4]]");
            Assert.assertEquals(handler.maxEvents(events), 3);
            events = PrintUtils.processSymbol("[[1,4],[4,4],[2,2],[3,4],[1,1]]");
            Assert.assertEquals(handler.maxEvents(events), 4);
            events = PrintUtils.processSymbol("[[1,100000]]");
            Assert.assertEquals(handler.maxEvents(events), 1);
            events = PrintUtils.processSymbol("[[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]");
            Assert.assertEquals(handler.maxEvents(events), 7);
        }


        /**
         * 1.对会议按开始时间从小到大排序
         * 2.准备一个优先队列，存储每次进来的会议的结束时间，排序按结束时间越早越靠前
         * 3.准备一个变量day 表示从第1天开始累加，队列里有会议就参加，ans++ 队列为空，continue掉
         * 4.因为每天只能参加一个会议，需要清掉那些过期会议（这些会议是没有来得及参加的）
         *
         * @param events
         * @return
         */
        public int maxEvents(int[][] events) {
            Arrays.sort(events, ((o1, o2) -> o1[0] - o2[0]));
            PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o1 - o2));
            int day = 0, i = 0, n = events.length, ans = 0;
            while (i < n || !pq.isEmpty()) {
                day++;
                while (!pq.isEmpty() && pq.peek() < day) pq.poll();
                while (i < n && events[i][0] == day) {
                    pq.offer(events[i][1]);
                    i++;
                }
                if (pq.isEmpty()) continue;//处理上面的case3 i>n 的时候，退出
                ans++;
                pq.poll();
            }
            return ans;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class Solution {
            public int maxEvents(int[][] events) {

                Arrays.sort(events, Comparator.comparingInt(o -> o[0]));
                PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
                int i = 0;
                int count = 0;
                int n = events.length;
                int curD = 1;
                while (i < n || !queue.isEmpty()) {
                    while (i < n && events[i][0] <= curD) {
                        queue.offer(events[i]);
                        i++;
                    }

                    while (!queue.isEmpty()) {
                        int[] curEvent = queue.poll();
                        if (curEvent[1] >= curD) {
                            count++;
                            break;
                        }
                    }
                    curD++;
                }
                return count;
            }
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[][] events = PrintUtils.processSymbol("[[1,2],[2,3],[3,4],[1,2]]");
            Assert.assertEquals(handler.maxEvents(events), 4);
            events = PrintUtils.processSymbol("[[1,2],[2,3],[3,4]]");
            Assert.assertEquals(handler.maxEvents(events), 3);
            events = PrintUtils.processSymbol("[[1,4],[4,4],[2,2],[3,4],[1,1]]");
            Assert.assertEquals(handler.maxEvents(events), 4);
            events = PrintUtils.processSymbol("[[1,100000]]");
            Assert.assertEquals(handler.maxEvents(events), 1);
            events = PrintUtils.processSymbol("[[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]");
            Assert.assertEquals(handler.maxEvents(events), 7);
        }


        /**
         * 借助题中的数据范围上限
         *
         * @param events
         * @return
         */
        public int maxEvents(int[][] events) {
            Arrays.sort(events, ((o1, o2) -> o1[0] - o2[0]));
            PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o1 - o2));
            int i = 0, n = events.length, ans = 0;
            for (int d = 1; d < 100_005; d++) {
                while (!pq.isEmpty() && pq.peek() < d) pq.poll();
                while (i < n && events[i][0] == d) {
                    pq.offer(events[i][1]);
                    i++;
                }
                if (pq.isEmpty()) continue;//处理上面的case3 i>n 的时候，退出
                ans++;
                pq.poll();
            }
            return ans;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
