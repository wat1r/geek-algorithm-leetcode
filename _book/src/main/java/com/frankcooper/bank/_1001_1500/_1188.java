package com.frankcooper.bank._1001_1500;

import java.util.*;
import java.util.concurrent.Semaphore;

import org.junit.Assert;

public class _1188 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class BoundedBlockingQueue {

            private int capacity;
            private Semaphore producerSema;
            private Semaphore consumerSema;
            private LinkedList<Integer> list = new LinkedList<>();

            public BoundedBlockingQueue(int capacity) {
                this.capacity = capacity;
                consumerSema = new Semaphore(0);
                producerSema = new Semaphore(capacity);
            }

            //进队列
            public void enqueue(int element) throws InterruptedException {
                producerSema.acquire();
                list.add(element);
                consumerSema.release();
            }

            public int dequeue() throws InterruptedException {
                consumerSema.acquire();
                int res = list.removeFirst();
                producerSema.release();
                return res;
            }

            public int size() {
                return list.size();
            }

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class BoundedBlockingQueue {

            private int capacity;
            private LinkedList<Integer> list;
            private Object obj = new Object();


            public BoundedBlockingQueue(int capacity) {
                this.capacity = capacity;
                list = new LinkedList<>();
            }

            public void enqueue(int element) throws InterruptedException {
                synchronized (obj) {
                    while (size() >= capacity) {
                        obj.wait();
                    }
                    list.add(element);
                    obj.notifyAll();
                }

            }

            public int dequeue() throws InterruptedException {
                int res;
                synchronized (obj) {
                    while (size() <= 0) {
                        obj.wait();
                    }
                    res = list.removeFirst();
                    obj.notifyAll();
                }
                return res;
            }

            public int size() {
                return list.size();
            }

        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        class BoundedBlockingQueue {

            private int capacity;

            public BoundedBlockingQueue(int capacity) {
                this.capacity = capacity;
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

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
