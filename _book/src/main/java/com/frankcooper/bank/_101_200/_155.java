package com.frankcooper.bank._101_200;

import java.util.Stack;

public class _155 {

    public static void main(String[] args) {

    }


    class MinStack {
        private Stack<Integer> data;
        private Stack<Integer> help;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            data = new Stack<>();
            help = new Stack<>();
        }

        public void push(int x) {
            data.push(x);
            if (help.isEmpty()) help.push(x);
            else if (help.peek() < x) help.push(help.peek());
            else help.push(x);
        }

        public void pop() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            data.pop();
            help.pop();
        }

        public int top() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            return data.peek();

        }

        public int getMin() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            return help.peek();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

}
