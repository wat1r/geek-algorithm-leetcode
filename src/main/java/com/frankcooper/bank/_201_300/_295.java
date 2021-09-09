package com.frankcooper.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _295 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.test();

            MedianFinder mf = new MedianFinder();
            mf.addNum(1);
            mf.addNum(2);
            mf.findMedian();
            mf.addNum(3);
            mf.findMedian();
        }


        private void test() {
            Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
            queue.offer(12);
            queue.offer(15);
            queue.offer(10);
            while (!queue.isEmpty()) {
                int t = queue.poll();
                System.out.println(t);
            }
        }


        static class MedianFinder {

            //大根堆，从栈顶到栈底 依次从大到小
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> o2 - o1));
            //小根堆，从栈顶到栈底 依次从小到大
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            int cnt = 0;


            /**
             * initialize your data structure here.
             */
            public MedianFinder() {

            }

            public void addNum(int num) {
                cnt++;
                maxHeap.offer(num);
                minHeap.offer(maxHeap.poll());
                if (cnt % 2 == 1) maxHeap.offer(minHeap.poll());
            }

            public double findMedian() {
                if (cnt % 2 == 1) {
                    return maxHeap.peek();
                } else {
                    return (maxHeap.peek() + minHeap.peek()) / 2.0;
                }
            }
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
