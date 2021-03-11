package com.frankcooper.bank;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
}
