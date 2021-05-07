package com.frankcooper.bank._1501_2000;

import java.util.*;

public class _5535 {
    //括号的最大嵌套深度

    static _5535 handler = new _5535();

    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";
//        handler.maxDepth(s);
//        s = "(1)+((2))+(((3)))";
//        handler.maxDepth(s);
//        s = "1+(2*3)/(2-1)";
//        handler.maxDepth(s);
//        s = "1";
//        handler.maxDepth(s);
        s = "8*((1*(5+6))*(8/6))";
        handler.maxDepth(s);
    }


    public int maxDepth(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
                res = Math.max(res, stack.size());
            } else if (c == ')') {
                stack.pop();
            }
        }
        return res;
    }


    public int maxDepth1st(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        int depth = 0;
        Stack<Character> stack = new Stack<>();
        boolean f = false;
        for (char c : s.toCharArray()) {
            if (c == '(') {

                stack.push(c);
                if (f) {
                    if (stack.isEmpty()) {
                        depth = 0;
                    }
                    f = false;
                }
            }
            if (c == ')' && !stack.isEmpty()) {
                stack.pop();
                depth += 1;
                f = true;
                res = Math.max(res, depth);
            }
        }
        System.out.printf("%s--->%d\n", s, res);
        return res;
    }

}
