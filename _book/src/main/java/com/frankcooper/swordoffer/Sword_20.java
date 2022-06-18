package com.frankcooper.platform.leetcode.swordoffer;

import java.util.*;

import org.junit.Assert;

public class Sword_20 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.isNumber("0");

        }


        public boolean isNumber(String s) {
            if (s == null || s.length() == 0) return false;
            boolean isNum = false, isDot = false, is_e_or_E = false;
            char[] ch = s.trim().toCharArray();
            for (int i = 0; i < ch.length; i++) {
                if (ch[i] >= '0' && ch[i] <= '9') isNum = true;
                else if (ch[i] == '.') {
                    if (is_e_or_E || isDot) return false;
                    isDot = true;
                } else if (ch[i] == 'e' || ch[i] == 'E') {
                    if (!isNum || is_e_or_E) return false;
                    is_e_or_E = true;
                    isNum = false;
                } else if (ch[i] == '-' || ch[i] == '+') {
                    if (i != 0 && ch[i - 1] != 'e' && ch[i - 1] != 'E') return false;
                } else return false;
            }
            return isNum;
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
