package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1190 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "(u(love)i)";
//            handler.reverseParentheses(s);
            s = "(ed(et(oc))el)";
            //etco
            //octeel
            //leetco
            Assert.assertEquals("leetcode", handler.reverseParentheses(s));
        }


        public String reverseParentheses(String s) {
            String cur = "";
            Stack<String> stk = new Stack<>();
            char[] chas = s.toCharArray();
            for (int i = 0; i < chas.length; i++) {
                if (chas[i] == '(') {
                    stk.push(cur);
                    cur = "";
                } else if (chas[i] == ')') {
                    cur = new StringBuilder(cur).reverse().toString();
                    cur = stk.pop() + cur;
                } else {
                    cur = cur + chas[i];
                }
            }
            return cur;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
