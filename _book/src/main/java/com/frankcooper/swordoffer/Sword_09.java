package com.frankcooper.platform.leetcode.swordoffer;

import java.util.*;

import org.junit.Assert;

public class Sword_09 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        class CQueue {

            Stack<Integer> data;
            Stack<Integer> help;

            public CQueue() {
                data = new Stack<>();
                help = new Stack<>();
            }

            public void appendTail(int value) {
                help.push(value);
            }

            public int deleteHead() {
                if (data.isEmpty() && help.isEmpty()) return -1;
                if (data.isEmpty()) {
                    while (!help.isEmpty()) data.push(help.pop());
                }
                return data.pop();
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class CQueue {

            Deque<Integer> data;
            Deque<Integer> help;

            public CQueue() {
                data = new ArrayDeque<>();
                help = new ArrayDeque<>();
            }

            public void appendTail(int value) {
                help.addFirst(value);
//                help.push(value);
            }

            public int deleteHead() {
                if (data.isEmpty() && help.isEmpty()) return -1;
                if (data.isEmpty()) {
                    while (!help.isEmpty()) {
//                        data.addFirst(help.pollFirst());
                        data.push(help.pop());
                    }
                }
//                return data.pollFirst();
                return data.pop();
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
