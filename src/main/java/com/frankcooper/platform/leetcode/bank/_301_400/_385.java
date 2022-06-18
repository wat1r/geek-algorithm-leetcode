package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

import com.frankcooper.struct.sec.NestedInteger;

public class _385 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "[123,[456,[789]]]";
            handler.deserialize(s);

        }

        public NestedInteger deserialize(String s) {
            if (!s.startsWith("[")) return new NestedInteger(Integer.parseInt(s));
            Deque<NestedInteger> stk = new ArrayDeque<>();
            NestedInteger cur = new NestedInteger();
            stk.push(cur);
            int l = 1;//数字的开头
            for (int r = 1; r < s.length(); r++) {
                char c = s.charAt(r);
                //遇到'['新开一个NestedInteger，并且将其加入到stk栈顶的元素,更新l的位置
                if (c == '[') {
                    NestedInteger ni = new NestedInteger();
                    //stk里会有多个引用传递的ni
                    stk.peek().add(ni);
                    stk.push(ni);
                    l = r + 1;
                } else if (c == ',' || c == ']') {
                    if (r > l) {
                        int val = Integer.parseInt(s.substring(l, r));
                        stk.peek().add(new NestedInteger(val));
                    }
                    l = r + 1;
                    //"[123,456,[788,799,833],[[]],10,[]]"
                    //遇到']'弹出ni
                    if (c == ']') stk.pop();
                }
            }
            return cur;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String s = "[123,[456,[789]]]";
            handler.deserialize(s);
            //"[-1]"
        }

        public NestedInteger deserialize(String s) {
            if (!s.startsWith("[")) return new NestedInteger(Integer.parseInt(s));
            Deque<NestedInteger> stk = new ArrayDeque<>();
            int num = 0;
            boolean negative = false;//负数
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '[') {
                    stk.push(new NestedInteger());
                } else if (Character.isDigit(c)) {
                    num = num * 10 + c - '0';
                } else if (c == '-') {
                    negative = true;
                } else if (c == ']' || c == ',') {
                    if (Character.isDigit(s.charAt(i - 1))) {
                        if (negative) num = -num;
                        stk.peek().add(new NestedInteger(num));
                    }
                    num = 0;
                    negative = false;
                    if (c == ']' && stk.size() > 1) {
                        NestedInteger ni = stk.pop();
                        stk.peek().add(ni);
                    }
                }
            }
            return stk.pop();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String s = "[123,[456,[789]]]";
            handler.deserialize(s);
        }

        int index = 0;

        public NestedInteger deserialize(String s) {
            if (s.charAt(index) == '[') {
                index++;
                NestedInteger ni = new NestedInteger();
                while (s.charAt(index) != ']') {
                    ni.add(deserialize(s));
                    if (s.charAt(index) == ',') {
                        index++;
                    }
                }
                index++;
                return ni;
            } else {
                boolean negative = false;
                if (s.charAt(index) == '-') {
                    negative = true;
                    index++;
                }
                int num = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    num = num * 10 + s.charAt(index) - '0';
                    index++;
                }
                if (negative) {
                    num *= -1;
                }
                return new NestedInteger(num);
            }
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
