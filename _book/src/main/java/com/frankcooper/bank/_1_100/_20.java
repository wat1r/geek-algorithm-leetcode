package com.frankcooper.bank._1_100;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * @Date 2020/8/14
 * @Author Frank Cooper
 * @Description
 */
public class _20 {

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(']', '[');
        map.put('}', '{');
        map.put(')', '(');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '[' || c == '{' || c == '(') stack.push(c);
            else if (!stack.isEmpty() && map.get(c) == stack.peek()) stack.pop();
            else return false;
        }
        return stack.isEmpty();
    }

    static class _1st {
        public boolean isValid(String s) {
            Map<Character, Character> dict = new HashMap<>();
            dict.put(']', '[');
            dict.put(')', '(');
            dict.put('}', '{');
            Stack<Character> stk = new Stack<>();
            for (char c : s.toCharArray()) {
                if (c == '[' || c == '(' || c == '{') stk.push(c);
                else if (!stk.isEmpty() && dict.containsKey(c) && dict.get(c) == stk.peek()) stk.pop();
                else return false;
            }
            return stk.isEmpty();
        }
    }
}
