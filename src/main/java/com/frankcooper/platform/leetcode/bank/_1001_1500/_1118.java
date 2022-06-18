package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;
import java.util.concurrent.Semaphore;

public class _1118 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        class BoundedBlockingQueue {
            private Semaphore consumer;
            private Semaphore producer;
            private int capacity;
            private LinkedList<Integer> queue = new LinkedList<>();


            public BoundedBlockingQueue(int capacity) {
                this.capacity = capacity;
                consumer = new Semaphore(0);
                producer = new Semaphore(capacity);
            }

            public void enqueue(int element) throws InterruptedException {
                producer.acquire();
                queue.add(element);
                consumer.release();

            }

            public int dequeue() throws InterruptedException {
                consumer.acquire();
                int v = queue.removeFirst();
                producer.release();
                return v;
            }

            public int size() {
                return queue.size();
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class BoundedBlockingQueue {


            public BoundedBlockingQueue(int capacity) {
            }

            public void enqueue(int element) throws InterruptedException {
            }

            public int dequeue() throws InterruptedException {
                return 0;
            }

            public int size() {
                return 0;
            }
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
