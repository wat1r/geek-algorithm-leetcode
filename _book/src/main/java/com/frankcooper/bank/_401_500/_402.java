package com.frankcooper.bank._401_500;

import java.util.LinkedList;
import java.util.Stack;

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


}
