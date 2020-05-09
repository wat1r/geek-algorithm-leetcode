package com.frankcooper.bank;

import java.util.LinkedList;
import java.util.Queue;

public class _225 {


    class MyStack {
        private Queue<Integer> data;
        private Queue<Integer> help;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            data = new LinkedList<>();
            help = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            data.add(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            while (data.size() != 1) help.add(data.poll());
            int res = data.poll();
            swap();
            return res;
        }

        /**
         * Get the top element.
         */
        public int top() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            while (data.size() != 1) help.add(data.poll());
            int res = data.poll();
            help.add(res);
            swap();
            return res;
        }


        private void swap() {
            Queue tmp = help;
            help = data;
            data = tmp;
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return data.size() == 0;
        }
    }

}


