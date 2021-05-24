package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _03_02 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class MinStack {

            Stack<Integer> data;
            Stack<Integer> help;

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
                else if (help.peek() >= x) help.push(x);
                else if (help.peek() < x) help.push(help.peek());
            }

            public void pop() {
                help.pop();
                data.pop();
            }

            public int top() {
                return data.peek();
            }

            public int getMin() {
                return help.peek();
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
