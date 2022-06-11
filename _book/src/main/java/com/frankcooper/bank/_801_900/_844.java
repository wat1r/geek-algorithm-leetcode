package com.frankcooper.bank._801_900;

import java.util.Stack;

public class _844 {


    static _844 handler = new _844();


    public static void main(String[] args) {
        String S = "y#fo##f", T = "y#f#o##f";
        handler.backspaceCompare(S, T);
    }


    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();
        char[] chas1 = S.toCharArray();
        char[] chas2 = T.toCharArray();
        helper(s1, chas1);
        helper(s2, chas2);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (s1.isEmpty() || s2.isEmpty()) return false;
            if (s1.pop() != s2.pop()) return false;
        }
        return true;
    }

    private void helper(Stack<Character> s, char[] chas) {
        for (char c : chas) {
            if (c == '#' && !s.isEmpty()) {
                s.pop();
            } else if (c != '#') {
                s.push(c);
            }
        }
    }


}
