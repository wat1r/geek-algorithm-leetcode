package com.frankcooper.bank._301_400;

import java.util.*;

/**
 * @Date 2020/9/9
 * @Author Frank Cooper
 * @Description
 */
public class _316 {

    static _316 handler = new _316();


    public static void main(String[] args) {
        String s = "bcabc";
        s = "cbacdcbc";
        handler.removeDuplicateLetters(s);
    }


    public String removeDuplicateLetters(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) counter[c - 'a']++;
        Set<Character> set = new HashSet<>();
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!set.contains(c)) {
                while (!stack.isEmpty() && stack.peek() > c && counter[stack.peek() - 'a'] > 1) {
                    char pop = stack.pop();
                    counter[pop - 'a']--;
                    set.remove(pop);
                }
                stack.push(c);
                set.add(c);
            } else {
                counter[c - 'a']--;
            }
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) sb.append(stack.pop());
        return sb.reverse().toString();
    }

}
