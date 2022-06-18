package com.frankcooper.platform.leetcode.bank._601_700;

import java.util.*;

public class _682 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int calPoints(String[] ops) {
            if (ops == null || ops.length == 0) return -1;
            Deque<Integer> stk = new ArrayDeque<>();
            for (String op : ops) {
                if (op.equals("D")) stk.push(stk.peek() * 2);
                else if (op.equals("C")) stk.poll();
                    //遇到+号 弹出前两次
                else if (op.equals("+")) {
                    int cur = stk.poll(), prev = stk.poll();
                    stk.push(prev);
                    stk.push(cur);
                    stk.push(prev + cur);
                } else {
                    stk.push(Integer.parseInt(op));
                }
            }
            int res = 0;
            while (!stk.isEmpty()) res += stk.poll();
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String[] ops = new String[]{"36", "28", "70", "65", "C", "+", "33", "-46", "84", "C"};
            handler.calPoints(ops);
        }

        public int calPoints(String[] ops) {
            if (ops == null || ops.length == 0) return -1;
            int[] arr = new int[1005];
            int idx = 0;
            for (int i = 0; i < ops.length; i++, idx++) {
                String op = ops[i];
                if (op.equals("D")) arr[idx] = arr[idx - 1] * 2;
                    //往前跳2次，for循环+1 达到向前移动一次的目的
                else if (op.equals("C")) idx -= 2;
                else if (op.equals("+")) arr[idx] = arr[idx - 1] + arr[idx - 2];
                else arr[idx] = Integer.parseInt(op);
            }
            int res = 0;
            //最后如果读到"C"的时候，idx会回退索引，但arr的元素并没有更新
            //参考case:[36", "28", "70", "65", "C", "+", "33", "-46", "84", "C"]
            for (int i = 0; i < idx; i++) res += arr[i];
            return res;
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
