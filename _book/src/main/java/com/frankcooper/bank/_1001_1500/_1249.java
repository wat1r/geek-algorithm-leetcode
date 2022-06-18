package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1249 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public String minRemoveToMakeValid(String s) {
            Deque<Integer> stk = new ArrayDeque<>();
            int n = s.length();
            boolean[] mark = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '(') {
                    stk.push(i);
                    mark[i] = true;
                } else if (s.charAt(i) == ')') {
                    if (stk.isEmpty()) {
                        mark[i] = true;
                    } else {
                        mark[stk.pop()] = false;
                    }
                }
            }
            String res = "";
            for (int i = 0; i < n; i++) {
                if (!mark[i]) res += s.charAt(i);
            }
            return res;
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
