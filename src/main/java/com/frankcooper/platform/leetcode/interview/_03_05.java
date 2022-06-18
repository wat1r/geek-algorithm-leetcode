package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _03_05 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class SortedStack {

            Stack<Integer> data;//降序
            Stack<Integer> help;//升序


            public SortedStack() {
                data = new Stack<>();
                help = new Stack<>();
            }

            public void push(int val) {
                //辅助栈的元素大于val，移入data栈
                while (!help.isEmpty() && help.peek() > val) {
                    data.push(help.pop());
                }
                //data栈的数据小于val，移入help
                while (!data.isEmpty() && data.peek() < val) {
                    help.push(data.pop());
                }
                data.push(val);
            }

            public void pop() {
                while (!help.isEmpty()) {
                    data.push(help.pop());
                }
                if (!data.isEmpty()) data.pop();

            }

            public int peek() {
                while (!help.isEmpty()) {
                    data.push(help.pop());
                }
                return data.isEmpty() ? -1 : data.peek();
            }

            public boolean isEmpty() {
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
