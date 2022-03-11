package com.frankcooper.swordoffer;

import java.util.PriorityQueue;

public class Sword_41 {


    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        mf.findMedian();
        mf.addNum(3);
        mf.findMedian();
    }


    static class MedianFinder {

        PriorityQueue<Integer> maxHeap;//大根堆，保存较小的一半，始终多一个，如果有的多的话
        PriorityQueue<Integer> minHeap;//小根堆，保存较大的一半
        int count;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            count = 0;
            maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            count++;
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (count % 2 == 1) maxHeap.offer(minHeap.poll());
        }

        public double findMedian() {
            double ans;
            if (count % 2 == 1) ans = (double) maxHeap.peek();
            else ans = (double) (maxHeap.peek() + minHeap.peek()) / 2;
            return ans;
        }
    }

}
