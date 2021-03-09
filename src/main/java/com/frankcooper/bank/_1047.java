package com.frankcooper.bank;

import java.util.Stack;

public class _1047 {

    static class _1st{
        public String removeDuplicates(String S) {
            Stack<Character> stk = new Stack<>();
            StringBuilder sb = new StringBuilder();
            for(char c: S.toCharArray()){
                if(!stk.isEmpty() && stk.peek() == c){
                    stk.pop();
                }else{
                    stk.push(c);
                }
            }
            while(!stk.isEmpty()){
                sb.append(stk.pop());
            }
            return sb.reverse().toString();
        }
    }
}
