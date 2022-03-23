package com.frankcooper.bank._1_100;

import java.util.*;

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

    static class _1st_1 {
        public static void main(String[] args) {

        }

        public boolean isValid(String s) {
            Map<Character, Character> dict = new HashMap<Character, Character>() {{
                put(']', '[');
                put(')', '(');
                put('}', '{');
            }};
            Deque<Character> deque = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                //左边括号纷纷入栈
                if (c == '[' || c == '(' || c == '{') deque.addFirst(c);
                    //如果找到了右边括号，且符合题意的右边括号，找到栈顶的字符看看是否不是成对的
                    //dict.containsKey(c) 去掉也可以跑过
                else if (!deque.isEmpty() && dict.containsKey(c) && dict.get(c) == deque.peekFirst()) {
                    deque.pollFirst();
                } else return false;
            }
            //注意 s="[" 这个case，需要最后返回
            return deque.isEmpty();
        }

    }

    static class _1st_2 {
        public boolean isValid(String s) {
            Map<Character, Character> dict = new HashMap<Character, Character>() {{
                put(']', '[');
                put(')', '(');
                put('}', '{');
            }};
            Deque<Character> deque = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                //左边括号纷纷入栈
                if (c == '[' || c == '(' || c == '{') deque.push(c);
                    //如果找到了右边括号，且符合题意的右边括号，找到栈顶的字符看看是否不是成对的
                    //dict.containsKey(c) 去掉也可以跑过
                else if (!deque.isEmpty() && dict.containsKey(c) && dict.get(c) == deque.peek()) {
                    deque.poll();
                } else return false;
            }
            //注意 s="[" 这个case，需要最后返回
            return deque.isEmpty();
        }
    }
}
