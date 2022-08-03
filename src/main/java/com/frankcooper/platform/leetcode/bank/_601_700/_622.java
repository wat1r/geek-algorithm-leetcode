package com.frankcooper.platform.leetcode.bank._601_700;

import java.util.*;

import org.junit.Assert;

public class _622 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        class MyCircularQueue {

            private int front, rear;
            private int capacity;
            private int[] arr;

            public MyCircularQueue(int k) {
                capacity = k + 1;
                arr = new int[capacity];
                front = rear = 0;
            }

            public boolean enQueue(int value) {
                if (isFull()) return false;
                arr[rear] = value;
                rear = (rear + 1) % capacity;
                return true;
            }

            public boolean deQueue() {
                if (isEmpty()) return false;
                front = (front + 1) % capacity;
                return true;
            }

            public int Front() {
                if (isEmpty()) return -1;
                return arr[front];
            }

            public int Rear() {
                if (isEmpty()) return -1;
                return arr[(rear - 1 + capacity) % capacity];
            }

            public boolean isEmpty() {
                return rear == front;
            }

            public boolean isFull() {
                return ((rear + 1) % capacity) == front;
            }
        }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
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
