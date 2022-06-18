package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _03_04 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

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
//                data.push(x);
                help.push(x);
            }

            /**
             * Removes the element from in front of queue and returns that element.
             */
            public int pop() {
                if (data.isEmpty()) {
                    while (!help.isEmpty()) {
                        data.push(help.pop());
                    }

                }
                return data.pop();
            }

            /**
             * Get the front element.
             */
            public int peek() {
                if (data.isEmpty()) {
                    while (!help.isEmpty()) {
                        data.push(help.pop());
                    }

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
