package com.frankcooper.platform.leetcode.bank._601_700;

import java.util.*;

import org.junit.Assert;

public class _640 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String equation = "x+5-3+x=6+x-2";
//            handler.solveEquation(equation);
            equation = "x+5+x-3+x=6+2x-2";
            handler.solveEquation(equation);


        }


        public String solveEquation(String equation) {
            int factor = 0;//x参数的前的因子
            int val = 0;//数值计算出的值
            int index = 0;//当前遍历到的结果
            int sign1 = 1;//等号左边的符号位
            int n = equation.length();
            while (index < n) {


                int sign2 = sign1;
                //遇到+ - 符号
                if (equation.charAt(index) == '+' || equation.charAt(index) == '-') {

                }


            }


            return null;
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
