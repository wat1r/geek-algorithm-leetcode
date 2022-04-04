package com.frankcooper.bank._1_100;

import java.util.*;

import org.junit.Assert;

public class _32 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int longestValidParentheses(String s) {
            int N = s.length();
            int[] f = new int[N];
            int res = 0;
            for (int i = 1; i < N; i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        f[i] = 2;
                        if (i - 2 >= 0) f[i] = f[i - 2] + 2;
                    } else if (f[i - 1] > 0) {
                        if ((i - f[i - 1] - 1) >= 0 && s.charAt(i - f[i - 1] - 1) == '(') {
                            f[i] = f[i - 1] + 2;
                            if ((i - f[i - 1] - 2) >= 0) {
                                f[i] = f[i] + f[i - f[i - 1] - 2];
                            }
                        }
                    }
                }
                res = Math.max(res, f[i]);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int longestValidParentheses(String s) {
            //stk存的是上一个不匹配的位置（下标）
            Deque<Integer> stk = new ArrayDeque<>();
            int res = 0;
            //[0...3]之间的长度是4，0也就是3-(-1)=4 -1为0位置往前推一个位置
            stk.push(-1);
            for (int i = 0; i < s.length(); i++) {
                // '('时，往栈里推下标
                if (s.charAt(i) == '(') stk.push(i);
                else {
                    //')' 把上一个下标索引弹出 如果栈空了，说明当前的这个下标是上一个不匹配的位置
                    int cur = stk.pop();
                    if (stk.isEmpty()) stk.push(i);
                        //栈不为空，计算当前i与上一个不匹配位置的距离
                    else res = Math.max(res, i - stk.peek());
                }
            }
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
