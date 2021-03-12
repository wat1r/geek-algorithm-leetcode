package com.frankcooper.bank;


import java.util.*;

public class _227 {

    static class _1st {
        public int calculate(String s) {
            Stack<Integer> stk = new Stack<>();
            List<Character> symbols = Arrays.asList('+', '-', '*', '/');
            char[] chas = s.toCharArray();
            int sign = 1, curr = 0;
            char symbol = '+';
            for (int i = 0; i < chas.length; i++) {
                if (Character.isDigit(chas[i])) {
                    curr = curr * 10 + chas[i] - '0';
                }
                // System.out.printf("%s\n",chas[i]);
                if (symbols.contains(chas[i]) && chas[i] != ' ' || i == chas.length - 1) {
                    switch (symbol) {
                        case '+':
                            stk.push(curr);
                            break;
                        case '-':
                            stk.push(-curr);
                            break;
                        case '*':
                            stk.push(stk.pop() * curr);
                            break;
                        case '/':
                            stk.push(stk.pop() / curr);
                            break;
                    }
                    symbol = chas[i];
                    curr = 0;
                }
            }
            int ans = 0;
            while (!stk.isEmpty()) ans += stk.pop();
            return ans;
        }
    }
}
