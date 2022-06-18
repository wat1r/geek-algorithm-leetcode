package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _03_01 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            TripleInOne one = new TripleInOne(1);
            one.push(0, 1);
            one.push(0, 2);
            one.pop(0);
            one.pop(0);
            one.pop(0);
            one.isEmpty(0);


/*            TripleInOne one = new TripleInOne(18);
            one.peek(1);
            one.pop(2);
            one.isEmpty(1);
            one.push(2, 40);
            one.pop(2);
            one.push(0, 44);
            one.push(1, 40);
            one.pop(0);
            one.push(1, 54);
            one.push(0, 42);
            one.isEmpty(0);
            one.pop(1);
            System.out.println(one.peek(1));*/

        }

        /**
         *
         */
        static class TripleInOne {
            int[][] arr;
            int[] p;
            int stackSize;

            public TripleInOne(int stackSize) {
                this.stackSize = stackSize;
                arr = new int[3][stackSize];
                p = new int[3];
                Arrays.fill(p, -1);
            }

            public void push(int stackNum, int value) {
                if (p[stackNum] >= stackSize - 1) return;
                arr[stackNum][++p[stackNum]] = value;

            }

            public int pop(int stackNum) {
                if (p[stackNum] == -1) return -1;
                int val = arr[stackNum][p[stackNum]];
                arr[stackNum][p[stackNum]] = 0;
                p[stackNum]--;
                return val;
            }

            public int peek(int stackNum) {
                if (p[stackNum] == -1) return -1;
                return arr[stackNum][p[stackNum]];
            }

            public boolean isEmpty(int stackNum) {
                return p[stackNum] == -1;
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
