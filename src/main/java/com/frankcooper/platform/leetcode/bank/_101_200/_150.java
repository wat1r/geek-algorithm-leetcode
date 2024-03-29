package com.frankcooper.platform.leetcode.bank._101_200;

import java.util.*;

public class _150 {
    static class _1st {

        public int evalRPN(String[] tokens) {
            List<String> symbols = Arrays.asList("+", "-", "*", "/");
            Stack<Integer> stk = new Stack<>();
            for (String t : tokens) {
                if (symbols.contains(t)) {
                    int r = stk.pop(), l = stk.pop();
                    if (t.equals("+")) stk.push(l + r);
                    if (t.equals("-")) stk.push(l - r);
                    if (t.equals("*")) stk.push(l * r);
                    if (t.equals("/")) stk.push(l / r);
                } else {
                    stk.push(Integer.valueOf(t));
                }
            }
            return stk.pop();
        }
    }

    static class _2nd {


        public int evalRPN(String[] tokens) {
            List<String> symbols = Arrays.asList("+", "-", "*", "/");
            Stack<Integer> stk = new Stack<>();
            for (String t : tokens) {
                if (symbols.contains(t)) {
                    int post = stk.pop(), prev = stk.pop();
                    if (t.equals("+")) stk.push(prev + post);
                    else if (t.equals("-")) stk.push(prev - post);
                    else if (t.equals("*")) stk.push(prev * post);
                    else if (t.equals("/")) stk.push(prev / post);
                } else stk.push(Integer.valueOf(t));
            }
            return stk.peek();
        }
    }

    static class _3rd {
        public int evalRPN(String[] tokens) {
            List<String> symbols = Arrays.asList("+", "-", "*", "/");
            Deque<Integer> stk = new ArrayDeque<>();
            for (String t : tokens) {
                if (symbols.contains(t)) {
                    int post = stk.pop(), prev = stk.pop();
                    if (t.equals("+")) stk.push(prev + post);
                    else if (t.equals("-")) stk.push(prev - post);
                    else if (t.equals("*")) stk.push(prev * post);
                    else if (t.equals("/")) stk.push(prev / post);
                } else stk.push(Integer.valueOf(t));
            }
            return stk.peek();
        }
    }
}
