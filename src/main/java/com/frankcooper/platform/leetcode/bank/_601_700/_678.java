package com.frankcooper.platform.leetcode.bank._601_700;

import java.util.*;

import org.junit.Assert;

public class _678 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertFalse(handler.checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"));

        }

        public boolean checkValidString(String s) {
            int n = s.length();
            char[] ch = s.toCharArray();
            int l = 0;//从左到右遍历 遇到( +1 遇到）-1 遇到* +1 要求l>=0 保证(足够
            for (int i = 0; i < n; i++) {
                if (ch[i] == '(' || ch[i] == '*') l++;
                else if (ch[i] == ')') l--;
                if (l < 0) return false;
            }
            int r = 0;//从右到左遍历 遇到( -1 遇到）+1 遇到* +1 要求r>=0 保证)足够
            for (int i = n - 1; i >= 0; i--) {
                if (ch[i] == ')' || ch[i] == '*') r++;
                else if (ch[i] == '(') r--;
                if (r < 0) return false;
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        //遇到左括号，直接进栈，记录括号的位置。
        //遇到星号，直接进栈，记录星号的位置。
        //遇到右括号：
        //a: 左括号栈里有元素，直接出栈。
        //b: 左括号栈里无元素，*栈里有元素，直接出栈。无元素的话就已经匹配失败了。
        public boolean checkValidString(String s) {
            Deque<Integer> left = new ArrayDeque<>();
            Deque<Integer> star = new ArrayDeque<>();
            int n = s.length();
            char[] ch = s.toCharArray();
            for (int i = 0; i < n; i++) {
                if (ch[i] == '(') left.push(i);
                else if (ch[i] == '*') star.push(i);
                else if (ch[i] == ')') {
                    if (!left.isEmpty()) left.pop();
                    else if (!star.isEmpty()) star.pop();
                    else return false;
                }
            }
            while (!left.isEmpty()) {
                int leftPos = left.pop();//左括号的位置
                if (star.isEmpty()) return false;//没有*号，说明不够了
                int starPos = star.pop();//* 的位置
                if (leftPos > starPos) {// * 的位置不应该出现在当前的(的位置的前面
                    return false;
                }
            }
            return true;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public boolean checkValidString(String s) {
            int n = s.length();
            // f[i][j] 表示 考虑前 i个字符（字符下标从 1 开始），能否与 j 个右括号形成合法括号序列。
            boolean[][] f = new boolean[n + 1][n + 1];
            f[0][0] = true;
            for (int i = 1; i <= n; i++) {
                char c = s.charAt(i - 1);
                for (int j = 0; j <= i; j++) {
                    if (c == '(') {
                        if (j - 1 >= 0) f[i][j] = f[i - 1][j - 1];
                    } else if (c == ')') {
                        if (j + 1 <= i) f[i][j] = f[i - 1][j + 1];
                    } else if (c == '*') {
                        f[i][j] = f[i - 1][j];
                        if (j - 1 >= 0) f[i][j] |= f[i - 1][j - 1];
                        if (j + 1 <= i) f[i][j] |= f[i - 1][j + 1];
                    }
                }
            }
            return f[n][0];
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
