package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import org.junit.Assert;

public class _17_20 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            MedianFinder finder = new MedianFinder();


            finder.addNum(1);
            finder.addNum(2);
            finder.findMedian();
            finder.addNum(3);
            finder.findMedian();
        }


        static class MedianFinder {

            //小根堆
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            //大根堆 保持大根堆的元素数量=小根堆+1|0
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);


            public MedianFinder() {
            }

            public void addNum(int num) {
                minHeap.offer(num);
                if (maxHeap.size() < minHeap.size()) {
                    maxHeap.offer(minHeap.poll());
                } else if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(maxHeap.poll());
                }

            }

            public double findMedian() {
                if (maxHeap.isEmpty()) return 0.0;
                if (maxHeap.size() == 1 + minHeap.size()) return maxHeap.peek();
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
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
