package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1417 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public String reformat(String s) {
            int i = 0, j = 0;//数字、字母的下标
            char[] chas = s.toCharArray();
            for (char c : chas) {
                if (Character.isDigit(c)) {
                    i++;
                } else {
                    j++;
                }
            }
            //数量相差只能是1，否则不能形成目标的字符串
            if (Math.abs(i - j) > 1) return "";
            //数量多的按偶数下标来排列，0 2 4 ...
            //数量少的按奇数下标来排列，1,3,5...
            if (i > j) {
                i = 0;//i还是数字的下标
                j = 1;//j还是字母的下标
            } else {
                i = 1;
                j = 0;
            }
            //遍历的是s源字符串，修改的是chas，最后也是返回这个
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    chas[i] = c;//数字的下标,每次跳2步
                    i += 2;
                } else {
                    chas[j] = c;
                    j += 2;
                }
            }
            return new String(chas);
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
