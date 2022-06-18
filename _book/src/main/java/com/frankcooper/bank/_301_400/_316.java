package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

/**
 * @Date 2020/9/9
 * @Author Frank Cooper
 * @Description
 */
public class _316 {

    static class _1st {


        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "bcabc";
            s = "cbacdcbc";
            handler.removeDuplicateLetters(s);
        }


        /*
1.在考虑字符 s[i] 时，如果它已经存在于栈中，则不能加入字符 s[i]。为此，需要记录每个字符是否出现在栈中。
2.在弹出栈顶字符时，如果字符串在后面的位置上再也没有这一字符，则不能弹出栈顶字符。为此，需要记录每个字符的剩余数量，当这个值为 0 时，就不能弹出栈顶字符了。

         */
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

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String s = "bcabc";
            s = "cbacdcbc";
            handler.removeDuplicateLetters(s);
        }

        public String removeDuplicateLetters(String s) {
            int[] cnt = new int[26];//记录s中每个字母出现的次数
            for (char c : s.toCharArray()) cnt[c - 'a']++;
            Set<Character> set = new HashSet<>();//记录当前字符是否已经存在在栈内
            Deque<Character> stk = new ArrayDeque<>();//单调栈
            for (char c : s.toCharArray()) {
                if (!set.contains(c)) {//set没有c字母
                    //1.栈顶字符比当前字符字典序大
                    //2.栈顶字符在当前字符c后还出现过（栈顶的cnt计数大于1）
                    while (!stk.isEmpty() && stk.peek() > c && cnt[stk.peek() - 'a'] > 1) {
                        char t = stk.pop();
                        cnt[t - 'a']--;
                        set.remove(t);
                    }
                    stk.push(c);
                    set.add(c);
                } else {//set中已经有该字符，也就是在栈内已经出现了1次，后面的同时出现该字符时，可以抛弃
                    cnt[c - 'a']--;
                }
            }
            StringBuilder sb = new StringBuilder();
            while (!stk.isEmpty()) sb.append(stk.pop());
            return sb.reverse().toString();
        }


    }


}
