package com.frankcooper.swordoffer;

import java.util.*;

import org.junit.Assert;

public class Sword_67 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.strToInt("   -42");

        }

        public int strToInt(String str) {
            str = str.trim();
            if(str == null || str.length() ==0) return 0;
            char first = str.charAt(0);
            int sign = 1, start = 0;
            if (first == '+') {
                sign = 1;
                start = 1;
            } else if (first == '-') {
                sign = -1;
                start = 1;
            }
            long res = 0;
            for (int i = start; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) return (int) (res * sign);
                res = res * 10 + str.charAt(i) - '0';
                if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;
            }
            return (int) (res * sign);

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
