package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import org.junit.Assert;

public class _08_09 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.generateParenthesis(3);
        }


        List<String> res = new ArrayList<>();
        int n;

        public List<String> generateParenthesis(int n) {
            this.n = n;
            dfs("", 0, 0);
            return res;
        }

        //传StringBuilder 不可以
        public void dfs(String s, int left, int right) {
            if (left == n && right == n) {
                res.add(s);
                return;
            }
            //left ++  ++left 均不可以
            if (left < n) dfs(s + "(", left + 1, right);
            //right < left
            if (right < left) dfs(s + ")", left, right + 1);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.generateParenthesis(3);
        }

        List<String> res = new ArrayList<>();
        int n;

        public List<String> generateParenthesis(int n) {
            this.n = n;
            dfs(new StringBuilder(), n, n);
            return res;
        }


        public void dfs(StringBuilder sb, int left, int right) {
            if (left == 0 && right == 0) {
                res.add(sb.toString());
                return;
            }
            if (left > 0) {
                dfs(sb.append("("), left - 1, right);
                sb.deleteCharAt(sb.length() - 1);
            }
            //right 的数量一定大于left
            if (right > left) {
                dfs(sb.append(")"), left, right - 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        List<String> res = new ArrayList<>();
        int n;

        public List<String> generateParenthesis(int n) {
            this.n = n;
            dfs("", 0, 0);
            return res;
        }

        //传StringBuilder 不可以
        public void dfs(String s, int left, int right) {
            if (s.length() == 2 * n) {//出口条件
                res.add(s);
                return;
            }
            //left ++  ++left 均不可以
            if (left < n) dfs(s + "(", left + 1, right);
            //right < left
            if (right < left) dfs(s + ")", left, right + 1);
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        List<String> res = new ArrayList<>();
        int n;

        public List<String> generateParenthesis(int n) {
            this.n = n;
            dfs("", 0, 0);
            return res;
        }

        //传StringBuilder 不可以
        public void dfs(String s, int left, int right) {
            if (left == n && right == n) {
                res.add(s);
                return;
            }
            if (left > n || right > n) return;
            if (right > left) return;
            dfs(s + "(", left + 1, right);
            //right < left
            dfs(s + ")", left, right + 1);
        }


    }
}
