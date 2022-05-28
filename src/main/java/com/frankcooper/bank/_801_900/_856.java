package com.frankcooper.bank._801_900;

import java.util.*;

public class _856 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String s = "(()(()))";
            handler.scoreOfParentheses(s);

        }

        public int scoreOfParentheses(String s) {
            Deque<Character> stk = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                //遇到 ( 时向 入栈
                if (c == '(') stk.push(c);
                    //雨大 ) 时 判断栈顶是不是 （
                else if (c == ')') {
                    //栈顶是 （  合并为1
                    if (stk.peek() == '(') {
                        stk.pop();
                        stk.push('1');
                    } else {//栈顶不是 （ 而是数字 累加
                        char b = stk.pop();
                        int t = 0;
                        while (b != '(') {
                            t += b - '0';
                            b = stk.pop();
                        }
                        //注意char 和 int类型的转换
                        stk.push((char) ('0' + 2 * t));
                    }
                }
            }
            int res = 0;
            while (!stk.isEmpty()) res += stk.pop() - '0';
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String s = "()";
            handler.scoreOfParentheses(s);
        }

        public int scoreOfParentheses(String s) {
            Deque<Integer> stk = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                //stk中存储Integer 使用-1来标记
                if (c == '(') stk.push(-1);
                else if (c == ')') {
                    if (stk.peek() == -1) {
                        stk.pop();
                        stk.push(1);
                    } else {
                        int t = 0;
                        while (stk.peek() != -1) {
                            t += stk.pop();
                        }
                        stk.push(2 * t);
                    }
                }
            }
            int res = 0;
            while (!stk.isEmpty()) res += stk.pop();
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String s = "(()(()))";
            handler.scoreOfParentheses(s);
        }

        public int scoreOfParentheses(String S) {
            Stack<Integer> stack = new Stack();
            stack.push(0); // The score of the current frame
            for (char c : S.toCharArray()) {
                if (c == '(')
                    stack.push(0);
                else {
                    int v = stack.pop();
                    int w = stack.pop();
                    stack.push(w + Math.max(2 * v, 1));
                }
            }
            return stack.pop();
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            String s = "(()(()))";
            handler.scoreOfParentheses(s);
        }

        public int scoreOfParentheses(String S) {
            int res = 0, balance = 0;
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) == '(') {
                    balance++;
                } else {
                    balance--;
                    if (S.charAt(i - 1) == '(') {
                        res += 1 << balance;
                    }
                }
            }
            return res;
        }
    }
}
