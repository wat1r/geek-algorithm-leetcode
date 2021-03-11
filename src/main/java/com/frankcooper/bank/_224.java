package com.frankcooper.bank;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class _224 {

    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.calculate("(1+(4+5+2)-3)+(6+8)");
        }


        public int calculate(String s) {
            Deque<Integer> ops = new LinkedList<Integer>();
            ops.push(1);
            int sign = 1;

            int ret = 0;
            int n = s.length();
            int i = 0;
            while (i < n) {
                if (s.charAt(i) == ' ') {
                    i++;
                } else if (s.charAt(i) == '+') {
                    sign = ops.peek();
                    i++;
                } else if (s.charAt(i) == '-') {
                    sign = -ops.peek();
                    i++;
                } else if (s.charAt(i) == '(') {
                    ops.push(sign);
                    i++;
                } else if (s.charAt(i) == ')') {
                    ops.pop();
                    i++;
                } else {
                    long num = 0;
                    while (i < n && Character.isDigit(s.charAt(i))) {
                        num = num * 10 + s.charAt(i) - '0';
                        i++;
                    }
                    ret += sign * num;
                }
            }
            return ret;
        }

    }


    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            handler.calculate("1 + 1");
            handler.calculate("2147483647");
        }

        public int calculate(String s) {
            Deque<Integer> stk = new LinkedList<>();
            stk.push(1);
            int sign = 1, i = 0, n = s.length();
            char[] chas = s.toCharArray();
            int res = 0;
            while (i < n) {
                char c = chas[i];
                if (c == ' ') i++;
                else if (c == '+') {
                    sign = stk.peek();
                    i++;
                } else if (c == '-') {
                    sign = -stk.peek();
                    i++;
                } else if (c == '(') {
                    stk.push(sign);
                    i++;
                } else if (c == ')') {
                    stk.pop();
                    i++;
                } else {
                    long sum = 0;
                    while (i < n && Character.isDigit(chas[i])) {
                        sum = sum * 10 + chas[i] - '0';
                        i++;
                    }
                    res += sign * sum;
                }
            }
            return res;
        }
    }
}
