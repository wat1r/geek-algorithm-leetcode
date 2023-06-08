package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1642 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] heights = new int[]{4, 2, 7, 6, 9, 14, 12};
            int bricks = 5, ladders = 1;
            Assert.assertEquals(4, handler.furthestBuilding(heights, bricks, ladders));


        }
        //          //大根堆，从栈顶到栈底 依次从大到小
        //            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        //            //小根堆，从栈顶到栈底 依次从小到大
        //            PriorityQueue<Integer> minHeap = new PriorityQueue<>();


        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            int n = heights.length;
            int sumH = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 1; i < n; i++) {
                int deltaH = heights[i] - heights[i - 1];
                if (deltaH > 0) {
                    pq.offer(deltaH);
                    if (!pq.isEmpty() && pq.size() > ladders) {
                        sumH += pq.poll();//弹出的是栈顶的元素，使用砖块小的，使用砖块大的在栈底
                    }
                    if (sumH > bricks) {
                        return i - 1;
                    }
                }
            }
            return n - 1;
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
