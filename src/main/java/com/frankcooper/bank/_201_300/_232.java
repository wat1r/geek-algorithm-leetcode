package com.frankcooper.bank._201_300;

import java.util.Stack;

public class _232 {

    public static void main(String[] args) {

    }


    static class _1st {
        class MyQueue {

            Stack<Integer> data;
            Stack<Integer> help;

            /**
             * Initialize your data structure here.
             */
            public MyQueue() {
                data = new Stack<>();
                help = new Stack<>();
            }

            /**
             * Push element x to the back of queue.
             */
            public void push(int x) {
                help.push(x);
            }

            /**
             * Removes the element from in front of queue and returns that element.
             */
            public int pop() {
                if (data.isEmpty() && help.isEmpty()) throw new RuntimeException("empty list");
                while (data.isEmpty()) {
                    while (!help.isEmpty()) data.push(help.pop());
                }
                return data.pop();
            }

            /**
             * Get the front element.
             */
            public int peek() {
                if (data.isEmpty() && help.isEmpty()) throw new RuntimeException("empty list");
                while (data.isEmpty()) {
                    while (!help.isEmpty()) data.push(help.pop());
                }
                return data.peek();
            }

            /**
             * Returns whether the queue is empty.
             */
            public boolean empty() {
                return data.isEmpty() && help.isEmpty();
            }
        }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
    }

    class MyQueue {
        Stack<Integer> data;
        Stack<Integer> help;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            data = new Stack<>();
            help = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            help.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (data.isEmpty() && help.isEmpty()) throw new RuntimeException("empty queue");
            if (data.isEmpty()) {
                while (!help.isEmpty()) data.push(help.pop());
            }
            return data.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (data.isEmpty() && help.isEmpty()) throw new RuntimeException("empty queue");
            if (data.isEmpty()) {
                while (!help.isEmpty()) data.push(help.pop());
            }
            return data.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return data.isEmpty() && help.isEmpty();
        }
    }

    static class _2nd {
        class MyQueue {


            Stack<Integer> data, help;

            /**
             * Initialize your data structure here.
             */
            public MyQueue() {
                data = new Stack<>();
                help = new Stack<>();
            }

            /**
             * Push element x to the back of queue.
             */
            public void push(int x) {
                help.push(x);
            }

            /**
             * Removes the element from in front of queue and returns that element.
             */
            public int pop() {
                int ans = peek();
                data.pop();
                return ans;
            }

            /**
             * Get the front element.
             */
            public int peek() {
                if (data.isEmpty()) {
                    while (!help.isEmpty()) data.push(help.pop());
                }
                return data.peek();
            }

            /**
             * Returns whether the queue is empty.
             */
            public boolean empty() {
                return data.isEmpty() && help.isEmpty();
            }
        }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
    }

    static class _3rd {
        class MyQueue {

            Stack<Integer> data = new Stack<>();
            Stack<Integer> help = new Stack<>();

            public MyQueue() {

            }

            public void push(int x) {
                help.push(x);
            }

            public int pop() {
                while (data.isEmpty()) {
                    while (!help.isEmpty()) data.push(help.pop());
                }
                return data.pop();
            }

            public int peek() {
                while (data.isEmpty()) {
                    while (!help.isEmpty()) data.push(help.pop());
                }
                return data.peek();
            }

            public boolean empty() {
                return data.isEmpty() && help.isEmpty();
            }
        }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
    }

}
