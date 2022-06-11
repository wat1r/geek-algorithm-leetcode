package com.frankcooper.bank._401_500;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.*;

/**
 * @Date 2020/9/9
 * @Author Frank Cooper
 * @Description
 */
public class _402 {

    static _402 handler = new _402();

    public static void main(String[] args) {
        String num = "100204";
        int k = 1;
        num = "1234567890";
        k = 9;
        handler.removeKdigits(num, k);

    }


    public String removeKdigits(String num, int k) {
        String res = "";
        for (char c : num.toCharArray()) {
            while (!res.equals("") && res.charAt(res.length() - 1) > c && k > 0) {
                res = res.substring(0, res.length() - 1);
                k--;
            }
            res += c;
        }
        while (k > 0) {
            res = res.substring(0, res.length() - 1);
            k--;
        }
        int i = 0;
        while (i < res.length() && res.charAt(i) == '0') i++;
        if (i == res.length()) return "0";
        return res.substring(i);
    }


    public String removeKdigits2nd(String num, int k) {
        LinkedList<Character> list = new LinkedList<>();
        for (char c : num.toCharArray()) {
            while (!list.isEmpty() && list.peekLast() > c && k > 0) {
                list.removeLast();
                k--;
            }
            list.addLast(c);
        }
        while (k > 0) {
            list.removeLast();
            k--;
        }
        StringBuilder res = new StringBuilder();
        boolean headZero = true;
        for (char c : list) {
            if (c == '0' && headZero) continue;
            res.append(c);
            headZero = false;
        }
        return res.toString().equals("") ? "0" : res.toString();
    }


    public String removeKdigits1st(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        while (k > 0) {
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        StringBuilder res = new StringBuilder();
        boolean headZero = true;
        for (char c : sb.reverse().toString().toCharArray()) {
            if (c == '0' && headZero) {
                continue;
            }
            res.append(c);
            headZero = false;
        }
        return res.toString().equals("") ? "0" : res.toString();
    }


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String num = "1432219";
            int k = 3;
//            handler.removeKdigits(num, k);
            num = "9";
            k = 1;
            Assert.assertEquals("0", handler.removeKdigits(num, k));
        }

        public String removeKdigits(String num, int k) {
            //维护一个单调递增的单调栈，该栈的大小是len(num)-k的长度
            Deque<Character> stk = new ArrayDeque<>();
            for (int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);
                while (!stk.isEmpty() && stk.peek() > c && k > 0) {
                    stk.pop();
                    k--;
                }
                stk.push(c);
            }
            //防止k没有完全消耗完
            /*num="9"
              k=1
              exprected:"0"
             */
            while (k-- > 0) stk.pop();
            StringBuilder sb = new StringBuilder();
            while (!stk.isEmpty()) sb.append(stk.pop());
            boolean headZero = true;//前导零
            StringBuilder res = new StringBuilder();
            for (char c : sb.reverse().toString().toCharArray()) {
                if (c == '0' && headZero) continue;
                res.append(c);
                headZero = false;
            }
            return res.toString().equals("") ? "0" : res.toString();
        }
    }


    static class _2nd_1 {
        public String removeKdigits(String num, int k) {
            Deque<Character> stk = new ArrayDeque<>();
            char[] ch = num.toCharArray();
            for (int i = 0; i < ch.length; i++) {
                while (!stk.isEmpty() && stk.peek() > ch[i] && k > 0) {
                    stk.pop();
                    k--;
                }
                stk.push(ch[i]);
            }
            while (k-- > 0) stk.pop();
            StringBuilder sb = new StringBuilder();
            while (!stk.isEmpty()) sb.append(stk.pop());
            StringBuilder res = new StringBuilder();
            boolean headZero = true;
            for (char c : sb.reverse().toString().toCharArray()) {
                if (c == '0' && headZero) {
                    continue;
                }
                res.append(c);
                headZero = false;
            }
            return res.toString().equals("") ? "0" : res.toString();
        }
    }


}
