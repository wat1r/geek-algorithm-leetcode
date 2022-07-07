package com.frankcooper.platform.leetcode.bank._701_800;

import java.util.*;

import java.util.*;

import org.junit.Assert;

public class _736 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            Assert.assertEquals(5, handler.evaluate("(let x 1 y 2 x (add x y) (add x y))"));


        }


        int start = 0;
        //记录作用域，每个变量都依次记录下它从外到内的所有值，查找时只需要查找最后一个数值
        Map<String, Deque<Integer>> scope = new HashMap<>();

        public int evaluate(String expression) {
            return process(expression);
        }

        private int process(String expr) {
            if (expr.charAt(start) != '(') {//可能是整数或者变量
                if (Character.isLowerCase(expr.charAt(start))) {
                    String var = parseVar(expr);
                    return scope.get(var).peek();
                } else {
                    return parseInt(expr);
                }
            }
            int res = 0;
            start++;//移除左括号
            if (expr.charAt(start) == 'l') {//let
                start += 4;
                List<String> vars = new ArrayList<>();
                while (true) {
                    if (!Character.isLowerCase(expr.charAt(start))) {
                        res = process(expr);//let 表达式的最后一个 expr 表达式的值
                        break;
                    }
                    String var = parseVar(expr);
                    if (expr.charAt(start) == ')') {
                        res = scope.get(var).peek();//let 表达式的最后一个 expr 表达式的值
                        break;
                    }
                    vars.add(var);
                    start++;//空格
                    int e = process(expr);
                    scope.putIfAbsent(var, new ArrayDeque<>());
                    scope.get(var).push(e);
                    start++;//空格
                }
                for (String var : vars) {
                    scope.get(var).pop();// 清除当前作用域的变量
                }
            } else if (expr.charAt(start) == 'a') {//add
                start += 4;
                int e1 = process(expr);
                start++;//移除空格
                int e2 = process(expr);
                return e1 + e2;
            } else {//mult
                start += 5;
                int e1 = process(expr);
                start++;//移除空格
                int e2 = process(expr);
                return e1 * e2;
            }
            start++;//移除右括号
            return res;
        }


        private int parseInt(String expr) {
            int n = expr.length(), res = 0, sign = 1;
            if (expr.charAt(start) == '-') {
                sign = -1;
                start++;
            }
            while (start < n && Character.isDigit(expr.charAt(start))) {
                res = res * 10 + (expr.charAt(start) - '0');
                start++;
            }
            return sign * res;
        }


        private String parseVar(String expr) {
            int n = expr.length();
            StringBuilder res = new StringBuilder();
            while (start < n && expr.charAt(start) != ' ' && expr.charAt(start) != ')') {
                res.append(expr.charAt(start++));
            }
            return res.toString();
        }


    }


    //https://leetcode.cn/problems/parse-lisp-expression/solution/lisp-yu-fa-jie-xi-by-leetcode-solution-zycb/
    //官解 递归 自动状态机
    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            Assert.assertEquals(5, handler.evaluate("(let x 1 y 2 x (add x y) (add x y))"));
        }


        Map<String, Deque<Integer>> scope = new HashMap<String, Deque<Integer>>();
        int start = 0;

        public int evaluate(String expression) {
            return innerEvaluate(expression);
        }

        public int innerEvaluate(String expression) {
            if (expression.charAt(start) != '(') { // 非表达式，可能为：整数或变量
                if (Character.isLowerCase(expression.charAt(start))) {
                    String var = parseVar(expression); // 变量
                    return scope.get(var).peek();
                } else { // 整数
                    return parseInt(expression);
                }
            }
            int ret;
            start++; // 移除左括号
            if (expression.charAt(start) == 'l') { // "let" 表达式
                start += 4; // 移除 "let "
                List<String> vars = new ArrayList<String>();
                while (true) {
                    if (!Character.isLowerCase(expression.charAt(start))) {
                        ret = innerEvaluate(expression); // let 表达式的最后一个 expr 表达式的值
                        break;
                    }
                    String var = parseVar(expression);
                    if (expression.charAt(start) == ')') {
                        ret = scope.get(var).peek(); // let 表达式的最后一个 expr 表达式的值
                        break;
                    }
                    vars.add(var);
                    start++; // 移除空格
                    int e = innerEvaluate(expression);
                    scope.putIfAbsent(var, new ArrayDeque<Integer>());
                    scope.get(var).push(e);
                    start++; // 移除空格
                }
                for (String var : vars) {
                    scope.get(var).pop(); // 清除当前作用域的变量
                }
            } else if (expression.charAt(start) == 'a') { // "add" 表达式
                start += 4; // 移除 "add "
                int e1 = innerEvaluate(expression);
                start++; // 移除空格
                int e2 = innerEvaluate(expression);
                ret = e1 + e2;
            } else { // "mult" 表达式
                start += 5; // 移除 "mult "
                int e1 = innerEvaluate(expression);
                start++; // 移除空格
                int e2 = innerEvaluate(expression);
                ret = e1 * e2;
            }
            start++; // 移除右括号
            return ret;
        }

        public int parseInt(String expression) { // 解析整数
            int n = expression.length();
            int ret = 0, sign = 1;
            if (expression.charAt(start) == '-') {
                sign = -1;
                start++;
            }
            while (start < n && Character.isDigit(expression.charAt(start))) {
                ret = ret * 10 + (expression.charAt(start) - '0');
                start++;
            }
            return sign * ret;
        }

        public String parseVar(String expression) { // 解析变量
            int n = expression.length();
            StringBuffer ret = new StringBuffer();
            while (start < n && expression.charAt(start) != ' ' && expression.charAt(start) != ')') {
                ret.append(expression.charAt(start));
                start++;
            }
            return ret.toString();
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
